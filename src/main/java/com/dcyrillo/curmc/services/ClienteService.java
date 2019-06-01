package com.dcyrillo.curmc.services;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dcyrillo.curmc.domain.Cliente;
import com.dcyrillo.curmc.domain.Cliente;
import com.dcyrillo.curmc.dto.ClienteDto;
import com.dcyrillo.curmc.repositories.ClienteRepository;
import com.dcyrillo.curmc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	public Cliente insert(Cliente obj) {
		obj.setId(null);
	
		return repo.save(obj);
	}
	public Cliente update(Cliente obj) {
		Cliente c=find(obj.getId());
		updateData(c,obj);
		return repo.save(obj);
		
	}
	public void delete(Integer id) throws Exception {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(Exception e) {
			
			throw new Exception("Não é possível excluir uma categoria que possui produtos");
		}
	}
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	public Page<Cliente> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	public Cliente fromDto(ClienteDto objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
	}
	private void updateData(Cliente c,Cliente obj) {
		c.setNome(obj.getNome());
		c.setEmail(obj.getEmail());
		
	}
}

	

