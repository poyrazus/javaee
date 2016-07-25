package tr.gen.usp.mac.jsf;

import java.io.Serializable;
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
import javax.inject.Named;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import tr.gen.usp.mac.entity.Saha;
import tr.gen.usp.mac.jsf.util.JsfUtil;
import tr.gen.usp.mac.jsf.util.JsfUtil.PersistAction;
import tr.gen.usp.mac.session.SahaFacade;

@Named("sahaController")
@SessionScoped
public class SahaController implements Serializable {

    @EJB
    private tr.gen.usp.mac.session.SahaFacade ejbFacade;
    private List<Saha> items = null;
    private Saha selected;

    public SahaController() {
    }

    public Saha getSelected() {
        return selected;
    }

    public void setSelected(Saha selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SahaFacade getFacade() {
        return ejbFacade;
    }

    public Saha prepareCreate() {
        selected = new Saha();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/messages").getString("SahaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/messages").getString("SahaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/messages").getString("SahaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Saha> getItems() {
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

    public Saha getSaha(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Saha> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Saha> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Saha.class)
    public static class SahaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SahaController controller = (SahaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sahaController");
            return controller.getSaha(getKey(value));
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
            if (object instanceof Saha) {
                Saha o = (Saha) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Saha.class.getName()});
                return null;
            }
        }

    }

    public MapModel fetchMapModel(Saha item){
        if(item.getKoordinat() == null)
            return null;
        String[] coords = item.getKoordinat().split(",");
        if(coords.length != 2)
            return null;
        
        MapModel model = new DefaultMapModel();
        LatLng coord = new LatLng(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
        model.addOverlay(new Marker(coord, item.getAd(), null, FacesContext.getCurrentInstance().getApplication().getResourceHandler().createResource("pin.png", "images").getRequestPath()));
        return model;
    }
}
