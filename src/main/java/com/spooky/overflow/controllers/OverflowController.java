package com.spooky.overflow.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spooky.overflow.models.Answer;
import com.spooky.overflow.models.Question;
import com.spooky.overflow.models.Tag;
import com.spooky.overflow.repositories.AnswerRepository;
import com.spooky.overflow.repositories.QuestionRepository;
import com.spooky.overflow.repositories.TagRepository;
import com.spooky.overflow.services.OverflowService;

@Controller
public class OverflowController {
	private final OverflowService overflowService;
	private final TagRepository tagRepository;
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	
	public OverflowController(OverflowService overflowService,TagRepository tagRepository,AnswerRepository answerRepository,QuestionRepository questionRepository) {
		this.overflowService = overflowService;
		this.tagRepository = tagRepository;
		this.answerRepository = answerRepository;
		this.questionRepository = questionRepository;
	}
	
	@GetMapping("/")
	public String bounce() {
		return "/overflow/index.jsp";
	}
	@GetMapping("/overflow/question")
	public String viewQuestions(Model model) {
		List<Question>allQuestions = overflowService.allQuestions();
		List<Tag>allTags = overflowService.allTags();
		model.addAttribute("question", allQuestions);
		model.addAttribute("tag", allTags);
		return "/overflow/questions.jsp";
	}
	@GetMapping("/overflow/question/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		System.out.println("INSIDE THE RENDER ANSWER");
		Question question = overflowService.findQuestion(id);
		model.addAttribute("question", question);
		return "/overflow/viewQuestion.jsp";
	}
	@PostMapping("/overflow/question/{id}")
	public String add(@PathVariable("id") Long id,@RequestParam("answer")String a ) {
		System.out.println("INSIDE THE CREATE ANSWER");
		Question question = overflowService.findQuestion(id);
		overflowService.createAnswer(a, question);
		return "redirect:/overflow/question/" + id;
	}
	@GetMapping("/overflow/question/new")
	public String newQuestion() {
		return "/overflow/newQuestion.jsp";
	}
	@PostMapping("/overflow/question/new")
	public String createQuestion(@RequestParam("question")String q,@RequestParam("tag")String t) {
		//if(result.hasErrors()) {
			// is unique logic?
			//return "/overflow/newQuestion.jsp";
		//} else {
			Tag createdTag = overflowService.createTag(t);
			overflowService.createQuestion(q, createdTag);
			return "redirect:/overflow/questions";
		}
	}
