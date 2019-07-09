/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.teresafernandes.evoluaserver.dominio.ItemPdi;

/**
 * @author Teresa Fernandes
 *
 */
@Repository
public interface ItemPdiRepository extends JpaRepository<ItemPdi, Long>{

}
