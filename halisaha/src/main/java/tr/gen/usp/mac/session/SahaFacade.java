/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gen.usp.mac.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tr.gen.usp.mac.entity.Saha;

/**
 *
 * @author sitki.poyraz
 */
@Stateless
public class SahaFacade extends AbstractFacade<Saha> {
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SahaFacade() {
        super(Saha.class);
    }
    
}
