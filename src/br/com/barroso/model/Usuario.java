package br.com.barroso.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name="sequence", sequenceName="usuario_sequence", allocationSize=1)
public class Usuario {

	@Id	@GeneratedValue(strategy=GenerationType.AUTO, generator="sequence")
	private Long id;
	
	@NotNull @Size(max = 255)
	private String nome;
	
	@NotNull @Size(max = 32)
	private String login;
	
	@NotNull @Size(max = 32)
	private String senha;
	
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
	
}