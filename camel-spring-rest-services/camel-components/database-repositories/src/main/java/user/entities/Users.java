package user.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    //@Transient
    // private final String USER_INFO = "UserId | %1$s | Email | %2$s | Name | %3$ %4$";
    private static final long serialVersionUID = 1L;

    @Column(unique = true, name = "userid")
    @Id
    private String userId;

    @Column(unique = true, name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile_no", nullable = false)
    private String number;

    @Column(name = "pwd", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne(targetEntity = Authorities.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role", nullable = false, referencedColumnName = "id")
    private Authorities authorities;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number='" + number + '\'' +
                ", status=" + status +
                ", authorities=" + authorities +
                '}';
    }
}
