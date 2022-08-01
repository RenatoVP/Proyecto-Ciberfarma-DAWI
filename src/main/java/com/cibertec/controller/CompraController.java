package com.cibertec.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Carrito;
import com.cibertec.entity.Compra;
import com.cibertec.entity.DetalleCompra;
import com.cibertec.entity.Producto;
import com.cibertec.entity.Usuario;
import com.cibertec.service.CompraServiceImpl;
import com.cibertec.service.DetalleServiceImpl;
import com.cibertec.service.ProductoServiceImpl;
import com.cibertec.service.UsuarioServiceImpl;
import com.cibertec.util.Shopping;

@Controller
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	private CompraServiceImpl serviceCompra;
	
	@Autowired
	private DetalleServiceImpl serviceDetalleCompra;
	
	@Autowired
	private ProductoServiceImpl serviceProducto;
	
	@Autowired
	private UsuarioServiceImpl serviceUsuario;
	
	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request, HttpSession session, Authentication auth, RedirectAttributes redirect) {
		ArrayList<Carrito> lstcarrito = Shopping.get(request);
		
		// Recuperamos el username del usuario que inicio session
		String username = auth.getName();
		
		//Buscamos el objeto Usuario en BD
		Usuario usuario = serviceUsuario.findByUsername(username);
		
		// Si no hay carrito o está vacío, regresamos inmediatamente
		if(lstcarrito == null || lstcarrito.size() <= 0) {
			redirect.addFlashAttribute("message", "Su carrito esta vacio");
			
			return "redirect:/carrito/";
		}
		
		//Guardamos la compra
		Compra compra = new Compra();
		
		compra.setFecha(new Date());
		compra.setUsuario(usuario);
		
		serviceCompra.save(compra);
		
		//Recorrer el carrito
		for(Carrito carrito : lstcarrito) {
			//Obtenemos el producto desde la base de datos
			Producto producto = serviceProducto.find(carrito.getId());
			
			
			if(producto == null) continue; //Si es nulo o no existe, ignoramos el siguiente codigo con continue
			
			//Restamos existencias
			producto.quitar(carrito.getCantidad());
			
			//Lo guardamos con la existencia restada
			serviceProducto.save(producto);
			
			//Creamos un nuevo producto que sera el que se guarda junto con la venta
			DetalleCompra detalleCompra = new DetalleCompra();
			
			detalleCompra.setCompra(compra);
			detalleCompra.setProducto(producto);
			detalleCompra.setCantidad(carrito.getCantidad());
			detalleCompra.setPrecioventa(carrito.getPrecio());
			
			serviceDetalleCompra.save(detalleCompra);
		}
		
		//Al finaliza limpiamos el carrito
		Shopping.clear(request);
		
		redirect.addFlashAttribute("message", "Se registro su compra correctamente");
		
		return "redirect:/";
	}
}
