/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Setor obj) {
		try {
			obj.validar();
			if(obj.getGestor() != null 
					&& !pessoaRepository.existsById(obj.getGestor().getId())) {
				throw new ServiceBusinessException("Pessoa inválida");
			}
		}catch (ServiceBusinessException e) {
			return new ResponseEntity<Object>(e, e.getStatus());
		}
		
		return ResponseEntity.ok().body(repository.save(obj));
	}
}
