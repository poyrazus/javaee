/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gen.usp.mac.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import tr.gen.usp.mac.jsf.util.JsfUtil;

/**
 *
 * @author sitki.poyraz
 */
@Entity
@Table(name = "MAC")
@NamedQueries({
    @NamedQuery(name = "Mac.findAll", query = "SELECT m FROM Mac m"),
    @NamedQuery(name = "Mac.findById", query = "SELECT m FROM Mac m WHERE m.id = :id"),
    @NamedQuery(name = "Mac.findByTarih", query = "SELECT m FROM Mac m WHERE m.tarih = :tarih"),
    @NamedQuery(name = "Mac.findBySaat", query = "SELECT m FROM Mac m WHERE m.saat = :saat"),
    @NamedQuery(name = "Mac.findByAsilKadroSayisi", query = "SELECT m FROM Mac m WHERE m.asilKadroSayisi = :asilKadroSayisi")})
public class Mac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "mac_generator")
        @TableGenerator(allocationSize = 1,
                    name = "mac_generator",
                    table = "ID_SEQUENCE",
                    pkColumnName = "NAME",
                    valueColumnName = "VALUE",
                    pkColumnValue = "MAC")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TARIH")
    @Temporal(TemporalType.DATE)
    private Date tarih;
    @Size(max = 11)
    @Column(name = "SAAT")
    private String saat;
    @Column(name = "ASIL_KADRO_SAYISI")
    private Integer asilKadroSayisi;
    @JoinColumn(name = "SAHA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Saha saha;
    
    public Mac() {
    }

    public Mac(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public Integer getAsilKadroSayisi() {
        return asilKadroSayisi;
    }

    public void setAsilKadroSayisi(Integer asilKadroSayisi) {
        this.asilKadroSayisi = asilKadroSayisi;
    }

    public Saha getSaha() {
        return saha;
    }

    public void setSaha(Saha saha) {
        this.saha = saha;
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
        if (!(object instanceof Mac)) {
            return false;
        }
        Mac other = (Mac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return JsfUtil.formatDate(tarih) + " Saat: " + saat + " Saha: " + saha.getAd();
    }
    
}
