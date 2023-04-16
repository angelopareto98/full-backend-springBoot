package com.anghack.backfullcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anghack.backfullcourse.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
