/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gen.usp.mac.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.primefaces.model.map.MapModel;

/**
 *
 * @author sitki.poyraz
 */
@Entity
@Table(name = "SAHA")
@NamedQueries({
    @NamedQuery(name = "Saha.findAll", query = "SELECT s FROM Saha s"),
    @NamedQuery(name = "Saha.findById", query = "SELECT s FROM Saha s WHERE s.id = :id"),
    @NamedQuery(name = "Saha.findByAd", query = "SELECT s FROM Saha s WHERE s.ad = :ad"),
    @NamedQuery(name = "Saha.findByAciklama", query = "SELECT s FROM Saha s WHERE s.aciklama = :aciklama"),
    @NamedQuery(name = "Saha.findByAdres", query = "SELECT s FROM Saha s WHERE s.adres = :adres"),
    @NamedQuery(name = "Saha.findByTel", query = "SELECT s FROM Saha s WHERE s.tel = :tel"),
    @NamedQuery(name = "Saha.findByMaxKisi", query = "SELECT s FROM Saha s WHERE s.maxKisi = :maxKisi")})
public class Saha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    	 @GeneratedValue(strategy = GenerationType.TABLE, generator = "saha_generator")
    @TableGenerator(allocationSize = 1,
                    name = "saha_generator",
                    table = "ID_SEQUENCE",
                    pkColumnName = "NAME",
                    valueColumnName = "VALUE",
                    pkColumnValue = "SAHA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "AD")
    private String ad;
    @Size(max = 50)
    @Column(name = "ACIKLAMA")
    private String aciklama;
    @Size(max = 50)
    @Column(name = "ADRES")
    private String adres;
    @Size(max = 11)
    @Column(name = "TEL")
    private String tel;
    @Column(name = "MAX_KISI")
    private Integer maxKisi;
    @Size(max = 40)
    @Column(name = "KOORDINAT")
    private String koordinat;
    @OneToMany(mappedBy = "saha")
    private Collection<Mac> macCollection;
    @Transient
    private MapModel simpleModel;

    public Saha() {
    }
    
    public Saha(Integer id) {
        this.id = id;
    }

    public Saha(Integer id, String ad) {
        this.id = id;
        this.ad = ad;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getMaxKisi() {
        return maxKisi;
    }

    public void setMaxKisi(Integer maxKisi) {
        this.maxKisi = maxKisi;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public Collection<Mac> getMacCollection() {
        return macCollection;
    }

    public void setMacCollection(Collection<Mac> macCollection) {
        this.macCollection = macCollection;
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
        if (!(object instanceof Saha)) {
            return false;
        }
        Saha other = (Saha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ad;
    }
}
