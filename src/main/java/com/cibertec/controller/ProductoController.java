package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.entity.Producto;
import com.cibertec.repository.ICategoriaRepository;
import com.cibertec.repository.IProductoRepository;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private IProductoRepository productoRepo;

	@Autowired
	private ICategoriaRepository categoriaRepo;

	@GetMapping("/agregar")
	public String cargarForm(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("lstCategoria", categoriaRepo.findAll());
		return "crudproductos";
	}

	@PostMapping("/agregar")
	public String grabarForm(@ModelAttribute(name = "producto") Producto producto, Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("lstCategoria", categoriaRepo.findAll());
		try {
			productoRepo.save(producto);
			model.addAttribute("success", "Proceso realizado con exito");
		} catch (Exception e) {
			model.addAttribute("error", "Error al registrar");
		}
		return "crudproductos";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("lstproducto", productoRepo.findAll());
		return "listadoproductos";
	}

	@GetMapping("/buscaxcategoria")
	public String listarxCategoria(Model model) {
		model.addAttribute("lstcategoria", categoriaRepo.findAll());
		return "consultaproductoxcategoria";
	}

	@PostMapping("/buscaxcategoria")
	public String consultaxCategoria(@RequestParam int categoria, Model model) {
		List<Producto> lstproducto;

		if (categoria == -1) {
			lstproducto = productoRepo.findAll();
		} else {
			lstproducto = productoRepo.findAllByIdcategoria(categoria);
			;
		}

		model.addAttribute("lstcategoria", categoriaRepo.findAll());
		model.addAttribute("lstproducto", lstproducto);
		return "consultaproductoxcategoria";
	}

	@GetMapping("/buscar")
	public String buscaProductoForm(Model model) {

		return "consultaproductos";
	}

	@PostMapping("/buscar")
	public String buscarProducto(@RequestParam("nombre") String nombre, Model model) {
		model.addAttribute("lstproducto", productoRepo.findAllByProductName(nombre));
		return "consultaproductos";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute(name = "producto") Producto producto, Model model) {
		System.out.println(producto);
		model.addAttribute("producto", productoRepo.findById(producto.getId()));
		model.addAttribute("lstCategoria", categoriaRepo.findAll());
		return "crudproductos";
	}

	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute(name = "producto") Producto producto, Model model) {
		productoRepo.delete(producto);
		return "redirect:/producto/listar";
	}

	/* Reporte Views */
	@GetMapping("/reporte")
	public String reporteProductoxCategoriayPrecio(Model model) {
		model.addAttribute("lstcategoria", categoriaRepo.findAll());
		return "reporteproductoxcategoria";
	}

	@PostMapping("/reporte")
	public String reporteProductoxCategoriayPrecio(@RequestParam int categoria, @RequestParam(defaultValue = "0.0") double precio1,
			@RequestParam(defaultValue = "0.0") double precio2, Model model) {
		List<Producto> lstproducto;
		
		if(categoria == -1)
			lstproducto = productoRepo.findAllByPrecioBetween(precio1, precio2);
		else
			lstproducto = productoRepo.findAllByCategoryPriceBetween(categoria, precio1, precio2);
		
		model.addAttribute("lstcategoria", categoriaRepo.findAll());
		model.addAttribute("lstproducto", lstproducto);
		return "reporteproductoxcategoria";
	}
}
