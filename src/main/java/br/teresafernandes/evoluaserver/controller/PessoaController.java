/**
 * Data: 13 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.Pessoa;
import br.teresafernandes.evoluaserver.repo.PessoaRepository;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/pessoas" })
public class PessoaController {

	private PessoaRepository repository;

	PessoaController(PessoaRepository pessoaRepository) {
		this.repository = pessoaRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Pessoa create(@RequestBody Pessoa pessoa) {
		return repository.save(pessoa);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {
		return repository.findById(id).map(record -> {
			record.setNome(pessoa.getNome());
			record.setSetor(pessoa.getSetor());
			record.setCargo(pessoa.getCargo());
			Pessoa updated = repository.save(record);
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
