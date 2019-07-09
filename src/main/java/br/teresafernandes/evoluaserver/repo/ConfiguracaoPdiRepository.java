/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.teresafernandes.evoluaserver.dominio.ConfiguracaoPdi;

/**
 * @author Teresa Fernandes
 *
 */
@Repository
public interface ConfiguracaoPdiRepository extends JpaRepository<ConfiguracaoPdi, Long>{

}
