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
public class Setor implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa gestor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pessoa getGestor() {
		return gestor;
	}

	public void setGestor(Pessoa gestor) {
		this.gestor = gestor;
	}
	
	@JsonProperty("gestor")
	public void getGestor(Map<String,Object> mapGestor) {
		gestor = null;
		if(mapGestor.containsKey("id")) {
			gestor = new Pessoa();
	        gestor.setId(((Integer)mapGestor.get("id")).longValue());
		}
    }

}