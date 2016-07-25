/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gen.usp.mac.jsf;

import java.text.MessageFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import tr.gen.usp.mac.entity.Olay;
import tr.gen.usp.mac.jsf.util.EventType;
import tr.gen.usp.mac.session.OlayFacade;

@Named("olayController")
@ApplicationScoped
public class OlayController {

    @EJB
    OlayFacade olayFacade;
    private List<Olay> events;
    @Inject
    AppController appController;

    @PostConstruct
    public void init() {
        events = olayFacade.findLastResultsOrderByDate(30);
    }

    public void logEvent(EventType type, Object... params) {
        Olay olay = new Olay(MessageFormat.format(type.getMessage(), params), type, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());

        switch (type) {
            case MAC_EKLE:
            case MAC_GUNCELLE:
            case MAC_IPTAL:
            case MACA_OYUNCU_EKLE:
            case MACTAN_OYUNCU_SIL:
                appController.sendEmail(olay.getMesaj());
                break;
        }

        olayFacade.create(olay);
        events.add(0, olay);
    }

    public List<Olay> getEvents() {
        return events;
    }

}
