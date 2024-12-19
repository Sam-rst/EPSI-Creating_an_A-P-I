package com.sbp.ollamaExemple.controller;

import com.sbp.ollamaExemple.dto.ConversationDTO;
import com.sbp.ollamaExemple.dto.QuestionDTO;
import com.sbp.ollamaExemple.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/conversation")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping(path = "/askQuestion")
    public ConversationDTO askQuestion(@RequestParam String question, @RequestParam(required = false) Long id_conversation) {
        return conversationService.askQuestion(question, id_conversation);
    }
}
