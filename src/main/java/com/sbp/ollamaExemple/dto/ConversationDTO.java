package com.sbp.ollamaExemple.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ConversationDTO(String title, List<QuestionDTO> questions) {
}
