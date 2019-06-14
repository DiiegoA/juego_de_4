/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juegode4.controlador;

import com.juegode4.controlador.util.JsfUtil;
import com.juegode4.modelo.Usuario;
import com.juegode4.modelo.grafo.Arista;
import com.juegode4.modelo.grafo.Ficha;
import com.juegode4.modelo.grafo.Grafo;
import com.juegode4.modelo.grafo.Vertice;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author tobby
 */
@Named(value = "juego4AppBean")
@ApplicationScoped
public class Juego4AppBean {

    private int altoTablero = 6;
    private int anchoTablero = 7;
    private byte distancia = 5;
    private DefaultDiagramModel model;
    private Grafo tablero = new Grafo();
    private boolean  estadoJuego=false;
    private Date fechaSistema; 
    private List botones;

    public List getBotones() {
        return botones;
    }

    public void setBotones(List botones) {
        this.botones = botones;
    }
    
    public Juego4AppBean() {
        botones= new ArrayList<>();
    }

    public Date getFechaSistema() {
        return new Date();
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }
    
    public boolean isEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(boolean estadoJuego) {
        this.estadoJuego = estadoJuego;
    }
    

    @PostConstruct
    private void pintarTablero() {
        
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);

        botonJuego();
        
        
        int x = 3;
        int y = 3;
        String color = "Blanco";
        String styleColor = "ui-diagram-element-tablero-blanco";
       
        
        for (int k = 1; k <=6; k++) {
           
        
        for (int i = 1; i <= (altoTablero) ; i++) {
                    
            for (int j = 1; j <= (anchoTablero); j++) {
                Ficha ficha=new Ficha(color, i, k);
                tablero.adicionarVertice(ficha);
                Element ceo = new Element(tablero.getVertices().size(), x + "em", y + "em");
                ceo.setDraggable(false);
                ceo.setStyleClass(styleColor);
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.CENTER));
                model.addElement(ceo);
                
                x = x + distancia;
               }
                x=3;
                y = y + distancia;
                
                }
             }
        
        
        llenarAristas();

        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#131DEB', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#131DEB'}");
        model.setDefaultConnector(connector);
        
                
        
        
        
        
        //recorrer aristas
        for (Arista arista : tablero.getAristas()) {
            Element origen = model.getElements().get(arista.getOrigen() - 1);
            Element destino = model.getElements().get(arista.getDestino() - 1);
            model.connect(new Connection(origen.getEndPoints().get(0), destino.getEndPoints().get(0)));
            
               
        }
        
        
        
    }

    
    public void llenarAristas() {
         
        for (Vertice vertice : tablero.getVertices()) {
            
            if (vertice.getId()% anchoTablero != 0) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + 1, 2);
            }
            if (vertice.getFicha().getNivel()< (altoTablero)) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + anchoTablero, 1);
                
            }
              if (vertice.getFicha().getNivel()< (altoTablero) && (vertice.getId()% anchoTablero != 0)) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + anchoTablero + 1, 1);
              }
            if (vertice.getFicha().getNivel()< (altoTablero) && vertice.getId() > ((vertice.getFicha().getNivel() 
                 * anchoTablero) - anchoTablero) + 1) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + anchoTablero - 1, 1);
            }

        }
    }
    public int getAltoTablero() {
        return altoTablero;
    }

    public void setAltoTablero(int altoTablero) {
        this.altoTablero = altoTablero;
    }

    public int getAnchoTablero() {
        return anchoTablero;
    }

    public void setAnchoTablero(int anchoTablero) {
        this.anchoTablero = anchoTablero;
    }

    public byte getDistancia() {
        return distancia;
    }

    public void setDistancia(byte distancia) {
        this.distancia = distancia;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }
    
    
   public void finTurno(){
   
//   _cont++;
   
   
   }
    
    
    
    public void activarJuego()
    {
        estadoJuego=true;
        JsfUtil.addSuccessMessage("Se ha habilitado el juego");
    }

    private void botonJuego(){
    
        for (int i = 1; i <= anchoTablero; i++) {
            botones.add(i);
            
        }
    }
    
    public void jugada(int numColumna,Usuario idJugador){
    
        while (numColumna+anchoTablero<=tablero.getVertices().size()) {
            if(model.getElements().get(numColumna+anchoTablero-1).getStyleClass().toString().compareTo("ui-diagram-element-tablero-blanco")==0){
                numColumna=numColumna+anchoTablero;
            }else{
                break;
            }
        }
        
        String nombre = idJugador.getJugador().getNombre();
        
        model.getElements().get(numColumna-1).setStyleClass("ui-diagram-element-tablero-" + idJugador.getColor());
    }
}

