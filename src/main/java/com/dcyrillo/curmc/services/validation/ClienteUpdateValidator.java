package com.dcyrillo.curmc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.dcyrillo.curmc.domain.Cliente;
import com.dcyrillo.curmc.dto.ClienteDto;
import com.dcyrillo.curmc.repositories.ClienteRepository;
import com.dcyrillo.curmc.resorces.Exception.FieldMessage;

 
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {


	@Autowired
	private  HttpServletRequest request;

	
	@Autowired
	private ClienteRepository repo;
	@Override
	
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {
		Map <String ,String>map= (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	Integer uriId = Integer.parseInt(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();
	Cliente aux =repo.findByEmail(objDto.getEmail());
	if(aux !=null) {
		list.add(new FieldMessage("email","Email já existente"));
	}
		
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}


}