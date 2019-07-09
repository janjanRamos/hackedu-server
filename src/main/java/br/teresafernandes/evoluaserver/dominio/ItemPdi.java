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
@Entity(name="item_pdi")
public class ItemPdi implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_pdi_pessoa", nullable=false)
	private Pdi pdiPessoa;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="data_prevista")
	private String dataPrevista;
	
	@Column(name="data_realizacao")
	private String dataRealizacao;
	
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

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	@JsonProperty("pdiPessoa")
	public void getPdiPessoa(Map<String,Object> mapPdi) {
		this.pdiPessoa = null;
		if(mapPdi.containsKey("id")) {
			this.pdiPessoa = new Pdi();
	        this.pdiPessoa.setId(((Integer)mapPdi.get("id")).longValue());
		}
    }

}