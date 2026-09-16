package com.aarohi.subsense.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity  //Entity = represents/maps the database table
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @Getter
    @NotBlank(message="Name is required")
    private String name;

    @Setter
    @Getter
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique=true , nullable =false)
    private String email;

    @Getter
    @Setter
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;
    @Setter
    @Getter
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
