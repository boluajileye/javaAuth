package bolu.ajileye.authfinal.entity;

import bolu.ajileye.authfinal.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static bolu.ajileye.authfinal.config.SpringSecurityConfig.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(
        name = "users_unique_email", columnNames = "email"
))
@Builder
public class User extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, length = 1000)
    private String about;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = true)
    private Boolean verse;

    @Column(nullable = false)
    private Boolean isBlocked;

    @Column(nullable = true)
    private LocalDateTime emailVerifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Car> car;

    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> courses;

    @PrePersist
    public void mutators(){
        this.isActive = false;
        this.isBlocked = false;
        this.password = passwordEncoder().encode(password);
    }

    public String getFirstName(){
        String[] name = this.name.split("");
        return name[0];
    }

    public String getLastName(){
        String[] name = this.name.split("");
        return name[1];
    }
}
