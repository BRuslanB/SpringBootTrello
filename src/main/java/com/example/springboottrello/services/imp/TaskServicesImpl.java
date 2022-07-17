package com.example.springboottrello.services.imp;

import com.example.springboottrello.entities.*;
import com.example.springboottrello.repository.*;
import com.example.springboottrello.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServicesImpl implements TaskServices {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private FoldersRepository foldersRepository;

    @Autowired
    private TaskCategoriesRepository taskCategoriesRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private TaskStatusesRepository taskStatusesRepository;

    @Override
    public Comments addComment(Comments comment) {
        return commentsRepository.save(comment);
    }

    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

//    @Override
//    public List<Comments> getTaskComments(Long id) {
//        return commentsRepository.findTaskAllComments(id);
//    }

    @Override
    public Comments getComment(Long id) {
        return commentsRepository.getReferenceById(id);
    }

    @Override
    public Comments saveComment(Comments comment) {
        return commentsRepository.save(comment);
    }

    @Override
    public void deleteComment(Comments comment) {
        commentsRepository.delete(comment);
    }

    @Override
    public Folders addFolder(Folders folder) {
        return foldersRepository.save(folder);
    }

    @Override
    public List<Folders> getAllFolders() {
        return foldersRepository.findAllSortByName();
    }

    @Override
    public Folders getFolder(Long id) {
        return foldersRepository.getReferenceById(id);
    }

    @Override
    public Folders saveFolder(Folders folder) {
        return foldersRepository.save(folder);
    }

    @Override
    public void deleteFolder(Folders folder) {
        tasksRepository.deleteByFolder(folder);
        foldersRepository.delete(folder);
    }

    @Override
    public TaskCategories addTaskCategory(TaskCategories taskCategory) {
        return taskCategoriesRepository.save(taskCategory);
    }

    @Override
    public List<TaskCategories> getAllTaskCategories() {
        return taskCategoriesRepository.findAllSortByName();
    }

    @Override
    public TaskCategories getTaskCategory(Long id) {
        return taskCategoriesRepository.getReferenceById(id);
    }

    @Override
    public TaskCategories saveTaskCategory(TaskCategories taskCategory) {
        return taskCategoriesRepository.save(taskCategory);
    }

    @Override
    public void deleteTaskCategory(TaskCategories taskCategory) {
        taskCategoriesRepository.delete(taskCategory);
    }

//    public List<TaskCategories> removeTaskCategoryFromFolder(TaskCategories taskCategory) {
//        return foldersRepository.removeByTaskCategories(taskCategory);
//    }

    @Override
    public Tasks addTask(Tasks task) {
        return tasksRepository.save(task);
    }

    @Override
    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    @Override
    public List<Tasks> getFolderTasks(Long id) {
        return tasksRepository.findFolderAllTasks(id);
    }

    @Override
    public Tasks getTask(Long id) {
        return tasksRepository.getReferenceById(id);
    }

    @Override
    public Tasks saveTask(Tasks task) {
        return tasksRepository.save(task);
    }

    @Override
    public void deleteTask(Tasks task) {
        tasksRepository.delete(task);
    }

    @Override
    public TaskStatuses addTaskStatus(TaskStatuses taskStatus) {
        return taskStatusesRepository.save(taskStatus);
    }

    @Override
    public List<TaskStatuses> getAllTaskStatuses() {
        return taskStatusesRepository.findAll();
    }

    @Override
    public TaskStatuses getTaskStatus(Long id) {
        return taskStatusesRepository.getReferenceById(id);
    }

    @Override
    public TaskStatuses saveTaskStatus(TaskStatuses taskStatus) {
        return taskStatusesRepository.save(taskStatus);
    }

    @Override
    public void deleteTaskStatus(TaskStatuses taskStatus) {
        taskStatusesRepository.delete(taskStatus);
    }
}