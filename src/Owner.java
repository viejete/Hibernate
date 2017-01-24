import java.util.*;

public class Owner {

    private int id;
    private String firstName;
    private String lastName;
    private String race;
    private Set pets;


    public Owner(){}

    public Owner(String firstName, String lastName, String race) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.race = race;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Set getPets() {
        return pets;
    }

    public void setPets(Set pets) {
        this.pets = pets;
    }

}
