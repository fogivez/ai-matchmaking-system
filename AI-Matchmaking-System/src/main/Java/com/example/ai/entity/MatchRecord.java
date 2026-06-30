package com.example.ai.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "match_records")
public class MatchRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "user_a_id", nullable = false)
    private Long userAId;

    @Column(name = "user_b_id", nullable = false)
    private Long userBId;

    @Column(name = "match_score")
    private BigDecimal matchScore;

    @Column(name = "match_state", nullable = false, length = 30)
    private String matchState; // PENDING, COMMUNICATING, DISCONNECTED

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}