package tr.gen.usp.mac.jsf;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.inject.Inject;
import javax.inject.Named;
import tr.gen.usp.mac.entity.Oyuncu;
import tr.gen.usp.mac.jsf.util.JsfUtil;
import tr.gen.usp.mac.jsf.util.JsfUtil.PersistAction;
import tr.gen.usp.mac.session.OyuncuFacade;

@Named("oyuncuController")
@SessionScoped
public class OyuncuController implements Serializable {

    @EJB
    private OyuncuFacade ejbFacade;
    @Inject
    AppController appController;
    private List<Oyuncu> items = null;
    private Oyuncu selected;

    public OyuncuController() {
    }

    public Oyuncu getSelected() {
        return selected;
    }

    public void setSelected(Oyuncu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OyuncuFacade getFacade() {
        return ejbFacade;
    }

    public Oyuncu prepareCreate() {
        selected = new Oyuncu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/messages").getString("OyuncuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/messages").getString("OyuncuUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/messages").getString("OyuncuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Oyuncu> getItems() {
        if (items == null) {
            items = getFacade().getAktifOyuncular();;
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
        appController.initializeEmailMembers();
        }
    }

    public Oyuncu getOyuncu(String value) {
        Integer id = JsfUtil.tryParse(value);
        if (id != null) {
            return getFacade().find(id);
        }

        return this.searchByName(value);
    }

    public List<Oyuncu> completeList(String query) {
        List<Oyuncu> filteredOyuncular2 = new ArrayList<>();

        for (Oyuncu oyuncu : this.items) {
            if (oyuncu.getAd().toLowerCase(JsfUtil.TR_LOCALE).contains(query)) {
                filteredOyuncular2.add(oyuncu);
            }
        }

        return filteredOyuncular2;
    }

    private Oyuncu searchByName(String name) {
        for (Oyuncu oyuncu : this.items) {
            if (oyuncu.getAd().toLowerCase(JsfUtil.TR_LOCALE).equals(name.toLowerCase(JsfUtil.TR_LOCALE))) {
                return oyuncu;
            }
        }

        return null;
    }

    @FacesConverter(forClass = Oyuncu.class)
    public static class OyuncuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OyuncuController controller = (OyuncuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "oyuncuController");
            return controller.getOyuncu(value);
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
            if (object instanceof Oyuncu) {
                Oyuncu o = (Oyuncu) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Oyuncu.class.getName()});
                return null;
            }
        }

    }
    @FacesConverter(value = "oyuncuConverter")
    public static class OyuncuConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OyuncuController controller = (OyuncuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "oyuncuController");
            return controller.getOyuncu(value);
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
            if (object instanceof Oyuncu) {
                Oyuncu o = (Oyuncu) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Oyuncu.class.getName()});
                return null;
            }
        }

    }    
}
