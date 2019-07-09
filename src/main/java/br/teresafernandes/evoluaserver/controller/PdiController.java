/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.Pdi;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.PdiRepository;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/pdi" })
public class PdiController extends AbstractController<Pdi>{

	PdiController(PdiRepository pdiPessoaRepository) {
		super(pdiPessoaRepository);
	}

	public void validarAntesSalvar(Pdi obj) throws ServiceBusinessException {
		if(ValidatorUtil.isEmpty(obj.getDescricao())) {
			addErro("Descrição: campo obrigatório.");
		}
		
		checarErros();
	}
}
