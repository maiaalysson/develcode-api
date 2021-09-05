package com.gabrielmaia.develcode.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielmaia.develcode.domain.exception.EntityNotFound;
import com.gabrielmaia.develcode.domain.function.MergeObjects;
import com.gabrielmaia.develcode.domain.model.User;
import com.gabrielmaia.develcode.domain.repository.UserRepository;
import com.gabrielmaia.develcode.domain.service.UserServiceRegistration;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserServiceRegistration userRegistration;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MergeObjects merge;

	@GetMapping
	public List<User> all() {
		return userRepository.findAll();
	}

	@GetMapping("/{usersId}")
	public ResponseEntity<User> search(@PathVariable Long usersId) {
		Optional<User> user = userRepository.findById(usersId);
		return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> add(@Validated @RequestBody User user) {
		user = userRegistration.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/{usersId}")
	public ResponseEntity<User> update(@PathVariable Long usersId, @Validated @RequestBody User user) {
		Optional<User> currentUser = userRepository.findById(usersId);

		if (currentUser.isPresent()) {
			BeanUtils.copyProperties(user, currentUser.get(), "id");
			User updateUser = userRegistration.save(currentUser.get());
			return ResponseEntity.ok(updateUser);
		}

		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/{usersId}")
	public ResponseEntity<User> partialUpdate(@PathVariable Long usersId, @RequestBody User user) {
		Optional<User> currentUser = userRepository.findById(usersId);

		if (currentUser.isPresent()) {
			merge.objects(user, currentUser.get());
			User mergeUser = userRegistration.save(currentUser.get());
			return ResponseEntity.ok(mergeUser);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{usersId}")
	public ResponseEntity<?> remove(@PathVariable Long usersId) {
		try {
			userRegistration.delete(usersId);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}
}