package br.com.zupacademy.caico.mercadolivre.validators;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.ProdutoRequest;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		ProdutoRequest request = (ProdutoRequest) target;
		Set<String> nomesIguais = request.buscarCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.reject("Características", null, "Olha, você tem características iguais: " + nomesIguais);
		}
		
	}

}
