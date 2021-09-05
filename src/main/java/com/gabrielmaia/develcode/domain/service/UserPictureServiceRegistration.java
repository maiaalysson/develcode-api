package com.gabrielmaia.develcode.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielmaia.develcode.domain.exception.EntityNotFound;
import com.gabrielmaia.develcode.domain.exception.PictureNotFound;
import com.gabrielmaia.develcode.domain.model.User;
import com.gabrielmaia.develcode.domain.model.UserPicture;
import com.gabrielmaia.develcode.domain.repository.UserPictureRepository;
import com.gabrielmaia.develcode.domain.repository.UserRepository;
import com.gabrielmaia.develcode.infrastructure.service.UserPictureStorageService;

@Service
public class UserPictureServiceRegistration {

	@Autowired
	private UserPictureRepository picturesRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPictureStorageService pictureStorageService;

	public UserPicture save(Long usersId, MultipartFile foto) {
		if (foto.isEmpty())
			throw new EntityNotFound("Foto invalida!");

		Optional<User> user = userRepository.findById(usersId);
		if (user.isEmpty())
			throw new EntityNotFound("Usuario invalido!");

		try {
			Optional<UserPicture> picture = picturesRepository.findById(usersId);
			if (picture.isPresent())
				pictureStorageService.deletePictureInDisk(picture.get().getNomeArquivo());
		} catch (IOException e) {
			e.printStackTrace();
		}

		UserPicture userPicture = new UserPicture();

		userPicture.setId(user.get().getId());
		userPicture.setNomeArquivo(foto.getOriginalFilename());
		userPicture.setContentType(foto.getContentType());
		userPicture.setTamanho(foto.getSize());
		userPicture.setUser(user.get());

		pictureStorageService.storagePictureInDisk(foto, userPicture.getNomeArquivo());
		picturesRepository.save(userPicture);

		return userPicture;
	}

	public InputStream search(Long usersId) {
		Optional<UserPicture> picture = picturesRepository.findById(usersId);

		if (picture.isEmpty())
			throw new PictureNotFound("Foto não encontrada!");

		return pictureStorageService.recoverPictureInDisk(picture.get().getNomeArquivo());

	}

	public UserPicture searchJson(Long usersId) {
		Optional<UserPicture> picture = picturesRepository.findById(usersId);
		if (picture.isPresent()) {
			UserPicture userPicture = picture.get();
			return userPicture;
		} else {
			return null;
		}

	}

	public void delete(Long usersId) {
		try {
			Optional<UserPicture> picture = picturesRepository.findById(usersId);

			if (picture.isPresent())
				pictureStorageService.deletePictureInDisk(picture.get().getNomeArquivo());

			picturesRepository.deleteById(usersId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFound(String.format("This user code: %d, does not exist.", usersId));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}