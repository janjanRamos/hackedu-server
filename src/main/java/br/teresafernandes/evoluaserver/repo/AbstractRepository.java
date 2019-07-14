/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Teresa Fernandes
 *
 */
@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, Long>{
	List<T> findByAtivoIsTrue();
}
