/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.dominio;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Teresa Fernandes
 *
 */
@Entity
public class ConfiguracaoPdi implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="id_setor", nullable=false)
	private Setor setor;
	
	@Column(name="periodicidade_em_meses", nullable=false)
	private Integer periodicidadeEmMeses;
	
	private Boolean ativo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Integer getPeriodicidadeEmMeses() {
		return periodicidadeEmMeses;
	}

	public void setPeriodicidadeEmMeses(Integer periodicidadeEmMeses) {
		this.periodicidadeEmMeses = periodicidadeEmMeses;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@JsonProperty("setor")
	public void getSetor(Map<String,Object> mapSetor) {
		this.setor = null;
		if(mapSetor.containsKey("id")) {
			this.setor = new Setor();
	        this.setor.setId(((Integer)mapSetor.get("id")).longValue());
		}
    }

}