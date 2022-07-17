package com.example.springboottrello.repository;

import com.example.springboottrello.entities.Folders;
import com.example.springboottrello.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TasksRepository extends JpaRepository<Tasks, Long> {
    @Query("SELECT c FROM Tasks c WHERE c.folder.id = :folder_id ORDER BY c.id")
    List<Tasks> findFolderAllTasks(Long folder_id);

    Long deleteByFolder(Folders folders);
}