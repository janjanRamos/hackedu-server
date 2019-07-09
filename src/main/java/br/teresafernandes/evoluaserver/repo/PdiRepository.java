/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.teresafernandes.evoluaserver.dominio.Pdi;

/**
 * @author Teresa Fernandes
 *
 */
@Repository
public interface PdiRepository extends JpaRepository<Pdi, Long>{

}
