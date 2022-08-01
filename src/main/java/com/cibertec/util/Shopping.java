package com.cibertec.util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.cibertec.entity.Carrito;

public class Shopping {
	
	//Obtener el carrito de compras
	public static ArrayList<Carrito> get(HttpServletRequest request){
		ArrayList<Carrito> lstcarrito = (ArrayList<Carrito>) request.getSession().getAttribute("carrito");
		
		if(lstcarrito == null)
			lstcarrito = new ArrayList<Carrito>();
		
		return lstcarrito;
	}
	
	//Guardar Carrito
	public static void save(ArrayList<Carrito> lstcarrito, HttpServletRequest request) {
		request.getSession().setAttribute("carrito", lstcarrito);
	}
	
	//Limpiar registro de carrito
	public static void clear(HttpServletRequest request) {
		Shopping.save(new ArrayList<>(), request);
	}
}
