package com.example.springboottrello.repository;

import com.example.springboottrello.entities.TaskStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskStatusesRepository extends JpaRepository<TaskStatuses, Long> {

}