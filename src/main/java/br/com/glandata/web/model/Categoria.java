package br.com.glandata.web.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

	public Categoria(String nome) {
		this.nome = nome;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@NotBlank
	@Size(max = 20)
	private String nome;

	@Getter
	@Setter
	@Size(max = 75)
	private String descricao;
	
	@Getter
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produto> produtos;
}
