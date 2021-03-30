package com.thippeshhirenallur.catalogmanagementplatform.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thippeshhirenallur.catalogmanagementplatform.entity.Category;
import com.thippeshhirenallur.catalogmanagementplatform.entity.Product;
import com.thippeshhirenallur.catalogmanagementplatform.entity.SKU;
import com.thippeshhirenallur.catalogmanagementplatform.entity.SubCategory;
import com.thippeshhirenallur.catalogmanagementplatform.repository.CategoryRepository;
import com.thippeshhirenallur.catalogmanagementplatform.repository.ProductRepository;
import com.thippeshhirenallur.catalogmanagementplatform.repository.SkuRepository;
import com.thippeshhirenallur.catalogmanagementplatform.repository.SubCategoryRepository;

@Service
public class SkuServiceImpl implements SkuService{
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SkuRepository skuRepository;
	
	public List<SKU> getSKUsByProductSubCategoryAndCategory(Integer categoryId, Integer subCategoryId,
			Integer productId) {
		List<Product> products = getProductsBySubCategoryAndCategory(categoryId, subCategoryId);
		Product product = getProduct(productId,products);
		return product.getSkus();
	}

	
	public SKU addSKU(Integer categoryId, Integer subCategoryId, Integer productId, SKU sku) {
		return skuRepository.save(sku);
	}

	
	public SKU updateSKU(Integer categoryId, Integer subCategoryId, Integer productId, Integer skuId,
			SKU sku) {
		List<SKU> skus = getSKUsByProductSubCategoryAndCategory(categoryId, subCategoryId,productId);
		SKU skuToUpdate = getSKU(skus, skuId);
		skuToUpdate.setDescription(sku.getDescription());
		skuToUpdate.setInventoryType(sku.getInventoryType());
		skuToUpdate.setName(sku.getName());
		skuToUpdate.setProductId(sku.getProductId());
		skuToUpdate.setQuantityAvailable(sku.getQuantityAvailable());
		skuToUpdate.setRetailPrice(sku.getRetailPrice());
		skuToUpdate.setSalePrice(sku.getSalePrice());
		skuToUpdate.setSkuId(sku.getSkuId());
		
		return skuToUpdate;
	}

	
	private SKU getSKU(List<SKU> skus, Integer skuId) {
		for (int x = 0; x < skus.size(); x++) {
			if (skus.get(x).getSkuId() == skuId)
				return skus.get(x);
		}
		return null;
	}


	public SKU deleteSKU(Integer categoryId, Integer subCategoryId, Integer productId, Integer skuId) {
		List<SKU> skus = getSKUsByProductSubCategoryAndCategory(categoryId, subCategoryId,productId);
		SKU skuToDelete = getSKU(skus, skuId);
		if (skuToDelete != null)
			skuRepository.delete(skuToDelete);
		return skuToDelete;
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
		SubCategory subCategory = getSubCategory(subCategoryId, subCategories);
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
