package com.sbp.ollamaExemple.component;

import com.sbp.ollamaExemple.dto.QuestionDTO;
import com.sbp.ollamaExemple.entity.QuestionEntity;
import com.sbp.ollamaExemple.repository.ConversationRepo;
import com.sbp.ollamaExemple.repository.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionMapper {

    private final ConversationRepo conversationRepo;

    public QuestionEntity dtoToEntity(QuestionDTO questionDTO) {
        return QuestionEntity
                .builder()
                .question(questionDTO.question())
                .response(questionDTO.response())
                .conversation(questionDTO.id_conversation() != null ? conversationRepo.findById(questionDTO.id_conversation()).get() : null)
                .build();
    }

    public QuestionDTO entityToDto(QuestionEntity questionEntity) {
        return QuestionDTO
                .builder()
                .question(questionEntity.getQuestion())
                .response(questionEntity.getResponse())
                .id_conversation(questionEntity.getConversation().getId())
                .build();
    }
}
