/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.Setor;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.PessoaRepository;
import br.teresafernandes.evoluaserver.repo.SetorRepository;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/setor" })
public class SetorController extends AbstractController<Setor>{

	@Autowired
	PessoaRepository pessoaRepository;
	
	SetorController(SetorRepository setorRepository) {
		super(setorRepository);
	}

	public void validarAntesSalvar(Setor obj) throws ServiceBusinessException {
		if(ValidatorUtil.isEmpty(obj.getNome())) {
			addErro("Nome: campo obrigatório.");
		}
		if(obj.getGestor() != null 
				&& !pessoaRepository.existsById(obj.getGestor().getId())) {
			addErro("Gestor inválido.");
		}
		
		checarErros();
	}
}
