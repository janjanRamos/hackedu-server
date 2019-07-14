/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.Cargo;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.CargoRepository;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/cargo" })
public class CargoController extends AbstractController<Cargo>{

	CargoController(CargoRepository cargoRepository) {
		super(cargoRepository);
	}

	public void validarAntesSalvar(Cargo obj) throws ServiceBusinessException {
		validarObrigatoriedade(obj.getNome(), "Nome");
		
		checarErros();
	}
}
