package com.example.springboottrello.repository;

import com.example.springboottrello.entities.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskCategoriesRepository extends JpaRepository<TaskCategories, Long> {
    @Query("SELECT c FROM TaskCategories c ORDER BY c.name")
    List<TaskCategories> findAllSortByName();
}