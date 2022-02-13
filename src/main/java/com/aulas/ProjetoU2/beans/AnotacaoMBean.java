package com.aulas.ProjetoU2.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aulas.ProjetoU2.dao.AnotacaoDAO;
import com.aulas.ProjetoU2.dominio.Anotacao;
import com.aulas.ProjetoU2.dominio.Responsavel;
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
		public void editar(Anotacao anotacao) {
			this.anotacao = anotacao;
		}
		
		
		public void gravar() throws ProjetoU2Exception {
			anotacao.setData(this.date);
			System.out.println("data " +date);
			if(this.anotacao.getId() == null) {
				anotacaoDAO.salvar(this.anotacao);
			} else {
				anotacaoDAO.atualizar(this.anotacao);
			}
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
