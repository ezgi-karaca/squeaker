package com.squeaker.squeaker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "squeak_id")
    @JsonBackReference(value="squeak-likes")
    private Squeak squeak;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value="user-likes")
    private User user;


    public Like(User user, Squeak squeak) {
        this.user = user;
        this.squeak = squeak;
    }

    public Like() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @JsonProperty("squeakId")
    public Long getSqueakId() {
        return squeak != null ? squeak.getId() : null;
    }

    @JsonProperty("userId")
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }
}
