/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.teresafernandes.evoluaserver.dominio.EntidadePersistente;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.AbstractRepository;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
public abstract class AbstractController<T extends EntidadePersistente> {

	protected AbstractRepository<T> repository;
	private List<String> listaErros;

	AbstractController(AbstractRepository<T> repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<T>> findAll() {
		limparErros();
		return ResponseEntity.ok().body(repository.findByAtivoIsTrue());
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<T> findById(@PathVariable long id) {
		limparErros();
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<? extends Object> create(@RequestBody T obj) {
		try {
			limparErros();
			validarAntesSalvar(obj);
			obj.setAtivo(true);
			obj = repository.save(obj);
			aposSalvar(obj);
			return ResponseEntity.ok().body(obj);
		}catch (ServiceBusinessException e) {
		    return new ResponseEntity<Object>(e, e.getStatus());
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<? extends Object> update(@PathVariable("id") long id, @RequestBody T obj) {
		limparErros();
		return repository.findById(id).map(record -> {
			try {
				obj.setId(record.getId());
				validarAntesSalvar(obj);
				T updated = repository.save(obj);
				aposSalvar(obj);
				return ResponseEntity.ok().body(updated);
			}catch (ServiceBusinessException e) {
			    return new ResponseEntity<Object>(e, e.getStatus());
			}
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(path = { "/inativar/{id}" })
	public ResponseEntity<?> inativar(@PathVariable("id") long id, @RequestBody T obj) {
		limparErros();
		return repository.findById(id).map(record -> {
			obj.setId(record.getId());
			obj.setAtivo(false);
			T updated = repository.save(obj);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		limparErros();
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	public abstract void validarAntesSalvar(T obj) throws ServiceBusinessException;
	public void aposSalvar(T obj) throws ServiceBusinessException{};
	
	public void addErro(String erro) {
		if(this.listaErros == null) {
			limparErros();
		}
		this.listaErros.add(erro);
	}
	
	public void validarObrigatoriedade(Object obj, String nome) {
		if(ValidatorUtil.isEmpty(obj)) {
			addErro(nome + ": campo obrigatório.");
		}
	}
	
	public void limparErros() {
		this.listaErros = new ArrayList<String>();
	}
	
	public boolean hasErros() {
		return ValidatorUtil.isNotEmpty(listaErros);
	}
	
	public void checarErros() throws ServiceBusinessException {
		if(hasErros()) {
			throw new ServiceBusinessException(this.listaErros);
		}
	}
}
