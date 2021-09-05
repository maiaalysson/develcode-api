package com.gabrielmaia.develcode.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("Codigo")
	private Long id;

	@NotBlank(message = "Nome, não pode ser vazio!")
	@Column(nullable = false)
	private String nome;

	@NotBlank
	@Column(name = "data_nascimento", nullable = false)
	@JsonProperty("Data de Nascimento")
	@NotBlank(message = "Data de Nascimento, não pode ser vazio!")
	private String dataNascimento;

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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "User [Codigo=" + id + ", Nome=" + nome + ", Data de Nascimento=" + dataNascimento + "]";
	}

}