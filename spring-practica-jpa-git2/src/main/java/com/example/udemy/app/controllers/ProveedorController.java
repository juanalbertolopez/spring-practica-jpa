package com.example.udemy.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.udemy.app.models.entity.Producto;
import com.example.udemy.app.models.entity.Proveedor;
import com.example.udemy.app.models.service.IProveedorService;
import com.example.udemy.app.util.paginator.PageRender;

@Controller
@SessionAttributes("proveedor")
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private IProveedorService proveedorService;
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Proveedor> proveedores = proveedorService.findAll(pageRequest);
		PageRender<Proveedor> pageRender = new PageRender<>("/proveedor/listar", proveedores);

		model.addAttribute("titulo", "Listado de Proveedores");
		model.addAttribute("proveedores", proveedores);
		model.addAttribute("page", pageRender);
		return "proveedor/listar";
	}
	
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Proveedor proveedor = proveedorService.findOne(id);
		if (proveedor == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base ed datos");
			return "redirect:proveedor/listar";
		}
		model.put("proveedor", proveedor);
		model.put("titulo", "Detalle proveedor: " + proveedor.getNombre());

		return "/proveedor/ver";
	}
	
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Proveedor proveedor = new Proveedor();
		model.put("proveedor", proveedor);
		model.put("titulo", "Formulario de Proveedor");		
		return "proveedor/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Proveedor proveedor, BindingResult result,RedirectAttributes flash, SessionStatus status, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de proveedores");
			return "/proveedor/form";
		}
		String mensajeFlash = (proveedor.getId() != null) ? "Proveedor editado con éxito!" : "Proveedor creado con éxito!";
		
		proveedorService.save(proveedor);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/proveedor/listar";
		
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Proveedor proveedor = null;
		if (id > 0) {
			proveedor= proveedorService.findOne(id);
			if (proveedor == null) {
				flash.addFlashAttribute("error", "El Id del proveedor no existe en la BBDD");
				return "redirect:proveedor/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El Id del proveedor no puede ser cero");
			return "redirect:proveedor/listar";
		}
		model.put("proveedor", proveedor);
		model.put("titulo", "Editar Proveedor");
		return "/proveedor/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Proveedor proveedor = proveedorService.findOne(id);

			proveedorService.delete(id);
			flash.addFlashAttribute("success", "Proveedor  eliminado con exito");
			}
		return "redirect:/proveedor/listar";
	}


}
