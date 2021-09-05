package com.gabrielmaia.develcode.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gabrielmaia.develcode.domain.exception.EntityNotFound;
import com.gabrielmaia.develcode.domain.model.User;
import com.gabrielmaia.develcode.domain.model.UserPicture;
import com.gabrielmaia.develcode.domain.repository.UserPictureRepository;
import com.gabrielmaia.develcode.domain.repository.UserRepository;

@Service
public class UserServiceRegistration {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPictureRepository pictureRepository;

	@Autowired
	private UserPictureServiceRegistration pictureService;

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(Long id) {
		try {
			Optional<UserPicture> picture = pictureRepository.findById(id);
			if (picture.isPresent()) {
				pictureService.delete(id);
			}
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFound(String.format("This user code: %d, does not exist.", id));

		}
	}

}