package com.tdk.edu.chatterino.repository;

import com.tdk.edu.chatterino.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
