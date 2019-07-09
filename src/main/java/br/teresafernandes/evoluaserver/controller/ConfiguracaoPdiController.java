/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.ConfiguracaoPdi;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.ConfiguracaoPdiRepository;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/configuracaoPdi" })
public class ConfiguracaoPdiController extends AbstractController<ConfiguracaoPdi>{

	ConfiguracaoPdiController(ConfiguracaoPdiRepository pdiRepository) {
		super(pdiRepository);
	}

	public void validarAntesSalvar(ConfiguracaoPdi obj) throws ServiceBusinessException {
		if(ValidatorUtil.isEmpty(obj.getDescricao())) {
			addErro("Descrição: campo obrigatório.");
		}
		
		checarErros();
	}
}
