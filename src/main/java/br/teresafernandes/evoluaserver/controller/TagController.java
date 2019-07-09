/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.Tag;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.TagRepository;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/tag" })
public class TagController extends AbstractController<Tag>{

	TagController(TagRepository tagRepository) {
		super(tagRepository);
	}

	public void validarAntesSalvar(Tag obj) throws ServiceBusinessException {
		if(ValidatorUtil.isEmpty(obj.getNome())) {
			addErro("Nome: campo obrigatório.");
		}
		
		checarErros();
	}
}
