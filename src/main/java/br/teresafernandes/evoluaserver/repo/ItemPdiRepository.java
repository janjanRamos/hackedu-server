/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.teresafernandes.evoluaserver.dominio.ItemPdi;
import br.teresafernandes.evoluaserver.dominio.Pdi;

/**
 * @author Teresa Fernandes
 *
 */
@Repository
public interface ItemPdiRepository extends AbstractRepository<ItemPdi>{

	List<ItemPdi> findByPdiAndAtivoIsTrue(Pdi pdi);
}
