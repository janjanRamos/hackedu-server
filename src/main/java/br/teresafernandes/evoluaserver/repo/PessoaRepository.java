/**
 * Data: 13 de jun de 2019
 */
package br.teresafernandes.evoluaserver.repo;

import org.springframework.stereotype.Repository;

import br.teresafernandes.evoluaserver.dominio.Pessoa;

/**
 * @author Teresa Fernandes
 *
 */
@Repository
public interface PessoaRepository extends AbstractRepository<Pessoa>{
	Boolean existsByCpfAndAtivoIsTrue(String cpf);
	Boolean existsByEmailAndAtivoIsTrue(String email);
	Pessoa findFirstByEmailAndAtivoIsTrue(String email);
	Pessoa findFirstByCpfAndAtivoIsTrue(String cpf);
}
