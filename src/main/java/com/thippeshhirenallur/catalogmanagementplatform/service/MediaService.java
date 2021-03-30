package com.thippeshhirenallur.catalogmanagementplatform.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.thippeshhirenallur.catalogmanagementplatform.entity.Media;

@Service
public interface MediaService {
	
	public List<Media> getMediasByProductSubCategoryAndCategory(Integer categoryId, Integer subCategoryId,
			Integer productId);

	public Media addMedia(Integer categoryId, Integer subCategoryId, Integer productId, Media media);

	@Transactional
	public Media updateMedia(Integer categoryId, Integer subCategoryId, Integer productId, Integer mediaId,
			Media media);

	public Media deleteMedia(Integer categoryId, Integer subCategoryId, Integer productId, Integer mediaId);
	
}
