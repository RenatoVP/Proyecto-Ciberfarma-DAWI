package com.cibertec.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class DataBaseWebSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	// Definiendo nuestras tablas con Spring Security
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select usr_usua, cla_usua, est_usua from tb_usuarios where usr_usua=?")
				.authoritiesByUsernameQuery("select u.usr_usua, t.descripcion from tb_usuario_tipo ut "
						+ "inner join tb_usuarios u on u.cod_usua = ut.cod_usua "
						+ "inner join tb_tipos t on t.idtipo = ut.idtipo " + "where u.usr_usua = ?");
	}
	
	//Brindar o bloquear acceso
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			// Los recursos estaticos no requieren autenticacion
			.antMatchers("/css/**", "/img/**", "/js/**", "/producto/imagen/**").permitAll()
			// Las vistas publicas no requieren autenticacion
			.antMatchers("/", "/signup", "/carrito","/search", "/bcrypt/**").permitAll()
			// Asignar permisos a URLs por Roles
			.antMatchers("/producto/**", "/categoria/**", "/usuario/**", "/proveedor/**").hasAnyAuthority("administrativo")
			// Todas las demas URLs de la Aplicacion requieren autenticacion
			.anyRequest().authenticated().and()
			.csrf().disable()
			// El formulario de login no requiere autenticacion
			.formLogin().loginPage("/login").permitAll();
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
