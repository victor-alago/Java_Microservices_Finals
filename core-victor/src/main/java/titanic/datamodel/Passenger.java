package titanic.datamodel;

import jakarta.persistence.*;

@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String pclass;

    @Column
    private double age;

    @Column
    private String sex;

    @Column
    private boolean survived;

    public Passenger() {
        // Default constructor for JPA
    }

    public Passenger(String name, String pclass, double age, String sex, boolean survived) {
        this.name = name;
        this.pclass = pclass;
        this.age = age;
        this.sex = sex;
        this.survived = survived;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPclass() {
        return pclass;
    }

    public void setPclass(String pclass) {
        this.pclass = pclass;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isSurvived() {
        return survived;
    }

    public void setSurvived(boolean survived) {
        this.survived = survived;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pclass='" + pclass + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", survived=" + survived +
                '}';
    }
}