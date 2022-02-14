package com.aulas.ProjetoU2.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.aulas.ProjetoU2.dao.AnotacaoDAO;
import com.aulas.ProjetoU2.dao.TarefaDAO;
import com.aulas.ProjetoU2.dominio.Anotacao;
import com.aulas.ProjetoU2.dominio.Tarefa;
import com.aulas.ProjetoU2.util.ProjetoU2Exception;

@ManagedBean
@ViewScoped
public class AnotacaoMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2585207154730468010L;
	
	Anotacao anotacao = new Anotacao();
	AnotacaoDAO anotacaoDAO = new AnotacaoDAO();
	
	private TarefaDAO tarefaDAO = new TarefaDAO();
	private Integer tarefaId;
	private Tarefa tarefa;
	
	

	public Integer getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(Integer tarefaId) {
		this.tarefaId = tarefaId;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void atualizaTarefa(AjaxBehaviorEvent e) throws ProjetoU2Exception {
		this.tarefa = tarefaDAO.buscarId(tarefaId);
	}
	
	Date date = new Date();
	
		//APAGAR
		public void apagar(Anotacao anotacao) throws ProjetoU2Exception {
			anotacaoDAO.excluir(anotacao);
		}
		
		//LISTAR
		public List<Anotacao> getLista() throws ProjetoU2Exception{
			return anotacaoDAO.listar();
		}
		
		//GRAVAR
		public void editar(Anotacao anotacao) throws ProjetoU2Exception{
			atualizaEditar(anotacao);
			this.anotacao = anotacao;
		}
		
		public void atualizaEditar(Anotacao anotacao) throws ProjetoU2Exception {
			tarefaId = anotacao.getTarefa().getId();
			
		}
		
		public void gravar() throws ProjetoU2Exception {
			anotacao.setData(this.date);
			System.out.println("data " +date);
			for (Tarefa p : tarefaDAO.listar()) {
				if (p.getId() == tarefaId) {
					this.anotacao.setTarefa(p);
						break;
					}
				}
			if(this.anotacao.getId() == null) {
				anotacaoDAO.salvar(this.anotacao);
			} else {
				anotacaoDAO.atualizar(this.anotacao);
			}
			tarefaId = null;
			anotacao = new Anotacao();
		}

		public Anotacao getAnotacao() {
			return anotacao;
		}

		public void setAnotacao(Anotacao anotacao) {
			this.anotacao = anotacao;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
	
	

}
