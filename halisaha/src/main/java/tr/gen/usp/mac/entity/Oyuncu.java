/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gen.usp.mac.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sitki.poyraz
 */
@Entity
@Table(name = "OYUNCU")
@NamedQueries({
    @NamedQuery(name = "Oyuncu.findAktif", query = "SELECT o FROM Oyuncu o WHERE o.aktif = true"),
    @NamedQuery(name = "Oyuncu.findById", query = "SELECT o FROM Oyuncu o WHERE o.id = :id"),
    @NamedQuery(name = "Oyuncu.findByAd", query = "SELECT o FROM Oyuncu o WHERE o.ad = :ad"),
    @NamedQuery(name = "Oyuncu.findByEmail", query = "SELECT o FROM Oyuncu o WHERE o.email = :email"),
    @NamedQuery(name = "Oyuncu.findByDahiliNo", query = "SELECT o FROM Oyuncu o WHERE o.dahiliNo = :dahiliNo"),
    @NamedQuery(name = "Oyuncu.findByCepNo", query = "SELECT o FROM Oyuncu o WHERE o.cepNo = :cepNo")})
public class Oyuncu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "oyuncu_generator")
    @TableGenerator(allocationSize = 1,
                    name = "oyuncu_generator",
                    table = "ID_SEQUENCE",
                    pkColumnName = "NAME",
                    valueColumnName = "VALUE",
                    pkColumnValue = "OYUNCU")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "AD")
    private String ad;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 4)
    @Column(name = "DAHILI_NO")
    private String dahiliNo;
    @Size(max = 11)
    @Column(name = "CEP_NO")
    private String cepNo;
    @Column(name = "AKTIF")
    private boolean aktif;    

    public Oyuncu() {
    }

    public Oyuncu(Integer id) { 
        this.id = id;
    }

    public Oyuncu(Integer id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDahiliNo() {
        return dahiliNo;
    }

    public void setDahiliNo(String dahiliNo) {
        this.dahiliNo = dahiliNo;
    }

    public String getCepNo() {
        return cepNo;
    }

    public void setCepNo(String cepNo) {
        this.cepNo = cepNo;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
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
        if (!(object instanceof Oyuncu)) {
            return false;
        }
        Oyuncu other = (Oyuncu) object;
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
