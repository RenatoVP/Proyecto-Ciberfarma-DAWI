package com.cibertec.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/reporte")
public class ReporteController {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/")
	public void reportes(HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/pdf");
		
		try {
			String ru = resourceLoader.getResource("classpath:Coffee.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/producto")
	public void productoxcategoriayprecio(HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/pdf");
		
		try {
			String ru = resourceLoader.getResource("classpath:productoxcategoriayprecio.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
