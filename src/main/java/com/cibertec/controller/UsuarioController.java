package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Usuario;
import com.cibertec.repository.ITipoRepository;
import com.cibertec.repository.IUsuarioRepository;
import com.cibertec.service.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private ITipoRepository tipoRepo;
	
	@Autowired
	private UsuarioServiceImpl serviceUsuario;
	
	@GetMapping("/listar")
	public String lista(Model model) {
		model.addAttribute("lstusuario", serviceUsuario.findAll());
		return "Usuario/listadousuarios";
	}
	
	@GetMapping("/agregar")
	public String cargaForm(Model model) {
		model.addAttribute("lsttipo", tipoRepo.findAll());
		model.addAttribute("usuario", new Usuario());
		return "Usuario/crudusuarios";
	}
	
	@PostMapping("/agregar")
	public String grabarForm(@ModelAttribute Usuario usuario, Model model) {
		try {
			serviceUsuario.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/usuario/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer idUsuario, RedirectAttributes redirect) {
		serviceUsuario.delete(idUsuario);
		
		redirect.addFlashAttribute("message", "Usuario eliminado correctamente!");
		
		return "redirect:/usuario/listar";
	}
}
