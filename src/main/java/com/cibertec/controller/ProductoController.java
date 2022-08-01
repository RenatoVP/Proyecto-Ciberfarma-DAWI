package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Producto;
import com.cibertec.service.CategoriaServiceImpl;
import com.cibertec.service.ProductoServiceImpl;
import com.cibertec.service.ProveedorServiceImpl;
import com.cibertec.util.ClaseUtil;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoServiceImpl serviceProducto;
	
	@Autowired
	private CategoriaServiceImpl serviceCategoria;
	
	@Autowired
	private ProveedorServiceImpl serviceProveedor;
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("lstcategoria", serviceCategoria.findAll());
		model.addAttribute("lstproveedor", serviceProveedor.findAll());
	}
	
	@GetMapping("/listar")
	public String listar(Model model, Pageable page) {
		model.addAttribute("lstproducto", serviceProducto.findAllPagination(page));
		
		return "Producto/listaproductos";
	}
	
	@GetMapping("/registrar")
	public String registrarForm(Producto producto, Model model) {
		
		return "Producto/formproducto";
	}
	
	@PostMapping("/registrar")
	public String registrar(Producto producto, BindingResult result, @RequestParam("archivoImagen") MultipartFile multipart, RedirectAttributes redirect) {
		//Si se detectan errores de formato al momento de registrar producto
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			
			return "redirect:/producto/registrar";
		}
		
		if(!multipart.isEmpty()) {
			String nombreArchivo = ClaseUtil.saveFile(multipart, "C:/Mis Archivos/Java_Proyects/Spring/CiberfarmaApp_Proyect/Files/images/");
			
			//Si la imagen se subio
			if(nombreArchivo != null) {
				producto.setImagen(nombreArchivo);
			}
		}
		
		//Si no hay errores registramos el producto
		serviceProducto.save(producto);
		
		redirect.addFlashAttribute("message", "Se registro el producto satisfactoriamente!");
		
		return "redirect:/producto/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") String idProducto, Model model) {
		model.addAttribute("producto", serviceProducto.find(idProducto));
		
		return "Producto/formproducto";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") String idProducto, RedirectAttributes redirect) {
		serviceProducto.delete(idProducto);
		
		redirect.addFlashAttribute("message", "Producto eliminado correctamente!");
		
		return "redirect:/producto/listar";
	}
}
