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

@Entity
@Table(name = "responsavel")
public class Responsavel implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(length = 50, nullable = false)
		private String nome;
		
		@Column(length = 40, nullable = false)
		private String email;
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinColumn(name = "id_tarefa")
		private Set<Tarefa> tarefas;

		public Set<Tarefa> getTarefas() {
			return tarefas;
		}

		public void setTarefas(Set<Tarefa> tarefas) {
			this.tarefas = tarefas;
		}

		public Responsavel(String nome, String email) {
			super();
			this.nome = nome;
			this.email = email;
		}

		public Responsavel() {
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public int hashCode() {
			return Objects.hash(email, id, nome);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Responsavel other = (Responsavel) obj;
			return Objects.equals(email, other.email) && Objects.equals(id, other.id)
					&& Objects.equals(nome, other.nome);
		}
		
		

		
}
