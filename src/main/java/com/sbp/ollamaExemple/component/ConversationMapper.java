package com.sbp.ollamaExemple.component;

import com.sbp.ollamaExemple.dto.ConversationDTO;
import com.sbp.ollamaExemple.entity.ConversationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConversationMapper {

    private final QuestionMapper questionMapper;

    public ConversationEntity dtoToEntity(ConversationDTO conversationDTO) {
        return ConversationEntity
                .builder()
                .title(conversationDTO.title())
                .questions(conversationDTO.questions().stream().map(questionMapper::dtoToEntity).collect(Collectors.toList()))
                .build();
    }

    public ConversationDTO entityToDto(ConversationEntity conversationEntity) {
        return ConversationDTO
                .builder()
                .title(conversationEntity.getTitle())
                .questions(conversationEntity.getQuestions() != null
                        ? conversationEntity.getQuestions().stream()
                        .map(questionMapper::entityToDto)
                        .collect(Collectors.toList())
                        : List.of()) // Liste vide par défaut
                .build();
    }

}
