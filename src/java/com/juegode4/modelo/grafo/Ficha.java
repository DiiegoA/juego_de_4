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
public class Ficha {
    
    private String color;
    private int nivel;
    private int tablero;

    public Ficha(String color, int nivel,int tablero ){
        this.color = color;
        this.nivel = nivel;
        this.tablero = tablero;
        
    }

  

    public String getColor() {
        return color;
    }

    public int getTablero() {
        return tablero;
    }

    public void setTablero(int tablero) {
        this.tablero = tablero;
    }

    
    
    public void setColor(String color) {
        this.color = color;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}
