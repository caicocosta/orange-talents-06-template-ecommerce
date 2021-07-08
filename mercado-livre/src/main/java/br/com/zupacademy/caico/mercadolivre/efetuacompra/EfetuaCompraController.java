package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.caico.mercadolivre.cadastroperguntas.Email;
import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import br.com.zupacademy.caico.mercadolivre.exception.Exceptions;

@RestController
public class EfetuaCompraController {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Pagamento pagamento;
	
	@Autowired
	private Email emailFake;
	
	@PostMapping("/compras")
	@Transactional
	public ResponseEntity<?> efetuaCompra(@RequestBody @Valid ComprasRequest comprasRequest, @AuthenticationPrincipal Usuarios usuario, UriComponentsBuilder uri) {
	
		Produtos produto = entityManager.find(Produtos.class, comprasRequest.getProduto_id());
		if (produto == null) {
			throw new Exceptions("Produto não cadastro no sistema!");
		}
		
		switch (comprasRequest.getGatewayPagamento()) {
		case PAYPAL:
			this.pagamento = new PagSeguro();
			break;
		case PAGSEGURO:
			this.pagamento = new PayPal();
			break;
		default:
			throw new Exceptions("Forma de pagamento inválida!");
		}
		
		String urlRetorno = comprasRequest.toModel(comprasRequest, entityManager, produto, usuario, pagamento, uri);
		
		String assunto = "Olá, estou interessado no produto: " 
				         + produto.getNome();
		
		emailFake.Envia(usuario.getLogin(), "vendedor@vendero.com", assunto);

		return ResponseEntity.status(302).body(urlRetorno);
	}
	
}
