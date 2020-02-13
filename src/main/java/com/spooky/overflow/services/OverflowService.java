package com.spooky.overflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spooky.overflow.models.Answer;
import com.spooky.overflow.models.Question;
import com.spooky.overflow.models.Tag;
import com.spooky.overflow.repositories.AnswerRepository;
import com.spooky.overflow.repositories.QuestionRepository;
import com.spooky.overflow.repositories.TagRepository;

@Service
public class OverflowService {
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final TagRepository tagRepository;
	
	public OverflowService(AnswerRepository answerRepository,QuestionRepository questionRepository,TagRepository tagRepository) {
		this.answerRepository = answerRepository;
		this.questionRepository = questionRepository;
		this.tagRepository = tagRepository;
	}
	public List<Tag> allTags(){
		return tagRepository.findAll();
	}
	public List<Answer> allAnswers(){
		return answerRepository.findAll();
	}
	public List<Question> allQuestions(){
		return questionRepository.findAll();
	}
	public Tag findTag(Long id) {
		Optional<Tag> optionalTag = tagRepository.findById(id);
		if(optionalTag.isPresent()) {
			return optionalTag.get();
		} else {
			return null;
		}
	}
	public Answer findAnswer(Long id) {
		Optional<Answer> optionalAnswer = answerRepository.findById(id);
		if(optionalAnswer.isPresent()) {
			return optionalAnswer.get();
		} else {
			return null;
		}
	}
	public Question findQuestion(Long id) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if(optionalQuestion.isPresent()) {
			return optionalQuestion.get();
		} else {
			return null;
		}
	}
	public Tag createTag(Tag t) {
		return tagRepository.save(t);
	}
	public Answer createAnswer(Answer a) {
		return answerRepository.save(a);
	}
	public Question createQuestion(Question q) {
		return questionRepository.save(q);
	}
}
