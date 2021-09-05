package com.gabrielmaia.develcode.infrastructure.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielmaia.develcode.domain.exception.PictureNotFound;

@Service
public class UserPictureStorageService {

	@Value("${spring.web.resources.static-locations}")
	private Path diretorioPath;

	private Path getFilePath(String nameFile) {
		return diretorioPath.resolve(Path.of(nameFile));
	}

	public void storagePictureInDisk(MultipartFile foto, String nameFile) {
		Path arquivoPath = getFilePath(nameFile);
		try {
			foto.transferTo(arquivoPath);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream recoverPictureInDisk(String nameFile) {
		Path arquivoPath = getFilePath(nameFile);

		try {
			InputStream pictureRecover = Files.newInputStream(arquivoPath);
			return pictureRecover;
		} catch (Exception e) {
			throw new PictureNotFound("Não foi possível recuperar arquivo.");
		}
	}

	public void deletePictureInDisk(String nameFile) throws IOException {
		Path arquivoPath = getFilePath(nameFile);
		File picture = new File(arquivoPath.toString());
		picture.delete();
	}
}