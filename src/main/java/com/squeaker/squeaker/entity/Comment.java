package com.squeaker.squeaker.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "squeak_id")
    @JsonBackReference(value = "squeak-comments")
    private Squeak squeak;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value="user-comment")
    private User user;

    public Comment() {
    }

    public Comment(Long id, String content, Squeak squeak, User user) {
        this.id = id;
        this.content = content;
        this.squeak = squeak;
        this.user = user;
    }

    public @NotBlank String getContent() {
        return content;
    }

    public void setContent(@NotBlank String content) {
        this.content = content;
    }

    public Squeak getSqueak() {
        return squeak;
    }

    public void setSqueak(Squeak squeak) {
        this.squeak = squeak;
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
