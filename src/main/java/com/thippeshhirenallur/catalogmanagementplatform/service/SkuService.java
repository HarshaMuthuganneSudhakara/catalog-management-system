package com.thippeshhirenallur.catalogmanagementplatform.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.thippeshhirenallur.catalogmanagementplatform.entity.SKU;

@Service
public interface SkuService {
	public List<SKU> getSKUsByProductSubCategoryAndCategory(Integer categoryId, Integer subCategoryId,
			Integer productId);

	public SKU addSKU(Integer categoryId, Integer subCategoryId, Integer productId, SKU sku);

	@Transactional
	public SKU updateSKU(Integer categoryId, Integer subCategoryId, Integer productId, Integer skuId,
			SKU sku);

	public SKU deleteSKU(Integer categoryId, Integer subCategoryId, Integer productId, Integer skuId);
	
}
