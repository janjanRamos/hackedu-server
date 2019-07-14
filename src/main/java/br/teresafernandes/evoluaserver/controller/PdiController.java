/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.ItemPdi;
import br.teresafernandes.evoluaserver.dominio.Pdi;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.ItemPdiRepository;
import br.teresafernandes.evoluaserver.repo.PdiRepository;
import br.teresafernandes.evoluaserver.repo.PessoaRepository;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/pdi" })
public class PdiController extends AbstractController<Pdi>{

	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	ItemPdiRepository itemRepository;
	
	PdiController(PdiRepository pdiPessoaRepository) {
		super(pdiPessoaRepository);
	}

	@GetMapping
	@Override
	public ResponseEntity<List<Pdi>> findAll() {
		limparErros();
		//lista apenas os registros ativos
		List<Pdi> lista = repository.findByAtivoIsTrue();
		for(Pdi pdi: lista) {
			//carrega os itens
			pdi.setItens(itemRepository.findByPdiAndAtivoIsTrue(pdi));
			for(ItemPdi item: pdi.getItens()) {
				//evita referência cruzada
				item.setPdi(null);
			}
		}
		return ResponseEntity.ok().body(lista);
	}
	
	public void validarAntesSalvar(Pdi obj) throws ServiceBusinessException {
		validarObrigatoriedade(obj.getPessoa(), "Pessoa");
		if(obj.getPessoa() != null 
				&& !pessoaRepository.existsById(obj.getPessoa().getId())) {
			addErro("Pessoa inválida.");
		}
		validarItens(obj);
		
		checarErros();
	}
	
	private void validarItens(Pdi obj) {
		if(ValidatorUtil.isEmpty(obj.getItens())) {
			addErro("Informe pelo menos um item.");
		}else {
			for(ItemPdi item: obj.getItens()) {
				validarObrigatoriedade(item.getDescricao(), "Descrição");
				validarObrigatoriedade(item.getDataPrevista(), "Data Prevista");
			}
		}
	}
	
	public void aposSalvar(Pdi obj) throws ServiceBusinessException{
		//remove os itens
		List<ItemPdi> itensBanco = itemRepository.findByPdiAndAtivoIsTrue(obj);
		for(ItemPdi item: itensBanco) {
			if(!obj.getItens().contains(item)) {
				item.setAtivo(false);
				itemRepository.save(item);
			}
		}
		//salva os itens do pdi
		for(ItemPdi item: obj.getItens()) {
			item.setPdi(obj);
			item.setAtivo(true);
			itemRepository.save(item);
		}
	}
}
