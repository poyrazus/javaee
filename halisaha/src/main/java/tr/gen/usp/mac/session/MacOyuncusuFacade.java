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
import tr.gen.usp.mac.entity.MacOyuncusu;

/**
 *
 * @author sitki.poyraz
 */
@Stateless
public class MacOyuncusuFacade extends AbstractFacade<MacOyuncusu> {
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MacOyuncusuFacade() {
        super(MacOyuncusu.class);
    }
    
    public List<MacOyuncusu> getAllMacOyunculariByMacId(Integer macId){
        return getEntityManager().createNamedQuery("MacOyuncusu.findByMacId", MacOyuncusu.class).setParameter("id", macId).getResultList();
    }
    
    public int remove(Integer id){
        return getEntityManager().createNamedQuery("MacOyuncusu.deleteById").setParameter("id", id).executeUpdate();
    }
    
}
