package com.noname.lnasessionapi.data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private OffsetDateTime sentAt;

    @Column(nullable = false)
    private String agent;

    @Column(nullable = false)
    private UUID session;

}