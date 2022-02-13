package com.aulas.ProjetoU2.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aulas.ProjetoU2.dao.ResponsavelDAO;
import com.aulas.ProjetoU2.dominio.Responsavel;
import com.aulas.ProjetoU2.util.ProjetoU2Exception;

@ManagedBean
@ViewScoped
public class ResponsavelMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 667248101800770767L;
	
	Responsavel responsavel = new Responsavel();
	
	ResponsavelDAO responsavelDAO = new ResponsavelDAO();

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public ResponsavelDAO getResponsavelDAO() {
		return responsavelDAO;
	}

	public void setResponsavelDAO(ResponsavelDAO responsavelDAO) {
		this.responsavelDAO = responsavelDAO;
	}
	
	//APAGAR
	public void apagar(Responsavel responsavel) throws ProjetoU2Exception {
		responsavelDAO.excluir(responsavel);
	}
	
	//LISTAR
	public List<Responsavel> getLista() throws ProjetoU2Exception{
		return responsavelDAO.listar();
	}
	
	//GRAVAR
	public void editar(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	
	
	public void gravar() throws ProjetoU2Exception {
		
		if(this.responsavel.getId() == null) {
			responsavelDAO.salvar(this.responsavel);
		} else {
			responsavelDAO.atualizar(this.responsavel);
		}
		responsavel = new Responsavel();
	}

}
