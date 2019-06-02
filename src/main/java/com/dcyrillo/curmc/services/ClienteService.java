package com.dcyrillo.curmc.services;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcyrillo.curmc.domain.Categoria;
import com.dcyrillo.curmc.domain.Cidade;
import com.dcyrillo.curmc.domain.Cliente;
import com.dcyrillo.curmc.domain.Endereco;
import com.dcyrillo.curmc.domain.enums.TipoCliente;
import com.dcyrillo.curmc.dto.ClienteDto;
import com.dcyrillo.curmc.dto.ClienteNewDTO;
import com.dcyrillo.curmc.repositories.CidadeRepository;
import com.dcyrillo.curmc.repositories.ClienteRepository;
import com.dcyrillo.curmc.repositories.EnderecoRepository;
import com.dcyrillo.curmc.services.exception.ObjectNotFoundException;



@Service
public class ClienteService {
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository repo;
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}		
	public Cliente update(Cliente obj) {
		Cliente c=find(obj.getId());
		updateData(c,obj);
		return obj;
		
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
	public Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
}

	
}

	

