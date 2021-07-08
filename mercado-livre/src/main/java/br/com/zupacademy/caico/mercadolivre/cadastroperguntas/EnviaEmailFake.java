package br.com.zupacademy.caico.mercadolivre.cadastroperguntas;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EnviaEmailFake implements Email{

	@Override
	public void Envia(String from, String to, String assunto) {
		System.out.println("Enviando email...");
		System.out.println("De: " + from);
		System.out.println("Para: " + to);
		System.out.println("Assunto: " + assunto);
	}

}
