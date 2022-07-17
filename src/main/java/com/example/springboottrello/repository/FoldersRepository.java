package com.example.springboottrello.repository;

import com.example.springboottrello.entities.Folders;
import com.example.springboottrello.entities.TaskCategories;
import com.example.springboottrello.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FoldersRepository extends JpaRepository<Folders, Long> {
    @Query("SELECT c FROM Folders c ORDER BY c.name")
    List<Folders> findAllSortByName();

//    List<TaskCategories> removeByTaskCategories(List<TaskCategories> taskCategories);
}