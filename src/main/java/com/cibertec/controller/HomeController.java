package com.cibertec.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cibertec.entity.Producto;
import com.cibertec.entity.Usuario;
import com.cibertec.service.CategoriaServiceImpl;
import com.cibertec.service.ProductoServiceImpl;
import com.cibertec.service.UsuarioServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private CategoriaServiceImpl serviceCategoria;

	@Autowired
	private ProductoServiceImpl serviceProducto;
	
	@Autowired
	private UsuarioServiceImpl serviceUsuario;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Init Binder para String si los detecta vacios en el Data Binding los setea a
	// null
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("lstcategoria", serviceCategoria.findAll());
		model.addAttribute("lstproducto", serviceProducto.findAll());
		model.addAttribute("search", new Producto());
	}
	
	@GetMapping("/")
	public String home(Model model) {

		return "principal";
	}

	@GetMapping("/index")
	public String index(Authentication auth, HttpSession session) {
		String username = auth.getName();
		System.out.println("Nombre del usuario: " + username);
		
		//Verificar los roles que tiene el usuario ingresado
		for(GrantedAuthority rol : auth.getAuthorities()) {
			System.out.println("Rol: " + rol.getAuthority());
		}
		
		//Si el atributo usuario en httpsession no existe, crear uno
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = serviceUsuario.findByUsername(username);
			usuario.setContrasena(null);
			
			System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/signup")
	public String registrarForm(Usuario usuario) {
		return "formRegistro";
	}

	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Producto producto, Model model) {
		// where descripcion like %?1%
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion",
				ExampleMatcher.GenericPropertyMatchers.contains());

		model.addAttribute("lstproducto", serviceProducto.findByExample(Example.of(producto, matcher)));

		return "principal";
	}

	// Encriptar contraseñas
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encriptar(@PathVariable("texto") String texto) {
		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
	}
}
