package com.dcyrillo.curmc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dcyrillo.curmc.repositories.CategoriaRepository;
import com.dcyrillo.curmc.repositories.CidadeRepository;
import com.dcyrillo.curmc.repositories.ClienteRepository;
import com.dcyrillo.curmc.repositories.EnderecoRepository;
import com.dcyrillo.curmc.repositories.EstadoRepository;
import com.dcyrillo.curmc.repositories.ItemPedidoRepository;
import com.dcyrillo.curmc.repositories.PagamentoRepository;
import com.dcyrillo.curmc.repositories.PedidoRepository;
import com.dcyrillo.curmc.repositories.ProdutoRepository;


@SpringBootApplication
public class Curmc1Application implements CommandLineRunner {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Curmc1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			
	}

}
