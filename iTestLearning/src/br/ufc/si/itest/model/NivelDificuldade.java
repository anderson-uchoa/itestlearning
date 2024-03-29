/**
 * 
 */
package br.ufc.si.itest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Virginia
 *
 */
@Entity
@Table(name="itest.nivel_dificuldade")
public class NivelDificuldade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@OneToMany(mappedBy="nivelDificuldade")
	private List<Projeto> projetos;
	
	public NivelDificuldade() {
		
	}
	
	public NivelDificuldade(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof NivelDificuldade)
			if(((NivelDificuldade)other).getId().equals(this.id))
				return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 89 * hash + this.id;
		return hash;
	}
	
}//fim da classe
