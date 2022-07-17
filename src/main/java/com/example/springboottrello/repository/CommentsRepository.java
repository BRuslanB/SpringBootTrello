package com.example.springboottrello.repository;

import com.example.springboottrello.entities.Comments;
import com.example.springboottrello.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Long> {

//    @Query("SELECT c FROM Comments c WHERE c.task.id = :task_id ORDER BY c.id")
//    List<Comments> findTaskAllComments(Long task_id);
}