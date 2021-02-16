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
@Table(name = "player")
public class player implements java.io.Serializable {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "score")
    private double score;
    @Column(name = "time")
    private double time;
    @Column(name = "gender")
    private String gender;
    @Column(name = "speed")
    private double speed;
    @Column(name = "accuracy")
    private double accuracy;
    @Column(name = "no_chars")
    private int no_chars;
    @Column(name = "type")
    private String type;

    public player(int id, String user_name, double score, int time, String gender, double speed, double accuracy, int no_chars, String type) {
        this.id = id;
        this.user_name = user_name;
        this.score = score;
        this.time = time;
        this.gender = gender;
        this.speed = speed;
        this.accuracy = accuracy;
        this.no_chars = no_chars;
        this.type = type;
    }

    public player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getNo_chars() {
        return no_chars;
    }

    public void setNo_chars(int no_chars) {
        this.no_chars = no_chars;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "player{" + "id=" + id + ", user_name=" + user_name + ", score=" + score + ", time=" + time + ", gender=" + gender + ", speed=" + speed + ", accuracy=" + accuracy + ", no_chars=" + no_chars + ", type=" + type + '}';
    }

}
