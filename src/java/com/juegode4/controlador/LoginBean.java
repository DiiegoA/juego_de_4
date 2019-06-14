/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juegode4.controlador;

import com.juegode4.controlador.util.FacesUtils;
import com.juegode4.controlador.util.JsfUtil;
import com.juegode4.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;



import javax.inject.Named;



/**
 *
 * @author tobby
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{

    private Usuario usuario;
    @EJB   
    private UsuarioFacade usuarioFacade;   
       
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public LoginBean() {
    }
    @PostConstruct
    private void inicializar()
    {
        usuario= new Usuario();
    }
    
    public String ingresar()
    {       
    
       Usuario usuarioEncontrado=usuarioFacade.find(usuario.getCorreo());
        if(usuarioEncontrado != null)
        {
            if(usuario.getContrasena().compareTo(usuarioEncontrado.getContrasena())==0)
            {
                ControladorJuegoDe4 contJuego= (ControladorJuegoDe4) FacesUtils.getManagedBean("controladorJuegoDe4");
                contJuego.setUsuario(usuarioEncontrado);
                if(usuarioEncontrado.getTipoUsuario().getCodigo()==1)
                {
                    return "ingresar";
                }    
                else
                {
                    return "jugar";
                }
                
                
                
            }
            JsfUtil.addErrorMessage("Contraseña errada");
        }
        else
        {
            JsfUtil.addErrorMessage("El correo ingresado no existe");
        }
        return null;
    }
}
