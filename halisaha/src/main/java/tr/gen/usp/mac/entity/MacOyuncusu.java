/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gen.usp.mac.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sitki.poyraz
 */ 
@Entity
@Table(name = "MAC_OYUNCUSU", uniqueConstraints={@UniqueConstraint(columnNames={"MAC_ID", "OYUNCU_ID"})})
@NamedQueries({
    @NamedQuery(name = "MacOyuncusu.deleteById", query = "DELETE FROM MacOyuncusu m WHERE m.id = :id"),
    @NamedQuery(name = "MacOyuncusu.findById", query = "SELECT m FROM MacOyuncusu m WHERE m.id = :id"),
    @NamedQuery(name = "MacOyuncusu.findByMacId", query = "SELECT m FROM MacOyuncusu m WHERE m.mac.id = :id ORDER BY m.eklenmeZamani"),
    @NamedQuery(name = "MacOyuncusu.findByEklenmeZamani", query = "SELECT m FROM MacOyuncusu m WHERE m.eklenmeZamani = :eklenmeZamani")})
public class MacOyuncusu implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "macoyuncusu_generator")
        @TableGenerator(allocationSize = 1,
                    name = "macoyuncusu_generator",
                    table = "ID_SEQUENCE",
                    pkColumnName = "NAME",
                    valueColumnName = "VALUE",
                    pkColumnValue = "MACOYUNCUSU")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "EKLENME_ZAMANI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eklenmeZamani;

    @ManyToOne
    @JoinColumn(name = "MAC_ID")
    private Mac mac;
    
    @ManyToOne
    @JoinColumn(name = "OYUNCU_ID")
    private Oyuncu oyuncu;
    
    public MacOyuncusu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Mac getMac() {
        return mac;
    }

    public void setMac(Mac mac) {
        this.mac = mac; 
    }

    public Oyuncu getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
    }

    public Date getEklenmeZamani() {
        return eklenmeZamani;
    }

    public void setEklenmeZamani(Date eklenmeZamani) {
        this.eklenmeZamani = eklenmeZamani;
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
        if (!(object instanceof MacOyuncusu)) {
            return false;
        }
        MacOyuncusu other = (MacOyuncusu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MacOyuncusu[ad=" + oyuncu + " mac= " + mac +" ]";
    }
    
}
