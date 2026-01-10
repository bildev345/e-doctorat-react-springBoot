package org.example.doctoratrestapi.user;

import jakarta.persistence.*;
import lombok.Data;
import org.example.doctoratrestapi.role.RoleModel;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleModel> roles = new HashSet<>();



}
