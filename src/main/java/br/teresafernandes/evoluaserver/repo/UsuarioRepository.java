/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.repo;

import org.springframework.stereotype.Repository;

import br.teresafernandes.evoluaserver.dominio.Usuario;

/**
 * @author Teresa Fernandes
 *
 */
@Repository
public interface UsuarioRepository extends AbstractRepository<Usuario>{
	Usuario findFirstByLoginAndAtivoIsTrue(String login);
}
