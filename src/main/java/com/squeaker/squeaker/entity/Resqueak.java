package com.squeaker.squeaker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="resqueaks")
public class Resqueak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "squeak_id")

    private Squeak squeak;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Resqueak(Long id, Squeak squeak, User user) {
        this.id = id;
        this.squeak = squeak;
        this.user = user;
    }

    public Resqueak() {
    }

    @JsonProperty("squeakId")
    public Long getSqueakId() {
        return squeak != null ? squeak.getId() : null;
    }
}
