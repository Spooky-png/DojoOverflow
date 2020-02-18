package com.spooky.overflow.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 5)
	private String question;
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
	     name = "questions_tags", 
	     joinColumns = @JoinColumn(name = "question_id"), 
	     inverseJoinColumns = @JoinColumn(name = "tag_id")
	 )
	private List<Tag> tags;
	 @OneToMany(mappedBy="question", fetch = FetchType.LAZY)
	 private List<Answer> answers;
	
	public Question() {
	}public Question(String question, Tag tag) {
		this.question = question;
		this.tags = tags;
	}
	public Question(String question) {
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<Tag> getTags(){
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<Answer> getAnswers(){
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
