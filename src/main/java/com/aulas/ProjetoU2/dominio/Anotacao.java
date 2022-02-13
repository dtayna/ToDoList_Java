package com.aulas.ProjetoU2.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "anotacao")
public class Anotacao implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Temporal(TemporalType.DATE)
		private Date data;
		
		@ManyToOne 
		private Tarefa tarefa;
		
		@Column(length = 500, nullable = false)
		private String descricao;
		
		@Column(length = 50, nullable = false)
		private String titulo;



		public Anotacao() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Anotacao(Date data, Tarefa tarefa, String descricao, String titulo) {
			super();
			this.data = data;
			this.tarefa = tarefa;
			this.descricao = descricao;
			this.titulo = titulo;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}

		public Tarefa getTarefa() {
			return tarefa;
		}

		public void setTarefa(Tarefa tarefa) {
			this.tarefa = tarefa;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		@Override
		public int hashCode() {
			return Objects.hash(data, descricao, id, tarefa, titulo);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Anotacao other = (Anotacao) obj;
			return Objects.equals(data, other.data) && Objects.equals(descricao, other.descricao)
					&& Objects.equals(id, other.id) && Objects.equals(tarefa, other.tarefa)
					&& Objects.equals(titulo, other.titulo);
		}
		
		
}
