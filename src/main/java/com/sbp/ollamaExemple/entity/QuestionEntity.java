package com.sbp.ollamaExemple.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String response;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private ConversationEntity conversation;
}
