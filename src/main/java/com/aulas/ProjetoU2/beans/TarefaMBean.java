package com.aulas.ProjetoU2.beans;

import java.io.Serializable;
import java.util.ArrayList;
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
	private Projeto projetof = new Projeto();
	
	private Integer projetoId = null;
	private Integer responsavelId = null;

	private TarefaDAO tarefaDAO = new TarefaDAO();
	private ProjetoDAO projetoDAO = new ProjetoDAO();
	private ResponsavelDAO responsavelDAO = new ResponsavelDAO();
	
	private Boolean filtro = false;
	private Integer projetofiltro = null;
	
	public Projeto getProjetof() {
		return projetof;
	}

	public void setProjetof(Projeto projetof) {
		this.projetof = projetof;
	}

	public Boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(Boolean filtro) {
		this.filtro = filtro;
	}

	public Integer getProjetofiltro() {
		return projetofiltro;
	}

	public void setProjetofiltro(Integer projetofiltro) {
		this.projetofiltro = projetofiltro;
	}

	public void ativafiltro() throws ProjetoU2Exception {
	
		this.filtro = true;
		System.out.println("Ativou:" +filtro);
	}
	
	public void desativafiltro() throws ProjetoU2Exception {
		this.filtro = false;
	}
	
	public void atualizaProjetoFiltro(AjaxBehaviorEvent e) throws ProjetoU2Exception {
		
		this.projetof = projetoDAO.buscarId(projetofiltro);
	}
	
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
			this.tarefa.setFinalizado(false);
			tarefaDAO.salvar(tarefa);
		} else {
			tarefaDAO.atualizar(tarefa);
		}
		
		projetoId = null;
		responsavelId = null;
		tarefa = new Tarefa();
	}
	public void editar(Tarefa tarefa) throws ProjetoU2Exception {
		atualizaEditar(tarefa);
		this.tarefa = tarefa;
	}
	
	public List<Tarefa> getLista() throws ProjetoU2Exception {
		return this.tarefaDAO.listar();
	}
	public void apagar(Tarefa tarefa) throws ProjetoU2Exception {
		tarefaDAO.excluir(tarefa);
		this.tarefa.setId(null);
	}
	public void atualizaProjeto(AjaxBehaviorEvent e) throws ProjetoU2Exception {
		this.projeto = projetoDAO.buscarId(projetoId);
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
	}
	
	public List<Projeto> getListaClientes() throws ProjetoU2Exception {
		return projetoDAO.listar();
	}
	
	public List<Tarefa> listaTarefasPendentes() throws ProjetoU2Exception {
		
		List<Tarefa> tarefasPendentes = new ArrayList<Tarefa>();
		if (this.filtro == false) {
			for (Tarefa t : tarefaDAO.listar()) {
				if (t.getFinalizado() != true) {
					tarefasPendentes.add(t);
				}
			}
		}else {
			System.out.println("tô listando certo");
			for (Tarefa t : tarefaDAO.listar()) {
				if(projetofiltro != null && t.getProjeto() != null) {
					System.out.println("oioi");
					
					if (t.getFinalizado() != true && projetofiltro == t.getProjeto().getId() ) {
						tarefasPendentes.add(t);
					}
				}
				
			}
			
		}
		
		return tarefasPendentes;
	}
	
	public List<Tarefa> listaTarefasConcluidas() throws ProjetoU2Exception {
		List<Tarefa> tarefasFinalizadas = new ArrayList<Tarefa>();
		if (this.filtro == false) {
		for (Tarefa t : tarefaDAO.listar()) {
			if (t.getFinalizado() == true) {
				tarefasFinalizadas.add(t);
			}
		}
		}else {
			System.out.println("tô listando certo");
			for (Tarefa t : tarefaDAO.listar()) {
				if(projetofiltro != null && t.getProjeto() != null) {
					System.out.println("oioi");
					
					if (t.getFinalizado() == true && projetofiltro == t.getProjeto().getId() ) {
						tarefasFinalizadas.add(t);
					}
				}
				
			}
		}
		
		return tarefasFinalizadas;
	}
	
	public void concluiTarefa(Tarefa tarefa) throws ProjetoU2Exception {
		if ( tarefa.getFinalizado() == true ) {
			tarefa.setFinalizado(false);			
		} else {
			tarefa.setFinalizado(true);
		}
		this.setTarefa(tarefa);
		this.tarefaDAO.atualizar(this.tarefa);
	}
	
	public void atualizaEditar(Tarefa tarefa) throws ProjetoU2Exception {
		projetoId = tarefa.getProjeto().getId();
		responsavelId = tarefa.getResponsavel().getId();
		
	}
}
