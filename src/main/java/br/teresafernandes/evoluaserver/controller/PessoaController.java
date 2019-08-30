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
		
		validarObrigatoriedade(obj.getNome(), "Nome");
		validarObrigatoriedade(obj.getCpf(), "CPF");
		validarObrigatoriedade(obj.getEmail(), "Email");
		validarObrigatoriedade(obj.getSetor(), "Setor");
		validarObrigatoriedade(obj.getCargo(), "Cargo");
		
		if(obj.getSetor() != null 
				&& !setorRepository.existsById(obj.getSetor().getId())) {
			addErro("Setor inválido.");
		}
		if(obj.getCargo() != null 
				&& !cargoRepository.existsById(obj.getCargo().getId())) {
			addErro("Cargo inválido.");
		}
		
		if(((PessoaRepository) repository).existsByEmailAndAtivoIsTrue(obj.getEmail())) {
			addErro("Já existe um cadastro com este email.");
		}
		
		if(((PessoaRepository) repository).existsByCpfAndAtivoIsTrue(obj.getCpf())) {
			addErro("Já existe um cadastro com este CPF.");
		}
		
		checarErros();
	}
}
