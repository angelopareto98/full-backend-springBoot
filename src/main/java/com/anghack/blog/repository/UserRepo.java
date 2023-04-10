package com.anghack.blog.repository;

import com.anghack.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anaghack
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
