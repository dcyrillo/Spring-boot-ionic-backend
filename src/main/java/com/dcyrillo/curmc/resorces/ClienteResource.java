package com.dcyrillo.curmc.resorces;
import java.net.URI;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dcyrillo.curmc.domain.Categoria;
import com.dcyrillo.curmc.domain.Cliente;
import com.dcyrillo.curmc.domain.Cliente;
import com.dcyrillo.curmc.dto.CategoriaDto;
import com.dcyrillo.curmc.dto.ClienteDto;
import com.dcyrillo.curmc.dto.ClienteNewDTO;
import com.dcyrillo.curmc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")

public class ClienteResource {
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj=service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public  ResponseEntity<Void>  update(@RequestBody @Valid ClienteDto objDto,@PathVariable Integer id){
		Cliente obj= service.fromDto(objDto);
		obj.setId(id);
		obj=service.update(obj);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
	
	service.delete(id);
	return ResponseEntity.noContent().build();
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDto>> findall() {
		List<Cliente> list=service.findAll();
		List<ClienteDto> listDto = list.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
		
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDto>> findPage(@RequestParam(value="/page",defaultValue="0")Integer page,
			@RequestParam(value="/linesPerPage",defaultValue="24")Integer linesPerPage,
			@RequestParam(value="/orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="/direction",defaultValue="ASC")String direction) {
		Page<Cliente> list=service.findPage(page,linesPerPage,orderBy,direction);
		Page<ClienteDto> listDto = list.map(obj -> new ClienteDto(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
}
