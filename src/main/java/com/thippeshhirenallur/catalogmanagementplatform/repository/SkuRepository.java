package com.thippeshhirenallur.catalogmanagementplatform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thippeshhirenallur.catalogmanagementplatform.entity.SKU;
@Repository
public interface SkuRepository extends CrudRepository<SKU, Integer> {
}
