package user.entities;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private String role;

    public Authorities() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
