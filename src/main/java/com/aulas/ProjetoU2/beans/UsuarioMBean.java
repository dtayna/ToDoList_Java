package com.aulas.ProjetoU2.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aulas.ProjetoU2.dao.UsuarioDAO;
import com.aulas.ProjetoU2.dominio.Usuario;
import com.aulas.ProjetoU2.util.ProjetoU2Exception;

@ManagedBean
@ViewScoped
public class UsuarioMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -215216843219863286L;

	
	Usuario usuario = new Usuario();

	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//GRAVAR
	public void editar(Usuario usuario) {
		this.usuario = usuario;
	}
		
		
	public String gravar() throws ProjetoU2Exception {
		if(this.usuario.getId() == null) {
			usuarioDAO.salvar(this.usuario);
		} else {
			usuarioDAO.atualizar(this.usuario);
		}
		usuario = new Usuario();
		return "login"; 
	}
	
	public String logar() throws ProjetoU2Exception {
		List<Usuario> usuarios = usuarioDAO.listar();
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(usuario.getEmail())) {
				if (u.getSenha().equals(usuario.getSenha())) {
					SessionMBean.setSession(u);
					return "tarefasForm";
				}
				
			}
		}
		return "";
	}

}
