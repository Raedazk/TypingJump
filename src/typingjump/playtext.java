package typingjump;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah
 * Jabr
 */
@Entity
@Table(name = "playtext")
public class playtext implements java.io.Serializable {

    @Id
    @Column(name = "idtext")
    private int idtext;
    @Column(name = "type")
    private String type;
    @Column(name = "text")
    private String text;

    public playtext(int idtext, String type, String text) {
        this.idtext = idtext;
        this.type = type;
        this.text = text;
    }

    public playtext() {
    }

    public int getIdtext() {
        return idtext;
    }

    public void setIdtext(int idtext) {
        this.idtext = idtext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
