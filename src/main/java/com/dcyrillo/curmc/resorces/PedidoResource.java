package com.dcyrillo.curmc.resorces;


import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dcyrillo.curmc.domain.Categoria;
import com.dcyrillo.curmc.domain.Pedido;
import com.dcyrillo.curmc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")

public class PedidoResource {
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido obj=service.findr(id);
		return ResponseEntity.ok().body(obj);
		
		
	}
}
