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
		Question question = overflowService.findQuestion(id);
		List<Tag> tags = overflowService.allTags();
		List<Answer> answers = overflowService.allAnswers();
		model.addAttribute("tag", tags);
		model.addAttribute("answer", answers);
		model.addAttribute("question", question);
		return "/overflow/viewQuestion.jsp";
	}
	@GetMapping("/overflow/question/new")
	public String newQuestion(@ModelAttribute("questions") Question q) {
		return "/overflow/newQuestion.jsp";
	}
	@PostMapping(value="/overflow/question/new")
	public String createQuestion(@Valid @ModelAttribute("questions") Question q,@RequestParam("t") Tag t, BindingResult result, Model model) {
		if(result.hasErrors()) {
			// is unique logic?
			return "/overflow/newQuestion.jsp";
		} else {
			Question createdquestion = overflowService.createQuestion(q);
			createdquestion.getTags().add(t);
			questionRepository.save(createdquestion);
			return "redirect:/overflow/questions";
		}
	}
	@GetMapping("/overflow/addquestion/{id}")
	public String show(@PathVariable("id") Long id) {
		return "/overflow/question/{id}";
	}
	@PostMapping(value="/overflow/addquestion/{id}")
	public String add(@PathVariable("id") Long id,Answer a ) {
		Question question = overflowService.findQuestion(id);
		Answer createdanswer = overflowService.createAnswer(a);
		question.getAnswers().add(createdanswer);
		questionRepository.save(question);
		return "/overflow/question/{id}";
	}
	public AnswerRepository getAnswerRepository() {
		return answerRepository;
	}
	public TagRepository getTagRepository() {
		return tagRepository;
	}
	public QuestionRepository getQuestionRepository() {
		return questionRepository;
	}

}
