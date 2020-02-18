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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
	 name = "questions_tags", 
	 joinColumns = @JoinColumn(name = "tag_id"), 
	 inverseJoinColumns = @JoinColumn(name = "question_id")
	 )
	private List<Question> questions;
	
	public Tag() {
	}
	public Tag(String tag) {
		this.subject = tag;
	}
	public Tag(String subject, List<Question> questions) {
		this.setSubject(subject);
		this.setQuestions(questions);
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<Question> getQuestions(){
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
