package com.gabrielmaia.develcode.api.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielmaia.develcode.domain.exception.EntityNotFound;
import com.gabrielmaia.develcode.domain.model.UserPicture;
import com.gabrielmaia.develcode.domain.service.UserPictureServiceRegistration;

@RestController
@RequestMapping(value = "/users/{usersId}/foto")
public class UserPictureController {

	@Autowired
	private UserPictureServiceRegistration pictureService;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> savePicture(@PathVariable Long usersId, @RequestParam MultipartFile foto) {
		try {
			UserPicture userPicture = pictureService.save(usersId, foto);
			return ResponseEntity.status(HttpStatus.CREATED).body(userPicture);
		} catch (EntityNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updatePicture(@PathVariable Long usersId, @RequestParam MultipartFile foto) {
		try {
			UserPicture userPicture = pictureService.save(usersId, foto);
			return ResponseEntity.ok(userPicture);
		} catch (EntityNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<?> remove(@PathVariable Long usersId) {
		try {
			pictureService.delete(usersId);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> viewPictureJPEG(@PathVariable Long usersId) {
		InputStream picture = pictureService.search(usersId);
		InputStreamResource recoverPicture = new InputStreamResource(picture);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(recoverPicture);
	}

	@GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<InputStreamResource> viewPicturePNG(@PathVariable Long usersId) {
		InputStream picture = pictureService.search(usersId);
		InputStreamResource recoverPicture = new InputStreamResource(picture);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(recoverPicture);
	}

	@GetMapping
	public ResponseEntity<UserPicture> viewPictureJSON(@PathVariable Long usersId) {
		UserPicture picture = pictureService.searchJson(usersId);
		return picture != null ? ResponseEntity.ok(picture) : ResponseEntity.notFound().build();
	}

}