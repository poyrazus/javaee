/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gen.usp.mac.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import tr.gen.usp.mac.jsf.util.EventType;

/**
 *
 * @author sitki.poyraz
 */
@Entity
@Table(name = "OLAY")
@NamedQueries({
    @NamedQuery(name = "Olay.findAll", query = "SELECT o FROM Olay o"),
    @NamedQuery(name = "Olay.findById", query = "SELECT o FROM Olay o WHERE o.id = :id")})
public class Olay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "olay_generator")
    @TableGenerator(allocationSize = 1,
                    name = "olay_generator",
                    table = "ID_SEQUENCE",
                    pkColumnName = "NAME",
                    valueColumnName = "VALUE",
                    pkColumnValue = "OLAY")
    private Integer id;
    @Column(name = "CINS")
    private Integer cins;
    @Size(max = 150)
    @Column(name = "MESAJ")
    private String mesaj;
    @Column(name = "EKLENME_ZAMANI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eklenmeZamani;
    @Column(name = "KULLANICI")
    private String user;
    @Transient
    private EventType type;



    public Olay() {
    }
    
    public Olay(String message, EventType type, String user){
        this.mesaj = message;
        this.eklenmeZamani = new Date();
        this.cins = type.getType();
        this.type = type;
        this.user = user;
    }

    public Olay(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCins() {
        return cins;
    }

    public void setCins(Integer cins) {
        this.cins = cins;
        this.type = EventType.getByType(cins);
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public Date getEklenmeZamani() {
        return eklenmeZamani;
    }

    public void setEklenmeZamani(Date eklenmeZamani) {
        this.eklenmeZamani = eklenmeZamani;
    }
    public EventType getType() {
        return type;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Olay)) {
            return false;
        }
        Olay other = (Olay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mesaj;
    }
    
}
