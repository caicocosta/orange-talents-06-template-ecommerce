package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.caico.mercadolivre.cadastroperguntas.Email;
import br.com.zupacademy.caico.mercadolivre.efetuacompra.Compras;
import br.com.zupacademy.caico.mercadolivre.exception.Exceptions;

@RestController
public class FinalizaCompraController {

	@PersistenceContext
	private EntityManager entityManager;
	
	//1
	@Autowired
	private NotaFiscal nota;

	//1
	@Autowired
	private RakingVendedores raking;
	
	//1
	@Autowired
	private Email emailFake;
	
	@PostMapping("/retorno-pagseguro")
	@Transactional
	public void finalizaCompra(@RequestBody @Valid RetornoPagSeguroRequest retornoPagSeguro) {
		
		Compras compra = entityManager.find(Compras.class, retornoPagSeguro.getIdCompra());
		
		//1
		if(compra == null) {
			throw new Exceptions("Compra não cadastrada");	
		}
		
		compra.addTransacao(retornoPagSeguro, entityManager);
		
		entityManager.merge(compra);
		
		//1
		if(compra.compraConcluida()) {
			processaCompra(compra);
		}

		
		
	}
	
	@PostMapping("/retorno-paypal")
	@Transactional
	public void finalizaCompra(@RequestBody @Valid RetornoPayPalRequest retornoPayPal) {
		
		Compras compra = entityManager.find(Compras.class, retornoPayPal.getIdCompra());
		
		//1
		if(compra == null) {
			throw new Exceptions("Compra não cadastrada");	
		}
		
		compra.addTransacao(retornoPayPal, entityManager);
		
		entityManager.merge(compra);
		
		//1
		if(compra.compraConcluida()) {
			processaCompra(compra);
		}
		
		
	}	
	
	private void processaCompra(Compras compra) {
		nota.processaNota(compra);
		raking.processarRaking(compra);
		
		String assunto = "Compra finalizada com sucesso: " 
		         + compra.getProduto().getNome();

        emailFake.Envia("vendedor@vendedor.com", compra.getUsuario().getLogin(), assunto);
	}
	
}
