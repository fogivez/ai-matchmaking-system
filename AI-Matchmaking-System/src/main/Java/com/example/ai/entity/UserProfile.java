package com.example.ai.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(length = 50)
    private String nickname;

    @Column(length = 10)
    private String gender;

    private Integer age;

    // 用 TEXT 映射 MySQL 的 JSON 字段，免去本地复杂的驱动配置，打包交付最稳妥
    @Column(columnDefinition = "json")
    private String hobbies;

    @Column(name = "ai_personality_tags", columnDefinition = "json")
    private String aiPersonalityTags;

    @Column(name = "raw_bio", columnDefinition = "text")
    private String rawBio;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}