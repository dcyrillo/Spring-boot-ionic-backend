package com.dcyrillo.curmc.services;

import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcyrillo.curmc.domain.Pedido;
import com.dcyrillo.curmc.repositories.PedidoRepository;
import com.dcyrillo.curmc.services.exception.ObjectNotFoundException;

@Service

public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
