package br.teresafernandes.evoluaserver.dominio;

/**
 * @author Teresa Fernandes
 * */

public interface EntidadePersistente {
	public Long getId();
	public void setId(Long id);
	public Boolean getAtivo();
	public void setAtivo(Boolean ativo);
}
