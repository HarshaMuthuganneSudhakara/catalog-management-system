package com.thippeshhirenallur.catalogmanagementplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thippeshhirenallur.catalogmanagementplatform.entity.SKU;
import com.thippeshhirenallur.catalogmanagementplatform.service.SkuService;

@RestController
@RequestMapping("/skus")
public class SkuController {
	
	private final SkuService skuService;


	@Autowired
	public SkuController(SkuService skuService) {
		super();
		this.skuService = skuService;
	}

	@GetMapping(value = "/{categoryId}/{subCategoryId}/{productId}")
	public ResponseEntity<List<SKU>> getSKUs(@PathVariable final Integer categoryId, @PathVariable final Integer subCategoryId,@PathVariable final Integer productId) {
		List<SKU> skus = skuService.getSKUsByProductSubCategoryAndCategory(categoryId, subCategoryId, productId);
		return new ResponseEntity<>(skus, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{categoryId}/{subCategoryId}/{productId}")
	public ResponseEntity<SKU> addSKU(@RequestBody final SKU sku,@PathVariable final Integer categoryId, @PathVariable final Integer subCategoryId,@PathVariable final Integer productId ) {
		SKU skuAdded = skuService.addSKU(categoryId, subCategoryId, productId, sku);
		return new ResponseEntity<>(skuAdded, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{categoryId}/{subCategoryId}/{productId}/{skuId}")
	public ResponseEntity<SKU> updateMedia(@PathVariable final Integer categoryId, @PathVariable final Integer subCategoryId,@PathVariable final Integer productId,@PathVariable final Integer skuId,
			@RequestBody final SKU sku) {
		SKU skuToUpdate = skuService.updateSKU(categoryId, subCategoryId, productId, skuId, sku);
		return new ResponseEntity<>((skuToUpdate), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{categoryId}/{subCategoryId}/{productId}/{skuId}")
	public ResponseEntity<SKU> deleteMedia(@PathVariable final Integer categoryId,@PathVariable final Integer subCategoryId,@PathVariable final Integer productId,@PathVariable final Integer skuId) {
		SKU skuDeleted = skuService.deleteSKU(categoryId, subCategoryId, productId, skuId);
		return new ResponseEntity<>((skuDeleted), HttpStatus.OK);
	}
}
