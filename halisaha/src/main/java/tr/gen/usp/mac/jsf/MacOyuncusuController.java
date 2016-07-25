///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package tr.gen.usp.mac.jsf;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import tr.gen.usp.mac.entity.MacOyuncusu;
//import tr.gen.usp.mac.session.MacOyuncusuFacade;
//
//@Named("macOyuncusuController")
//@RequestScoped
//public class MacOyuncusuController implements Serializable {
//
//    @Inject
//    MacController macController;
//    @EJB
//    MacOyuncusuFacade macOyuncusuFacade;
//    List<MacOyuncusu> oyuncular;
//
//    public Collection<MacOyuncusu> getSelectedMacOyunculari() { 
////        if(oyuncular == null)
//            oyuncular = macOyuncusuFacade.getAllMacOyunculariByMacId(macController.getSelected().getId());
//        return oyuncular;
//    }
//}
