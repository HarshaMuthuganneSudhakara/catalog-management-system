package com.thippeshhirenallur.catalogmanagementplatform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thippeshhirenallur.catalogmanagementplatform.entity.Media;

@Repository
public interface MediaRepository  extends CrudRepository<Media, Integer> {

}
