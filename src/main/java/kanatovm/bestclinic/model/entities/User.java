package kanatovm.bestclinic.model.entities;

import kanatovm.bestclinic.model.enums.Role;
import kanatovm.bestclinic.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    public User(Long id, String email, String password, Status status, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && status == user.status && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, status, role);
    }
}
