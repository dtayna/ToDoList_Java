package com.aulas.ProjetoU2.dominio;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@OneToOne
		@JoinColumn(name = "email_usuario")
		private Usuario usuario;
		
		@OneToOne
		@JoinColumn(name = "id_responsavel")
		private Responsavel responsavel;
		
		@OneToOne
		@JoinColumn(name = "id_projeto")
		private Projeto projeto;
		
		@Column(length = 500, nullable = false)
		private String descricao;
		
		@Temporal(TemporalType.DATE)
		private Date prazo;
		
		@Temporal(TemporalType.DATE)
		private Date data_conclusao;
		
		@Column
		private int prioridade;
	
		@Column
		private Boolean finalizado;
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinColumn(name = "id_anotacao")
		private Set<Anotacao> anotacoes;
		

		public Tarefa(Usuario usuario, Responsavel responsavel, Projeto projeto, String descricao, Date prazo,
				Date data_conclusao, int prioridade, Boolean finalizado, Set<Anotacao> anotacoes) {
			super();
			this.usuario = usuario;
			this.responsavel = responsavel;
			this.projeto = projeto;
			this.descricao = descricao;
			this.prazo = prazo;
			this.data_conclusao = data_conclusao;
			this.prioridade = prioridade;
			this.finalizado = finalizado;
			this.anotacoes = anotacoes;
		}

		public Tarefa() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Responsavel getResponsavel() {
			return responsavel;
		}

		public void setResponsavel(Responsavel responsavel) {
			this.responsavel = responsavel;
		}

		public Projeto getProjeto() {
			return projeto;
		}

		public void setProjeto(Projeto projeto) {
			this.projeto = projeto;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public Date getPrazo() {
			return prazo;
		}

		public void setPrazo(Date prazo) {
			this.prazo = prazo;
		}

		public Date getData_conclusao() {
			return data_conclusao;
		}

		public void setData_conclusao(Date data_conclusao) {
			this.data_conclusao = data_conclusao;
		}

		public int getPrioridade() {
			return prioridade;
		}

		public void setPrioridade(int prioridade) {
			this.prioridade = prioridade;
		}

		public Boolean getFinalizado() {
			return finalizado;
		}

		public void setFinalizado(Boolean finalizado) {
			this.finalizado = finalizado;
		}

		public Set<Anotacao> getAnotacoes() {
			return anotacoes;
		}

		public void setAnotacoes(Set<Anotacao> anotacoes) {
			this.anotacoes = anotacoes;
		}

		@Override
		public int hashCode() {
			return Objects.hash(anotacoes, data_conclusao, descricao, finalizado, id, prazo, prioridade, projeto,
					responsavel, usuario);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tarefa other = (Tarefa) obj;
			return Objects.equals(anotacoes, other.anotacoes) && Objects.equals(data_conclusao, other.data_conclusao)
					&& Objects.equals(descricao, other.descricao) && Objects.equals(finalizado, other.finalizado)
					&& Objects.equals(id, other.id) && Objects.equals(prazo, other.prazo)
					&& prioridade == other.prioridade && Objects.equals(projeto, other.projeto)
					&& Objects.equals(responsavel, other.responsavel) && Objects.equals(usuario, other.usuario);
		}


		
		
		
}
