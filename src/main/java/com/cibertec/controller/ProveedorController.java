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

import com.cibertec.entity.Proveedor;
import com.cibertec.service.ProveedorServiceImpl;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorServiceImpl serviceProveedor;
	
	@GetMapping("/listar")
	public String listar(Model model, Pageable page) {
		model.addAttribute("lstproveedor", serviceProveedor.findAllPagination(page));
		
		return "Proveedor/listaproveedor";
	}
	
	@GetMapping("/registrar")
	public String registrarForm(Proveedor proveedor) {
		return "Proveedor/formproveedor";
	}
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Proveedor proveedor, RedirectAttributes redirect) {
		serviceProveedor.save(proveedor);
		
		redirect.addFlashAttribute("message", "Se registro el proveedor sastifactoriamente");
		
		return "redirect:/proveedor/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer idProveedor, Model model) {
		model.addAttribute("proveedor", serviceProveedor.find(idProveedor));
		
		return "Proveedor/formproveedor";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer idProveedor, RedirectAttributes redirect) {
		serviceProveedor.delete(idProveedor);
		
		redirect.addFlashAttribute("message", "Proveedor eliminado correctamente");
		
		return "redirect:/proveedor/listar";
	}
}
