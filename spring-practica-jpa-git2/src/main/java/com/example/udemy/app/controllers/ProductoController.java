package com.example.udemy.app.controllers;

import java.io.IOException;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.udemy.app.models.dao.IFacturaDao;
import com.example.udemy.app.models.entity.Cliente;
import com.example.udemy.app.models.entity.Factura;
import com.example.udemy.app.models.entity.Producto;
import com.example.udemy.app.models.service.IProductoService;
import com.example.udemy.app.models.service.ProductoService;
import com.example.udemy.app.util.paginator.PageRender;

@Controller
@SessionAttributes("producto")
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Producto> productos = productoService.findAll(pageRequest);
		PageRender<Producto> pageRender = new PageRender<>("/producto/listar", productos);

		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		return "producto/listar";
	}
	
		
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Producto prod = new Producto();
		model.put("producto", prod);
		model.put("titulo", "Formulario de Producto");		
		return "producto/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result,RedirectAttributes flash, SessionStatus status, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de producto");
			return "/producto/form";
		}
		
		String mensajeFlash = (producto.getId() != null) ? "Producto editado con éxito!" : "Producto creado con éxito!";
	
		//Producto productoItems = productoService.findOne(producto.getId());

		/*if (productoItems.getItems()!=null && !productoItems.getItems().isEmpty()) {
			model.addAttribute("titulo", "El producto existe en una factura");
			return "producto/form";
		}*/
		
		productoService.save(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producto/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Producto producto = null;
		
		if (id > 0) {
			producto = productoService.findOne(id);
			if (producto == null) {
				flash.addFlashAttribute("error", "El Id del producto no existe en la BBDD");
				return "redirect:/producto/listar";
			}
			
		} else {
			flash.addFlashAttribute("error", "El Id del producto no puede ser cero");
			return "redirect:/producto/listar";
		} 
		
		/*if(producto.getItems()!=null && !producto.getItems().isEmpty()) {
			flash.addFlashAttribute("error", "El producto existe en una factura");
			return "redirect:/producto/listar";
		}*/
			
		model.put("producto", producto);
		model.put("titulo", "Editar Producto");
		return "producto/form";	
	}
	

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			//Producto producto = productoService.findOne(id);

			productoService.delete(id);
			flash.addFlashAttribute("success", "Producto  eliminado con exito");
			}
		return "redirect:/producto/listar";
	}
	
	@RequestMapping(value = "/ver/{id}",method = RequestMethod.GET)
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Producto producto = productoService.findOne(id);
		if (producto== null) {
			flash.addFlashAttribute("error", "El producto no existe en la base ed datos");
			return "redirect:/producto/listar";	
		}		
		model.put("producto", producto);
		model.put("titulo", "Detalle producto: " + producto.getNombre());
		return "/producto/ver";
	}

		

		
	}
	

