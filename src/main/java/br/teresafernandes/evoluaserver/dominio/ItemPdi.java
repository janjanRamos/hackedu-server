/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.dominio;

import java.util.Date;
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
	@JoinColumn(name="id_pdi", nullable=false)
	private Pdi pdi;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="data_prevista")
	private Date dataPrevista;
	
	@Column(name="data_realizacao")
	private Date dataRealizacao;
	
	@Column(name="ativo", nullable=false)
	private Boolean ativo = true;
	
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

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Pdi getPdi() {
		return pdi;
	}

	public void setPdi(Pdi pdi) {
		this.pdi = pdi;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@JsonProperty("pdi")
	public void getPdi(Map<String,Object> mapPdi) {
		this.pdi = null;
		if(mapPdi.containsKey("id")) {
			this.pdi = new Pdi();
	        this.pdi.setId(((Integer)mapPdi.get("id")).longValue());
		}
    }

}