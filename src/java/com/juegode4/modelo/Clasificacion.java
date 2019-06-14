/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juegode4.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tobby
 */
@Entity
@Table(name = "clasificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificacion.findAll", query = "SELECT c FROM Clasificacion c"),
    @NamedQuery(name = "Clasificacion.findByTiemposg", query = "SELECT c FROM Clasificacion c WHERE c.tiemposg = :tiemposg"),
    @NamedQuery(name = "Clasificacion.findByTabla", query = "SELECT c FROM Clasificacion c WHERE c.tabla = :tabla"),
    @NamedQuery(name = "Clasificacion.findByJugador", query = "SELECT c FROM Clasificacion c WHERE c.jugador = :jugador")})
public class Clasificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiemposg")
    private int tiemposg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tabla")
    private int tabla;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "jugador")
    private String jugador;
    @JoinColumn(name = "jugador", referencedColumnName = "identificacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Jugador jugador1;

    public Clasificacion() {
    }

    public Clasificacion(String jugador) {
        this.jugador = jugador;
    }

    public Clasificacion(String jugador, int tiemposg, int tabla) {
        this.jugador = jugador;
        this.tiemposg = tiemposg;
        this.tabla = tabla;
    }

    public int getTiemposg() {
        return tiemposg;
    }

    public void setTiemposg(int tiemposg) {
        this.tiemposg = tiemposg;
    }

    public int getTabla() {
        return tabla;
    }

    public void setTabla(int tabla) {
        this.tabla = tabla;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jugador != null ? jugador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificacion)) {
            return false;
        }
        Clasificacion other = (Clasificacion) object;
        if ((this.jugador == null && other.jugador != null) || (this.jugador != null && !this.jugador.equals(other.jugador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.juegode4.modelo.Clasificacion[ jugador=" + jugador + " ]";
    }
    
}
