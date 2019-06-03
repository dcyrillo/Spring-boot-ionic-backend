package com.dcyrillo.curmc.services.validation;

import java.util.ArrayList;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dcyrillo.curmc.domain.enums.TipoCliente;
import com.dcyrillo.curmc.dto.ClienteNewDTO;
import com.dcyrillo.curmc.resorces.Exception.FieldMessage;
import com.dcyrillo.curmc.services.validation.util.BR;

 
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())&& !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","	CNPJ inválido"));
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