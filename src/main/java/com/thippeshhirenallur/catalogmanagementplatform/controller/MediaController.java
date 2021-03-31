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

import com.thippeshhirenallur.catalogmanagementplatform.entity.Media;
import com.thippeshhirenallur.catalogmanagementplatform.exception.ResourceNotFoundException;
import com.thippeshhirenallur.catalogmanagementplatform.service.MediaService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/medias")
@Api(tags= {"Media API's"} ) 
public class MediaController {
	
	private final MediaService mediaService;

	@Autowired
	public MediaController(MediaService mediaService) {
		super();
		this.mediaService = mediaService;
	}

	@GetMapping(value = "/{categoryId}/{subCategoryId}/{productId}")
	public ResponseEntity<List<Media>> getMedias(@PathVariable final Integer categoryId, @PathVariable final Integer subCategoryId,@PathVariable final Integer productId) {
		List<Media> medias = mediaService.getMediasByProductSubCategoryAndCategory(categoryId, subCategoryId, productId);
		if(medias == null)
			throw new ResourceNotFoundException("Medias Not Found");
		return new ResponseEntity<>(medias, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{categoryId}/{subCategoryId}/{productId}")
	public ResponseEntity<Media> addMedia(@RequestBody final Media media,@PathVariable final Integer categoryId, @PathVariable final Integer subCategoryId,@PathVariable final Integer productId ) {
		Media mediaAdded = mediaService.addMedia(categoryId, subCategoryId, productId, media);
		return new ResponseEntity<>(mediaAdded, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{categoryId}/{subCategoryId}/{productId}/{mediaId}")
	public ResponseEntity<Media> updateMedia(@PathVariable final Integer categoryId, @PathVariable final Integer subCategoryId,@PathVariable final Integer productId,@PathVariable final Integer mediaId,
			@RequestBody final Media media) {
		Media mediaToUpdate = mediaService.updateMedia(categoryId, subCategoryId, productId, mediaId, media);
		return new ResponseEntity<>((mediaToUpdate), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{categoryId}/{subCategoryId}/{productId}/{mediaId}")
	public ResponseEntity<Media> deleteMedia(@PathVariable final Integer categoryId,@PathVariable final Integer subCategoryId,@PathVariable final Integer productId,@PathVariable final Integer mediaId) {
		Media mediaDeleted = mediaService.deleteMedia(categoryId, subCategoryId, productId, mediaId);
		return new ResponseEntity<>((mediaDeleted), HttpStatus.OK);
	}
}
