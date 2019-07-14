/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.teresafernandes.evoluaserver.util.DataUtils;

/**
 * @author Teresa Fernandes
 *
 */
@Entity(name="pdi")
public class Pdi implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa", nullable=false)
	private Pessoa pessoa;
	
	@Column(name="observacoes")
	private String observacoes;
	
	@Column(name="data_cadastro", nullable=false)
	private Date dataCadastro = new Date();
	
	@Column(name="ativo", nullable=false)
	private Boolean ativo = true;
	
	@Transient
	private List<ItemPdi> itens;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<ItemPdi> getItens() {
		return itens;
	}

	public void setItens(List<ItemPdi> itens) {
		this.itens = itens;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@JsonProperty("pessoa")
	public void getPessoa(Map<String,Object> mapPessoa) {
		this.pessoa = null;
		if(mapPessoa.containsKey("id")) {
			this.pessoa = new Pessoa();
	        this.pessoa.setId(((Integer)mapPessoa.get("id")).longValue());
		}
    }
	@JsonProperty("itens")
	public void getItens(List<Map<String,Object>> mapItens) {
		this.itens = new ArrayList<ItemPdi>();
		ItemPdi item = null;
		for(Map<String, Object> obj : mapItens) {
			item = new ItemPdi();
			if(obj.containsKey("id")) {
		        item.setId(((Integer)obj.get("id")).longValue());
			}
			if(obj.containsKey("descricao")) {
				item.setDescricao((String) obj.get("descricao"));
			}
			if(obj.containsKey("dataPrevista")) {
				item.setDataPrevista(DataUtils.stringToData((String) obj.get("dataPrevista")));
			}
			if(obj.containsKey("dataRealizacao")) {
				item.setDataRealizacao(DataUtils.stringToData((String) obj.get("dataRealizacao")));
			}
	        this.itens.add(item);
		}
    }
}