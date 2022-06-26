package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Usuario;
import com.cibertec.repository.ITipoRepository;
import com.cibertec.repository.IUsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private ITipoRepository tipoRepo;
	
	@Autowired
	private IUsuarioRepository usuarioRepo;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/validar")
	public String validar(@ModelAttribute(name="usuario") Usuario usuario, RedirectAttributes redirect) {
		Usuario data = usuarioRepo.findByUsernameAndContrasena(usuario.getUsername(), usuario.getContrasena());
		redirect.addFlashAttribute("usuario", data);
		return "redirect:/";
	}
	
	@GetMapping("/listar")
	public String lista(Model model) {
		model.addAttribute("lstusuario", usuarioRepo.findAll());
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
			usuarioRepo.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/usuario/listar";
	}
}
