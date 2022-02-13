package com.aulas.ProjetoU2.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.aulas.ProjetoU2.dao.ProjetoDAO;
import com.aulas.ProjetoU2.dao.ResponsavelDAO;
import com.aulas.ProjetoU2.dao.TarefaDAO;
import com.aulas.ProjetoU2.dominio.Projeto;
import com.aulas.ProjetoU2.dominio.Responsavel;
import com.aulas.ProjetoU2.dominio.Tarefa;
import com.aulas.ProjetoU2.dominio.Usuario;
import com.aulas.ProjetoU2.util.ProjetoU2Exception;


@ManagedBean
@ViewScoped
public class TarefaMBean implements Serializable {

	/**
	 * re
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	private Tarefa tarefa = new Tarefa();
	private Responsavel responsavel = new Responsavel();
	private Projeto projeto = new Projeto();
	
	private Integer projetoId = null;
	private Integer responsavelId = null;

	private TarefaDAO tarefaDAO = new TarefaDAO();
	private ProjetoDAO projetoDAO = new ProjetoDAO();
	private ResponsavelDAO responsavelDAO = new ResponsavelDAO();
	
	public Integer getProjetoId() {
		return projetoId;
	}
	public void setProjetoId(Integer projetoId) {
		this.projetoId = projetoId;
	}
	public ProjetoDAO getProjetoDAO() {
		return projetoDAO;
	}
	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
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
		System.out.println(this.responsavel);
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
		System.out.println(projeto);
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public TarefaDAO getTarefaDAO() {
		return tarefaDAO;
	}
	public void setTarefaDAO(TarefaDAO tarefaDAO) {
		this.tarefaDAO = tarefaDAO;
	}
	public void gravar() throws ProjetoU2Exception {
		for (Responsavel r : responsavelDAO.listar()) {
			if (r.getId() == responsavelId) {
				this.tarefa.setResponsavel(r);
				break;
			}
		}
		for (Projeto p : projetoDAO.listar()) {
		if (p.getId() == projetoId) {
			this.tarefa.setProjeto(p);
				break;
			}
		}
		tarefa.setUsuario(SessionMBean.getSession());

		if(this.tarefa.getId() == null) {
			tarefaDAO.salvar(tarefa);
		} else {
			tarefaDAO.atualizar(tarefa);
		}
		
		projetoId = null;
		responsavelId = null;
		tarefa = new Tarefa();
	}
	public void editar(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	public List<Tarefa> getLista() throws ProjetoU2Exception {
		return this.tarefaDAO.listar();
	}
	public void apagar(Tarefa tarefa) throws ProjetoU2Exception {
		tarefaDAO.excluir(tarefa);
	}
	public void atualizaProjeto(AjaxBehaviorEvent e) throws ProjetoU2Exception {
		this.projeto = projetoDAO.buscarId(projetoId);
		System.out.println(projetoId);
		System.out.println(this.getProjeto());
	}
	public Integer getResponsavelId() {
		return responsavelId;
	}
	public void setResponsavelId(Integer responsavelId) {
		this.responsavelId = responsavelId;
	}
	public ResponsavelDAO getResponsavelDAO() {
		return responsavelDAO;
	}
	public void setResponsavelDAO(ResponsavelDAO responsavelDAO) {
		this.responsavelDAO = responsavelDAO;
	}
	public void atualizaResponsavel(AjaxBehaviorEvent e) throws ProjetoU2Exception {
		this.responsavel = responsavelDAO.buscarId(responsavelId);
		System.out.println(this.getResponsavel());
	}
	
	public List<Projeto> getListaClientes() throws ProjetoU2Exception {
		return projetoDAO.listar();
	}
	
	//public void carregar() throws ProjetoU2Exception {
		//this.projetoId = this.tarefa.getProjeto().getId();
	//}
	
	
}
