package br.com.zupacademy.caico.mercadolivre.cadastroprodutos;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake implements Uploader{

	/**
	 * 
	 * @param imagens
	 * @return Linkes para as imagens que foram enviadas
	 */
	public Set<String> envia(List<MultipartFile> imagens) {
		return imagens.stream()
					.map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
					.collect(Collectors.toSet());
	}

}
