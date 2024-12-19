package com.sbp.ollamaExemple.repository;

import com.sbp.ollamaExemple.entity.ConversationEntity;
import com.sbp.ollamaExemple.entity.QuestionEntity;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepo extends CrudRepository<QuestionEntity, Long> {
}
