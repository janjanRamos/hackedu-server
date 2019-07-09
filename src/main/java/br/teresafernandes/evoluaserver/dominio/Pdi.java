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
@Entity(name="pdi_pessoa")
public class Pdi implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa", nullable=false)
	private Pessoa pessoa;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="data_cadastro", nullable=false)
	private Date dataCadastro;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@JsonProperty("pessoa")
	public void getPessoa(Map<String,Object> mapPessoa) {
		this.pessoa = null;
		if(mapPessoa.containsKey("id")) {
			this.pessoa = new Pessoa();
	        this.pessoa.setId(((Integer)mapPessoa.get("id")).longValue());
		}
    }
}