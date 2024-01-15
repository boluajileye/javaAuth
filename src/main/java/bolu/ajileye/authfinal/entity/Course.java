package bolu.ajileye.authfinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long score;

    @Column(nullable = true)
    private Boolean isGraded;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "course_users", joinColumns = @JoinColumn(name = "course_od"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private List<User> user;

}
