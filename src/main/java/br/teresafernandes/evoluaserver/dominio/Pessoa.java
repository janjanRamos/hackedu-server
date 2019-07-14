/**
 * Data: 13 de jun de 2019
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
public class Pessoa implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_setor", nullable=false)
	private Setor setor;
	
	@ManyToOne
	@JoinColumn(name="id_cargo", nullable=false)
	private Cargo cargo;
	
	private Boolean gestor;
	
	@Column(name="ativo", nullable=false)
	private Boolean ativo = true;
	
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

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public Boolean getGestor() {
		return gestor;
	}

	public void setGestor(Boolean gestor) {
		this.gestor = gestor;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@JsonProperty("setor")
	public void getGestor(Map<String,Object> mapGestor) {
		this.setor = null;
		if(mapGestor.containsKey("id")) {
			this.setor = new Setor();
	        this.setor.setId(((Integer)mapGestor.get("id")).longValue());
		}
    }
	
	@JsonProperty("cargo")
	public void getCargo(Map<String,Object> mapCargo) {
		this.cargo = null;
		if(mapCargo.containsKey("id")) {
			this.cargo = new Cargo();
	        this.cargo.setId(((Integer)mapCargo.get("id")).longValue());
		}
    }

}