package com.sbp.ollamaExemple.repository;

import com.sbp.ollamaExemple.entity.ConversationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepo extends CrudRepository<ConversationEntity, Long> {
}
