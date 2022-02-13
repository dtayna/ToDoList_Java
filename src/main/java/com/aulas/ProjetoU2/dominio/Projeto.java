package com.aulas.ProjetoU2.dominio;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity  //a classe vai representar uma tabela no banco
@Table(name = "projeto")
public class Projeto implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(length = 40, nullable = false)
		private String nome;
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinColumn(name = "id")
		private Set<Tarefa> tarefas;

		public Set<Tarefa> getTarefas() {
			return tarefas;
		}

		public void setTarefas(Set<Tarefa> tarefas) {
			this.tarefas = tarefas;
		}

		public Projeto(String nome) {
			super();
			this.nome = nome;
		}

		public Projeto() {
			super();
			// TODO Auto-generated constructor stub
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

		@Override
		public int hashCode() {
			return Objects.hash(id, nome);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Projeto other = (Projeto) obj;
			return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
		}
		
		
		
		
		
}
