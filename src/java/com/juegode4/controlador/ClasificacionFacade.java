/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juegode4.controlador;

import com.juegode4.modelo.Clasificacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tobby
 */
@Stateless
public class ClasificacionFacade extends AbstractFacade<Clasificacion> {

    @PersistenceContext(unitName = "juegode4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasificacionFacade() {
        super(Clasificacion.class);
    }
    
}
