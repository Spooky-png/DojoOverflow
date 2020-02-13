package com.spooky.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spooky.overflow.models.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long>{
	List<Answer> findAll();
}
