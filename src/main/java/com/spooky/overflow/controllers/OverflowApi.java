package com.spooky.overflow.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spooky.overflow.models.Answer;
import com.spooky.overflow.models.Question;
import com.spooky.overflow.models.Tag;
import com.spooky.overflow.services.OverflowService;

@RestController
public class OverflowApi {
	private final OverflowService overflowService;
	public OverflowApi(OverflowService overflowService) {
		this.overflowService = overflowService;
	}
	@GetMapping("/api/tags")
	public List<Tag> index(){
		return overflowService.allTags();
	}
	@PostMapping(value="/api/tags")
	public Tag create(@RequestParam(value="subject") String subject,@RequestParam(value="question") List<Question> question) {
		Tag tag = new Tag(subject, question);
		return overflowService.createTag(tag);
	}
	@GetMapping("/api/answers")
	public List<Answer> index2(){
		return overflowService.allAnswers();
	}
	@PostMapping(value="/api/answers")
	public Answer create2(@RequestParam(value="name") String name,@RequestParam(value="question") Question question) {
		Answer answer = new Answer(name, question);
		return overflowService.createAnswer(answer);
	}
	@GetMapping("/api/questions")
	public List<Question> index3(){
		return overflowService.allQuestions();
	}
	@PostMapping(value="/api/questions")
	public Question create3(@RequestParam(value="question") String questioncontent) {
		Question question = new Question(questioncontent);
		return overflowService.createQuestion(question);
	}
}
