package com.squeaker.squeaker.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value="user-squeak")
    private Set<Squeak> squeaks = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value="user-resqueak")
    private Set<Resqueak> resqueaks = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value="user-likes")
    private Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value="user-comments")
    private Set<Comment> comments = new HashSet<>();


    public User() {
    }

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public Set<Squeak> getSqueaks() {
        return squeaks;
    }

    public void setSqueaks(Set<Squeak> squeaks) {
        this.squeaks = squeaks;
    }

    public Set<Resqueak> getResqueaks() {
        return resqueaks;
    }

    public void setResqueaks(Set<Resqueak> resqueaks) {
        this.resqueaks = resqueaks;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
