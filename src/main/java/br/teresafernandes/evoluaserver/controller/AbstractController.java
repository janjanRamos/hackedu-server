/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.teresafernandes.evoluaserver.dominio.EntidadePersistente;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
public abstract class AbstractController<T extends EntidadePersistente> {

	protected JpaRepository<T, Long> repository;

	AbstractController(JpaRepository<T, Long> repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<T>> findAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<T> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody T obj) {
		return ResponseEntity.ok().body(repository.save(obj));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<T> update(@PathVariable("id") long id, @RequestBody T obj) {
		return repository.findById(id).map(record -> {
			obj.setId(record.getId());
			T updated = repository.save(obj);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
