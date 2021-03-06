package com.dcyrillo.curmc.services;

import java.util.List;
import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dcyrillo.curmc.domain.Categoria;
import com.dcyrillo.curmc.domain.Cliente;
import com.dcyrillo.curmc.dto.CategoriaDto;
import com.dcyrillo.curmc.repositories.CategoriaRepository;

import com.dcyrillo.curmc.services.exception.ObjectNotFoundException;

@Service

public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public Categoria update(Categoria obj) {
	Categoria c=find(obj.getId());
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
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	public Categoria fromDto(CategoriaDto objDto) {
		return new Categoria(objDto.getId(),objDto.getNome());
	}
	private void updateData( Categoria c,Categoria obj) {
		c.setNome(obj.getNome());
		
		
	}
}
