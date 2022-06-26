package com.cibertec.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.entity.Boleta;
import com.cibertec.entity.BoletaProducto;
import com.cibertec.entity.DetalleBoleta;
import com.cibertec.entity.Producto;
import com.cibertec.repository.IBoletaRepository;
import com.cibertec.repository.IDetalleBoletaRepository;
import com.cibertec.repository.IProductoRepository;

@Controller
@RequestMapping("/boleta")
public class BoletaController {

	@Autowired
	private IProductoRepository productoRepo;

	@Autowired
	private IDetalleBoletaRepository dbRepo;

	@Autowired
	private IBoletaRepository boletaRepo;

	// Utilidades
	private ArrayList<BoletaProducto> lstproducto = new ArrayList<BoletaProducto>();

	double totalAPagar = 0.0;
	int item = 0;
	double base = 0.0;

	// Carga la pagina de formularioboleta
	@GetMapping("/genera")
	public String cargaBoleta(Model model) {
		model.addAttribute("totalAPagar", totalAPagar);
		model.addAttribute("lstboletaproducto", lstproducto);
		return "Boleta/formularioboleta";
	}

	// Almacena los datos de la boleta con sus respectivos productos solicitados
	// en la base de datos
	@PostMapping("/genera")
	public String generaBoleta(Model model) {
		try {
			// Guardar Boleta
			Boleta boleta = new Boleta();
			boleta.setFec_bol(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			boleta.setIdusuario(1);

			boletaRepo.save(boleta);

			// Guardar Detalle Boleta
			for (int i = 0; i <= lstproducto.size() - 1; i++) {
				DetalleBoleta db = new DetalleBoleta();
				db.setNroboleta(boleta.getCodigo());
				db.setIdprod(lstproducto.get(i).getIdproducto());
				db.setCantidad(lstproducto.get(i).getCantidad());
				db.setPreciovta(lstproducto.get(i).getSubtotal() * lstproducto.get(i).getCantidad());

				dbRepo.save(db);
			}
			
			totalAPagar = 0.0;
			lstproducto.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
		item = 0;

		return "Boleta/formularioboleta";
	}

	// Se ejecuta al agregar un producto en la boleta
	@PostMapping("/productoboleta")
	public String productToBoleta(@ModelAttribute Producto producto, Model model) {
		item++;
		Producto productoEncontrado = productoRepo.getById(producto.getId());
		// Almacena el producto encontrado
		BoletaProducto bp = new BoletaProducto();
		bp.setItem(item);
		bp.setIdproducto(productoEncontrado.getId());
		bp.setProducto(productoEncontrado.getDescripcion());
		bp.setCategoria(productoEncontrado.getCategoria().getDescripcion());
		bp.setPreciounitario(productoEncontrado.getPrecio());
		bp.setCantidad(1);
		bp.setSubtotal(Double.parseDouble(new DecimalFormat("#.00").format(productoEncontrado.getPrecio() * 1)));
		

		lstproducto.add(bp);
		
		//Agrega el importe a pagar
		totalAPagar += bp.getSubtotal() * bp.getCantidad();
		
		model.addAttribute("totalAPagar", totalAPagar);
		model.addAttribute("lstproducto", lstproducto);
		return "Boleta/formularioboleta";
	}
	
	@PostMapping("/actualizaStock")
	public String actualizaStock(@RequestParam int index, @RequestParam int cantidad, Model model) {
		//Obtenemos precio inicial antes de actualizarlo
		base = lstproducto.get(index - 1).getSubtotal() * lstproducto.get(index - 1).getCantidad(); 
		
		//Actualizamos el stock del producto seleccionado
		lstproducto.get(index - 1).setCantidad(cantidad);
		
		//Subtotal actualizado
		double precioActualizado = lstproducto.get(index - 1).getSubtotal() * lstproducto.get(index - 1).getCantidad();
		
		totalAPagar += precioActualizado - base;
		
		model.addAttribute("totalAPagar", totalAPagar);
		model.addAttribute("lstproducto", lstproducto);
		return "Boleta/formularioboleta";
	}

	// Se ejecuta al quitar un producto seleccionado por error o por cambio de
	// decision por parte del cliente
	@PostMapping("/quitaProducto")
	public String quitaProducto(@RequestParam int index, Model model) {
		//Restamos el precio total del producto seleccionado mas su cantidad
		totalAPagar -= lstproducto.get(index - 1).getSubtotal() * lstproducto.get(index - 1).getCantidad();
		item--;
		lstproducto.remove(index -1);
		
		model.addAttribute("totalAPagar", totalAPagar);
		model.addAttribute("lstproducto", lstproducto);
		return "Boleta/formularioboleta";
	}
}
