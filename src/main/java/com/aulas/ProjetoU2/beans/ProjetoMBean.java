package com.aulas.ProjetoU2.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.aulas.ProjetoU2.dao.ProjetoDAO;
import com.aulas.ProjetoU2.dominio.Projeto;
import com.aulas.ProjetoU2.util.ProjetoU2Exception;

@ManagedBean
@ViewScoped
public class ProjetoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8695948206122890215L;
	
	Projeto projeto = new Projeto();
	
	ProjetoDAO projetoDAO = new ProjetoDAO();
	
	private Integer projetoId;

	public Integer getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Integer projetoId) {
		this.projetoId = projetoId;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	//LISTAR 

		public List<Projeto> getLista() throws ProjetoU2Exception{
			return projetoDAO.listar();
		}
	
	//GRAVAR
	public void editar(Projeto projeto) {
		this.projeto = projeto;
	}
	
	
	public void gravar() throws ProjetoU2Exception {
		
		if(this.projeto.getId() == null) {
			projetoDAO.salvar(this.projeto);
		} else {
			projetoDAO.atualizar(this.projeto);
		}
		projeto = new Projeto();
	}
	
	public void atualizaProjeto(AjaxBehaviorEvent e) throws ProjetoU2Exception {
		this.projeto = projetoDAO.buscarId(projetoId);
		System.out.println(projetoId);
		System.out.println(this.getProjeto());
	}
	
	

}
