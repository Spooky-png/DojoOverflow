package com.spooky.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spooky.overflow.models.Tag;

public interface TagRepository extends CrudRepository<Tag, Long>{
	List<Tag> findAll();
}
