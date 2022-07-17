package com.example.springboottrello.services;

import com.example.springboottrello.entities.*;

import java.util.List;

public interface TaskServices {

    Comments addComment(Comments comment);
    List<Comments> getAllComments();
//    List<Comments> getTaskComments(Long id);
    Comments getComment(Long id);
    Comments saveComment (Comments comment);
    void deleteComment(Comments comment);

    Folders addFolder(Folders folder);
    List<Folders> getAllFolders();
    Folders getFolder(Long id);
    Folders saveFolder (Folders folder);
    void deleteFolder(Folders folder);

    TaskCategories addTaskCategory(TaskCategories taskCategory);
    List<TaskCategories> getAllTaskCategories();
    TaskCategories getTaskCategory(Long id);
    TaskCategories saveTaskCategory (TaskCategories taskCategory);
    void deleteTaskCategory(TaskCategories taskCategory);
//    List<TaskCategories> removeTaskCategoryFromFolder(TaskCategories taskCategory);

    Tasks addTask(Tasks task);
    List<Tasks> getAllTasks();
    List<Tasks> getFolderTasks(Long id);
    Tasks getTask(Long id);
    Tasks saveTask (Tasks task);
    void deleteTask(Tasks task);

    TaskStatuses addTaskStatus(TaskStatuses taskStatus);
    List<TaskStatuses> getAllTaskStatuses();
    TaskStatuses getTaskStatus(Long id);
    TaskStatuses saveTaskStatus (TaskStatuses taskStatus);
    void deleteTaskStatus(TaskStatuses taskStatus);
}