package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Categoria;
import com.cibertec.service.CategoriaServiceImpl;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaServiceImpl serviceCategoria;

	@GetMapping("/listar")
	public String listar(Model model, Pageable page) {
		model.addAttribute("lstcategoria", serviceCategoria.findAllPagination(page));
		
		return "Categoria/listacategoria";
	}
	
	@GetMapping("/registrar")
	public String registrarForm(Categoria categoria) {
		
		return "Categoria/formcategoria";
	}
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Categoria categoria, RedirectAttributes redirect) {
		serviceCategoria.save(categoria);
		
		redirect.addFlashAttribute("message", "Se registro la categoria satisfactoriamente");
		
		return "redirect:/categoria/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer idCategoria, Model model) {
		model.addAttribute("categoria", serviceCategoria.find(idCategoria));
		
		return "Categoria/formcategoria";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer idCategoria, RedirectAttributes redirect) {
		serviceCategoria.delete(idCategoria);
		
		redirect.addFlashAttribute("message", "Categoria eliminada correctamente");
		
		return "redirect:/categoria/listar";
	}
}
