package com.thippeshhirenallur.catalogmanagementplatform.service;

import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thippeshhirenallur.catalogmanagementplatform.entity.Category;
import com.thippeshhirenallur.catalogmanagementplatform.entity.Media;
import com.thippeshhirenallur.catalogmanagementplatform.entity.Product;
import com.thippeshhirenallur.catalogmanagementplatform.entity.SubCategory;
import com.thippeshhirenallur.catalogmanagementplatform.exception.ResourceNotFoundException;
import com.thippeshhirenallur.catalogmanagementplatform.repository.CategoryRepository;
import com.thippeshhirenallur.catalogmanagementplatform.repository.MediaRepository;
import com.thippeshhirenallur.catalogmanagementplatform.repository.ProductRepository;
import com.thippeshhirenallur.catalogmanagementplatform.repository.SubCategoryRepository;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	public List<Media> getMediasByProductSubCategoryAndCategory(Integer categoryId, Integer subCategoryId,
			Integer productId) {
		List<Product> products = getProductsBySubCategoryAndCategory(categoryId, subCategoryId);
		if(products == null)
			throw new ResourceNotFoundException("Products Not Found for a given input data");
		Product product = getProduct(productId,products);
		if(product == null)
			throw new ResourceNotFoundException("Product Not Found for a given input data");
		return product.getMedias();
	}

	
	public Media addMedia(Integer categoryId, Integer subCategoryId, Integer productId, Media media) {
		return mediaRepository.save(media);
	}

	
	public Media updateMedia(Integer categoryId, Integer subCategoryId, Integer productId, Integer mediaId,
			Media media) {
		List<Media> medias = getMediasByProductSubCategoryAndCategory(categoryId, subCategoryId,productId);
		if(medias == null)
			throw new ResourceNotFoundException("Medias Not Found for a given input data");
		Media mediaToUpdate = getMedia(medias, mediaId);
		if(mediaToUpdate == null)
			throw new ResourceNotFoundException("Media to be updated is not found");
		mediaToUpdate.setAltText(media.getAltText());
		mediaToUpdate.setImageUrl(media.getImageUrl());
		mediaToUpdate.setProductId(media.getProductId());	
		return mediaToUpdate;
	}

	
	private Media getMedia(List<Media> medias, Integer mediaId) {
		for (int x = 0; x < medias.size(); x++) {
			if (medias.get(x).getMediaId() == mediaId)
				return medias.get(x);
		}
		return null;
	}


	public Media deleteMedia(Integer categoryId, Integer subCategoryId, Integer productId, Integer mediaId) {
		List<Media> medias = getMediasByProductSubCategoryAndCategory(categoryId, subCategoryId,productId);
		Media mediaToDelete = getMedia(medias, mediaId);
		if (mediaToDelete != null)
			mediaRepository.delete(mediaToDelete);
		return mediaToDelete;
	}
	
	public Product getProduct(Integer productId, List<Product> products) {
		for (int x = 0; x < products.size(); x++) {
			if (products.get(x).getProductId() == productId)
				return products.get(x);
		}
		return null;
	}
	
	public List<Product> getProductsBySubCategoryAndCategory(Integer categoryId, Integer subCategoryId) {
		List<SubCategory> subCategories = getSubCategoriesByCategory(categoryId);
		if(subCategories == null)
			throw new ResourceNotFoundException("subCategories Not Found for a given input data");
		SubCategory subCategory = getSubCategory(subCategoryId, subCategories);
		if(subCategory == null)
			throw new ResourceNotFoundException("subCategory Not Found for a given input data");
		return subCategory.getProducts();
	}
	
	private SubCategory getSubCategory(Integer subCategoryId, List<SubCategory> subCategories) {
		for (int x = 0; x < subCategories.size(); x++) {
			if (subCategories.get(x).getSubCategoryId() == subCategoryId)
				return subCategories.get(x);
		}
		return null;
	}

	public List<SubCategory> getSubCategoriesByCategory(Integer categoryId) {
		List<Category> categories = StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		for (int x = 0; x < categories.size(); x++) {
			if (categories.get(x).getCategoryId() == categoryId)
				return categories.get(x).getSubCategories();
		}
		return null;
	}

}
