package com.aulas.ProjetoU2.dominio;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity  
@Table(name = "usuario")
public class Usuario implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(length = 50, nullable = false)
		private String email;
		
		@Column(length = 50, nullable = false)
		private String nome;
		
		@Column(length = 15, nullable = false)
		private String telefone;

		@Column(length = 50, nullable = false)
		private String senha;
		
		@OneToOne
		@JoinColumn(name = "id_tarefa")
		private Tarefa tarefa;
		


		public Usuario(String email, String nome, String telefone, String senha) {
			super();
			this.email = email;
			this.nome = nome;
			this.telefone = telefone;
			this.senha = senha;
		}



		public Usuario() {
			super();
			// TODO Auto-generated constructor stub
		}



		public Integer getId() {
			return id;
		}



		public void setId(Integer id) {
			this.id = id;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getNome() {
			return nome;
		}



		public void setNome(String nome) {
			this.nome = nome;
		}



		public String getTelefone() {
			return telefone;
		}



		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}



		public String getSenha() {
			return senha;
		}



		public void setSenha(String senha) {
			this.senha = senha;
		}



		@Override
		public int hashCode() {
			return Objects.hash(email, id, nome, senha, tarefa, telefone);
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Usuario other = (Usuario) obj;
			return Objects.equals(email, other.email) && Objects.equals(id, other.id)
					&& Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha)
					&& Objects.equals(tarefa, other.tarefa) && Objects.equals(telefone, other.telefone);
		}



	
		
		
		
}
