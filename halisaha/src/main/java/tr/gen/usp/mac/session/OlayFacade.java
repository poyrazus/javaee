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
import javax.persistence.criteria.Root;
import tr.gen.usp.mac.entity.Olay;

/**
 *
 * @author sitki.poyraz
 */
@Stateless
public class OlayFacade extends AbstractFacade<Olay> {
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OlayFacade() {
        super(Olay.class);
    }   
    
    public List<Olay> findLastResultsOrderByDate(int count) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery(Olay.class);
        Root hobbyRoot = criteriaQuery.from(Olay.class);
        criteriaQuery.orderBy(cb.desc(hobbyRoot.get("eklenmeZamani")));
        return em.createQuery(criteriaQuery).setMaxResults(count).getResultList();
    }
}
