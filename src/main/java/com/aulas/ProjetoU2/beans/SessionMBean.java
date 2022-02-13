package com.aulas.ProjetoU2.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.aulas.ProjetoU2.dominio.Usuario;


@Named
@SessionScoped
public class SessionMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Usuario usuario = null;

	public static Usuario getSession() {
		return usuario;
	}

	public static void setSession(Usuario usuario) {
		SessionMBean.usuario = usuario;
	}
	
	public static void destroySession() {
		SessionMBean.usuario = null;
	}
}
