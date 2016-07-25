/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gen.usp.mac.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import tr.gen.usp.mac.entity.Oyuncu;

/**
 *
 * @author sitki.poyraz
 */
@Stateless
public class OyuncuFacade extends AbstractFacade<Oyuncu> {
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OyuncuFacade() {
        super(Oyuncu.class);
    }
    
    public List<Oyuncu> getAktifOyuncular(){
        return getEntityManager().createNamedQuery("Oyuncu.findAktif").getResultList();
    }
    
}
