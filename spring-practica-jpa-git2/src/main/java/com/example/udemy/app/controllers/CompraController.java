package com.example.udemy.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.udemy.app.models.entity.Cliente;
import com.example.udemy.app.models.entity.Compra;
import com.example.udemy.app.models.entity.ComprasItems;
import com.example.udemy.app.models.entity.Factura;
import com.example.udemy.app.models.entity.ItemFactura;
import com.example.udemy.app.models.entity.Producto;
import com.example.udemy.app.models.entity.Proveedor;
import com.example.udemy.app.models.service.CompraService;
import com.example.udemy.app.models.service.IClienteService;
import com.example.udemy.app.models.service.ICompraService;
import com.example.udemy.app.models.service.IProductoService;
import com.example.udemy.app.models.service.IProveedorService;
import com.example.udemy.app.util.paginator.PageRender;

@Controller
@RequestMapping("/compra")
@SessionAttributes("compra")
public class CompraController {
	
	@Autowired
	private ICompraService compraService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IProveedorService proveedorService;
	
	@Autowired
	private IProductoService productoService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	
	
	@GetMapping("/form/{proveedorId}")
	public String crear(@PathVariable(value="proveedorId") Long proveedorId, Map<String,Object> model, RedirectAttributes flash) {
		Proveedor proveedor = proveedorService.findOne(proveedorId);
		if(proveedor == null) {
			flash.addFlashAttribute("error", "El proveedor no existe en la base de datos");
		return "redirect:/listar";
		}
		
		Compra compra = new Compra();
		compra.setProveedor(proveedor);
		model.put("compra", compra);
		model.put("titulo", "Crear compra");
		
		return "/compra/form";
}
	
	@GetMapping(value="/cargar-productos/{term}",produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
		return productoService.findByNombre(term);
	}
	

	@PostMapping("/form")
	public String guardar(@Valid Compra compra,
			BindingResult result,
			Model model,
			@RequestParam(name="item_id[]",required= false) Long[] itemId,
			@RequestParam(name="cantidad[]",required=false) Integer[] cantidad,
			@RequestParam(name="precio[]",required=false) Double[] precio_compra,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			
			return "compra/form";
		}
	
		if(itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear Compra");
			model.addAttribute("error", "Error: la factura no puede no tener lineas");
			
			return "compra/form";
		}
		
		ArrayList<Producto> productoStock = new ArrayList<Producto>();
				
		for(int i = 0; i< itemId.length; i ++) {
			int nuevoStock=0;
			Producto producto = productoService.findProductoById(itemId[i]);
			
			if(cantidad[i]+producto.getStock()>=100) {
				model.addAttribute("error", "Error: La cantidad de stock no puede ser mayor de cien :" + producto.getNombre());
				return "compra/form";
				                              					
			}
			
		     nuevoStock=producto.getStock() +cantidad[i];
		      producto.setStock(nuevoStock);
		     productoStock.add(producto);
				
			ComprasItems linea =  new ComprasItems();
			linea.setCantidad(cantidad[i]);
			linea.setPrecio_compra(precio_compra[i]);
			linea.setProducto(producto);
			compra.addComprasItems(linea);
			
			
			log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
		}
		
		/*for (int i = 0; i < productoStock.size(); i++) {			
			productoService.save(productoStock.get(i));
		}*/
		/*for (Producto prod : productoStock) {
			productoService.save(prod);
		}*/
		productoService.saveAll(productoStock);
		
		compraService.save(compra);
		
		status.setComplete();
		
		flash.addAttribute("success", "Factura creada con exito");
		
		return "redirect:/proveedor/ver/" + compra.getProveedor().getId();
	} 
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id,
			Model model,
			RedirectAttributes flash) {
		Compra compra = compraService.findCompraById(id);
		
		if(compra == null) {
			flash.addFlashAttribute("error", "La compra no se existe en la base de datos!");
			return "redirect:/listar";
		}
		model.addAttribute("compra", compra);
		model.addAttribute("titulo", "Detalle compra: " + compra.getProveedor().getNombre());
		
		return "compra/ver";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		Compra compra = compraService.findCompraById(id);
		 
		if(compra != null) {
			compraService.delete(id);;
			flash.addFlashAttribute("success", "Compra eliminada con exito!");
			return "redirect:/ver/" + compra.getProveedor().getId();
		}
		flash.addFlashAttribute("error", "La compra no existe en la base de datos, no se pudo eliminar");
		
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Compra> compras = compraService.findAll(pageRequest);
		PageRender<Compra> pageRender = new PageRender<>("/compra/listar", compras);

		model.addAttribute("titulo", "Listado de Compras");
		model.addAttribute("compras", compras);
		model.addAttribute("page", pageRender);
		return "compra/listar";
	}

	
}
	
	