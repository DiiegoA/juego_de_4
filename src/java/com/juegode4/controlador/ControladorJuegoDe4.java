/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juegode4.controlador;

import com.juegode4.controlador.util.JsfUtil;
import com.juegode4.modelo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author tobby
 */
@Named(value = "controladorJuegoDe4")
@SessionScoped
public class ControladorJuegoDe4 implements Serializable {

    
    private Usuario usuario;
    @EJB
    private UsuarioFacade usuarioFacade;   
    private DefaultDiagramModel model;
    
    
    public ControladorJuegoDe4() {
    }
    @PostConstruct
    private void inicializar(){
    
    usuario = new Usuario();
    }
    
    public String ingresar(){
        
        Usuario usuarioEncontrado=usuarioFacade.find(usuario.getCorreo());
    
        if (usuarioEncontrado != null) {
            if (usuario.getContrasena().compareTo(usuarioEncontrado.getContrasena())==0) {
              return "ingresar";
            }
            JsfUtil.addErrorMessage("Contraseña Errada");
        }
        else{
        JsfUtil.addErrorMessage("El Correo Ingresado No Existe");
        
        }
       
    return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
            
      public DiagramModel getModel() {
        return model;
    }
}
