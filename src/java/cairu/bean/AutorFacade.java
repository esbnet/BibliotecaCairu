/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cairu.bean;

import cairu.model.Autor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author edmilson
 */
@Stateless
public class AutorFacade extends AbstractFacade<Autor> {

    @PersistenceContext(unitName = "BibliotecaCairuPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorFacade() {
        super(Autor.class);
    }
    
}
