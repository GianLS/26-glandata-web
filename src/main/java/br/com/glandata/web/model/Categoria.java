package br.com.glandata.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categorias")
@ToString
public class Categoria {

	public Categoria() {
	}

	public Categoria(Long id) {
		this.id = id;
	}

	public Categoria(String descricao) {
		this.nome = descricao;
	}

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	private String nome;
}
