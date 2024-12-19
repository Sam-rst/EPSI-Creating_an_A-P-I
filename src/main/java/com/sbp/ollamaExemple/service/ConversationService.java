package com.sbp.ollamaExemple.service;

import com.sbp.ollamaExemple.component.ConversationMapper;
import com.sbp.ollamaExemple.dto.ConversationDTO;
import com.sbp.ollamaExemple.entity.ConversationEntity;
import com.sbp.ollamaExemple.entity.QuestionEntity;
import com.sbp.ollamaExemple.repository.ConversationRepo;
import com.sbp.ollamaExemple.repository.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final QuestionRepo questionRepo;
    private final ConversationRepo conversationRepo;

    private final FileReadingService fileReadingService;
    private final ChatModel chatModel;
    private final ConversationMapper conversationMapper;

    public ConversationDTO askQuestion(String question_message, Long id_conversation) {
        String prompt = fileReadingService.readInternalFileAsString("prompts/promptTonyStark.txt") ;

        List<Message> messages = new ArrayList<>();
        messages.add(new SystemMessage("<start_of_turn>" + prompt + "<end_of_turn>")) ;
        messages.add(new UserMessage("<start_of_turn>" + question_message + "<end_of_turn>")) ;

        Prompt promptToSend = new Prompt(messages);
        Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
        String answer = Objects.requireNonNull(chatResponses.collectList().block()).stream()
                .map(response -> response.getResult().getOutput().getContent())
                .collect(Collectors.joining("")) ;


        ConversationEntity conversation;
        String title = question_message.substring(0, Math.min(question_message.length(), 35));
        if (id_conversation == null) {
            conversation = ConversationEntity
                    .builder()
                    .title(title)
                    .build();

        } else {
            if (conversationRepo.findById(id_conversation).isPresent()) {
                conversation = conversationRepo.findById(id_conversation).get();

            } else {
                conversation = ConversationEntity
                    .builder()
                    .title(title)
                    .build();
            }

        }

        conversationRepo.save(conversation);

        QuestionEntity question = QuestionEntity
                .builder()
                .question(question_message)
                .response(answer)
                .conversation(conversation)
                .build();

        questionRepo.save(question);


        if(conversation.getQuestions() == null)
            conversation.setQuestions(new ArrayList<>());

        conversation.getQuestions().add(question);

        return conversationMapper.entityToDto(conversation);
    }

    public ConversationDTO findById(Long id_conversation) {
        Optional<ConversationEntity> conversation = conversationRepo.findById(id_conversation);
        return conversation.map(conversationMapper::entityToDto).orElse(null);
    }
}
