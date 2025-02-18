package com.squeaker.squeaker.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name= "squeaks")

public class Squeak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value="user-squeak")
    private User user;

    @OneToMany(mappedBy = "squeak", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "squeak-comments")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "squeak", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "squeak-likes")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "squeak", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "squeak-resqueaks")
    private List<Resqueak> resqueaks = new ArrayList<>();




    public Squeak() {
    }

    public Squeak(String content, User user, Long id) {
        this.content = content;
        this.user = user;
        this.id = id;
    }

    public @NotBlank String getContent() {
        return content;
    }

    public void setContent(@NotBlank String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return user != null ? user.getUsername() : null;
    }
}
