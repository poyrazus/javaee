/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gen.usp.mac.jsf;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

@Named("sessionController")
@SessionScoped
public class SessionController implements Serializable {

    private String userName;
    private boolean olaylarCollapsed;
    private int activeTabIndex;
    @PostConstruct
    public void init(){
        userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public int getActiveTabIndex() {
        return activeTabIndex;
    }

    public void setActiveTabIndex(int activeTabIndex) {
        this.activeTabIndex = activeTabIndex;
    }
    
    public boolean isOlaylarCollapsed() {
        return olaylarCollapsed;
    }

    public void setOlaylarCollapsed(boolean olaylarCollapsed) {
        this.olaylarCollapsed = olaylarCollapsed;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void olaylarFieldsetToggled(ToggleEvent event){
        olaylarCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void activeTabChanged(final TabChangeEvent event) {
        this.setActiveTabIndex(((TabView) event.getComponent()).getActiveIndex());
    }
}
