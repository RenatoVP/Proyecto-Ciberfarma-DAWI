package com.cibertec.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class ClaseUtil {
	public static String saveFile(MultipartFile multipart, String ruta) {
		// Obtenemos el nombre original del archivo
		String nombreOriginal = multipart.getOriginalFilename();
		nombreOriginal.replace(" ", "-");

		String nombreFinal = randomAlphaNumeric(8) + nombreOriginal;
		try {
			// Creamos el nombre del archivo para guardarlo en el disco duro
			File file = new File(ruta + nombreFinal);
			System.out.println("Archivo: " + file.getAbsolutePath());

			// Guardamos fisicamente el archivo
			multipart.transferTo(file);
			return nombreFinal;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}

	public static String randomAlphaNumeric(int count) {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();

		while (count-- != 0) {
			int character = (int) (Math.random() * caracteres.length());
			builder.append(caracteres.charAt(character));
		}

		return builder.toString();

	}
}
