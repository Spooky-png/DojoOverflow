package com.spooky.overflow.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="answers")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min=5)
	private String answer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="question_id")
	private Question question;
	
	public Answer() {
	}
	public Answer(String answer) {
		this.answer = answer;
	}
	public Answer(String answer, Question question) {
		this.setAnswer(answer);
		this.setQuestion(question);
	}
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	public Long getId() {
		return id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String subject) {
		this.answer = subject;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}

}
