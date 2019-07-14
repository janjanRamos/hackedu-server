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
		validarObrigatoriedade(obj.getNome(), "Nome");
		
		checarErros();
	}
}
