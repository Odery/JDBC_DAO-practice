package dao2;

import java.io.Serializable;
import java.sql.Date;

public class Actor implements Serializable{
    private int actor_id;
    private String first_name;
    private String last_name;
    private Date last_update;

    public Actor() {
    }

    public Actor(int actor_id, String first_name, String last_name, Date last_update) {
        this.actor_id = actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_update = last_update;
    }

    public Actor(String first_name, String last_name, Date last_update) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_update = last_update;
    }

    public Actor(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
    public void show(){
        System.out.println("ID: "+actor_id);
        System.out.println("Name: "+first_name);
        System.out.println("Last name: "+last_name);
        System.out.println("Last updated: "+last_update.toString());
    }
}
