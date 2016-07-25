package tr.gen.usp.mac.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import tr.gen.usp.mac.entity.Mac;
import tr.gen.usp.mac.entity.MacOyuncusu;
import tr.gen.usp.mac.entity.Oyuncu;
import tr.gen.usp.mac.jsf.util.JsfUtil;
import tr.gen.usp.mac.jsf.util.EventType;
import tr.gen.usp.mac.jsf.util.JsfUtil.PersistAction;
import tr.gen.usp.mac.session.MacFacade;
import tr.gen.usp.mac.session.MacOyuncusuFacade;

@Named("macController")
@SessionScoped
public class MacController implements Serializable {

    @EJB
    private MacFacade macFacade;
    @EJB
    private MacOyuncusuFacade macOyuncusuFacade;
    @Inject
    private OlayController olay;
    private List<Mac> items = null;
    private List<MacOyuncusu> oyuncular = null;
    private Mac selected;
    private Oyuncu selectedOyuncu;
    private MacOyuncusu selectedMacOyuncusu;
    private Integer selectedMacOyuncusuId;
    @Inject
    OyuncuController oyuncuController;

    public MacOyuncusu getSelectedMacOyuncusu() {
        return selectedMacOyuncusu;
    }

    public void setSelectedMacOyuncusu(MacOyuncusu selectedMacOyuncusu) {
        this.selectedMacOyuncusu = selectedMacOyuncusu;
    }

    public Integer getSelectedMacOyuncusuId() {
        return selectedMacOyuncusuId;
    }

    public void setSelectedMacOyuncusuId(Integer id) {
        this.selectedMacOyuncusuId = id;
    }

    public Oyuncu getSelectedOyuncu() {
        return selectedOyuncu;
    }

    public void setSelectedOyuncu(Oyuncu selectedOyuncu) {
        this.selectedOyuncu = selectedOyuncu;
    }

    public MacController() {
    }

    public Mac getSelected() {
        return selected;
    }

    public void setSelected(Mac selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MacFacade getFacade() {
        return macFacade;
    }

    public Mac prepareCreate() {
        selected = new Mac();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/messages").getString("MacCreated"));
        olay.logEvent(EventType.MAC_EKLE, selected);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/messages").getString("MacUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/messages").getString("MacDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Mac> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/messages").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/messages").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Mac getMac(java.lang.Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Mac.class)
    public static class MacControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MacController controller = (MacController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "macController");
            return controller.getMac(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Mac) {
                Mac o = (Mac) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mac.class.getName()});
                return null;
            }
        }

    }

    public List<Oyuncu> completeList(String query) {
        List<Oyuncu> filteredOyuncular = new ArrayList<>();

        for (Oyuncu oyuncu : oyuncuController.getItems()) {
            if (oyuncu.getAd().toLowerCase(JsfUtil.TR_LOCALE).contains(query)) {
                filteredOyuncular.add(oyuncu);
            }
        }
        return filteredOyuncular;
    }

    public List<MacOyuncusu> getOyuncular() {
        return oyuncular;
    }

    public void setOyuncular(List<MacOyuncusu> oyuncular) {
        this.oyuncular = oyuncular;
    }

    public void onMacSelect(SelectEvent event) {
        if (selected != null) {
            this.oyuncular = macOyuncusuFacade.getAllMacOyunculariByMacId(selected.getId());
        } else {
            this.oyuncular = null;
        }
    }

    public void onOyuncuSelect(SelectEvent event) {
        MacOyuncusu macOyuncusu = new MacOyuncusu();
        macOyuncusu.setEklenmeZamani(new Date(System.currentTimeMillis()));
        macOyuncusu.setMac(selected);
        macOyuncusu.setOyuncu((Oyuncu) event.getObject());

        try {
            macOyuncusuFacade.create(macOyuncusu);
            onMacSelect(null);
//            selected.getMacOyuncusuCollection().add(macOyuncusu);
            olay.logEvent(EventType.MACA_OYUNCU_EKLE, selected, macOyuncusu.getOyuncu());
            JsfUtil.addSuccessMessage(macOyuncusu.getOyuncu() + " kadroya eklendi");
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
            }
            if (t.getMessage() != null && t.getMessage().contains("Duplicate entry")) {
                JsfUtil.addErrorMessage("Oyuncu zaten kadroda mevcut.");
            } else {
                JsfUtil.addErrorMessage("Oyuncu eklenemedi");
                Logger.getLogger(MacController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        selectedOyuncu = null;
    }

    public void kadrodanOyuncuCikar(ActionEvent event) {
        System.out.println(selectedMacOyuncusuId);

        Oyuncu oyuncu = selectedMacOyuncusu.getOyuncu();
        if (macOyuncusuFacade.find(selectedMacOyuncusu.getId()) != null) {
            macOyuncusuFacade.remove(selectedMacOyuncusu);
            onMacSelect(null);
            olay.logEvent(EventType.MACTAN_OYUNCU_SIL, selected, oyuncu);
            JsfUtil.addSuccessMessage(oyuncu.getAd() + " kadrodan çıkarıldı");
        } else {
            JsfUtil.addErrorMessage("Oyuncu zaten çıkarılmış");
            onMacSelect(null);
        }
        selectedMacOyuncusu = null;
    }

}
