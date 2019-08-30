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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Teresa Fernandes
 *
 */
@Entity(name="usuario")
public class Usuario implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@Column(name="login", nullable=false)
	private String login;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	@Transient
	private String confirmacaoSenha;
	
	@Column(name="ativo", nullable=false)
	private Boolean ativo = true;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	@JsonProperty("pessoa")
	public void getPessoa(Map<String,Object> mapPessoa) {
		this.pessoa = null;
		if(mapPessoa.containsKey("nome") || mapPessoa.containsKey("cpf") || mapPessoa.containsKey("email")) {
			this.pessoa = new Pessoa();
		}
		if(mapPessoa.containsKey("nome")) {
	        this.pessoa.setNome(((String)mapPessoa.get("nome")));
		}
		if(mapPessoa.containsKey("cpf")) {
	        this.pessoa.setCpf(((String)mapPessoa.get("cpf")));
		}
		if(mapPessoa.containsKey("email")) {
	        this.pessoa.setEmail(((String)mapPessoa.get("email")));
		}
    }
	
}