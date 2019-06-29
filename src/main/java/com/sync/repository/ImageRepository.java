package com.sync.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sync.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, String>{
	
	List<Image> findByUsername(String name);

}
