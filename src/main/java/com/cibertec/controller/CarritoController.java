package com.cibertec.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Carrito;
import com.cibertec.entity.Producto;
import com.cibertec.service.ProductoServiceImpl;
import com.cibertec.util.Shopping;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
	
	@Autowired
	private ProductoServiceImpl serviceProducto;
	
	@GetMapping("/")
	public String carrito(Model model, HttpServletRequest request) {
		double total = 0;
		ArrayList<Carrito> lstcarrito = Shopping.get(request);
		
		for(Carrito carrito : lstcarrito) total += carrito.getTotal();
		
		model.addAttribute("total", total);
		
		return "carrito";
	}
	
	@PostMapping("/agregar/")
	public String agregarCarrito(@RequestParam("idproducto") String idproducto, @RequestParam("cantidad") Integer cantidad, HttpServletRequest request, RedirectAttributes redirect, Model model) {
		ArrayList<Carrito> lstcarrito = Shopping.get(request);
		Producto producto = serviceProducto.find(idproducto);
		
		boolean encontrado = false;
		
		//Verificar si existen existencia del producto seleccionado
		
		for(Carrito carrito : lstcarrito) {
			//Si se encuentra el id del producto en la lista, agregar cantidad al producto encontrado
			if(carrito.getId().equals(producto.getId())) {
				carrito.agregaCantidad(cantidad);
				encontrado = true;
				
				break;
			}
			
		}
		
		if(!encontrado) {
			//Agregamos un producto al carrito
			Carrito carrito = new Carrito();
			carrito.setId(idproducto);
			carrito.setDescripcion(producto.getDescripcion());
			carrito.setImagen(producto.getImagen());
			carrito.setCantidad(cantidad);
			carrito.setPrecio(producto.getPrecio());
			
			lstcarrito.add(carrito);
		}
		
		Shopping.save(lstcarrito, request);

		return "redirect:/carrito/";
	}
	
	@GetMapping("/quitar/{index}")
	public String quitarCarrito(@PathVariable("index") int index, HttpServletRequest request) {
		ArrayList<Carrito> lstcarrito = Shopping.get(request);
		
		if(lstcarrito != null && lstcarrito.size() > 0 && lstcarrito.get(index) != null) {
			lstcarrito.remove(index);
			Shopping.save(lstcarrito, request);
		}
		
		return "redirect:/carrito/";
	}
	
	@GetMapping("/vaciar")
	public String vaciarCarrito(HttpServletRequest request) {
		Shopping.clear(request);
		
		return "redirect:/carrito/";
	}
}
