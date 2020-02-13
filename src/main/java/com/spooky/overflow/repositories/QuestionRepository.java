package com.spooky.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spooky.overflow.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{
	List<Question> findAll();
}
