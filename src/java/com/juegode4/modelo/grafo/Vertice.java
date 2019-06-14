/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juegode4.modelo.grafo;

/**
 *
 * @author tobby
 */
public class Vertice {
    
    private int id;
    private Ficha ficha;

    public Vertice(int id, Ficha ficha) {
        this.id = id;
        this.ficha = ficha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
    
}
