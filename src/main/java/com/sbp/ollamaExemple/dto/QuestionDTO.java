package com.sbp.ollamaExemple.dto;

import lombok.Builder;

@Builder
public record QuestionDTO(String question, String response, Long id_conversation) {
}
