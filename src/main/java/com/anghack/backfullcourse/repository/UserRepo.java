package com.anghack.backfullcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anghack.backfullcourse.entity.User;

/**
 *
 * @author anaghack
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
