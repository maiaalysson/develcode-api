package com.gabrielmaia.develcode.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielmaia.develcode.domain.model.UserPicture;

@Repository
public interface UserPictureRepository extends JpaRepository<UserPicture, Long> {

}