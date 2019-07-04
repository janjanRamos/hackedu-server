/**
 * Data: 13 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.Pessoa;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.CargoRepository;
import br.teresafernandes.evoluaserver.repo.PessoaRepository;
import br.teresafernandes.evoluaserver.repo.SetorRepository;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/pessoa" })
public class PessoaController extends AbstractController<Pessoa>{

	@Autowired
	SetorRepository setorRepository;
	@Autowired
	CargoRepository cargoRepository;
	
	/**
	 * @param repository
	 */
	PessoaController(PessoaRepository repository) {
		super(repository);
	}
	
	public void validarAntesSalvar(Pessoa obj) throws ServiceBusinessException {
		if(ValidatorUtil.isEmpty(obj.getNome())) {
			addErro("Nome: campo obrigatório.");
		}
		if(ValidatorUtil.isEmpty(obj.getSetor())) {
			addErro("Setor: campo obrigatório.");
		}
		if(ValidatorUtil.isEmpty(obj.getCargo())) {
			addErro("Cargo: campo obrigatório.");
		}
		if(obj.getSetor() != null 
				&& !setorRepository.existsById(obj.getSetor().getId())) {
			addErro("Setor inválido.");
		}
		if(obj.getCargo() != null 
				&& !cargoRepository.existsById(obj.getCargo().getId())) {
			addErro("Cargo inválido.");
		}
		
		checarErros();
	}
}
