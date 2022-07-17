package com.example.springboottrello.controllers;

import com.example.springboottrello.entities.*;
import com.example.springboottrello.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TaskServices taskServices;

    @GetMapping(value = "/")
    public String indexPage(Model model){

        List<Folders> folders = taskServices.getAllFolders();
        model.addAttribute("folders", folders);

        return "index";
    }

    @PostMapping(value = "/addfolder")
    public String addFolder(@RequestParam(name = "folder_name") String folderName){

        Folders folder = new Folders();
        folder.setName(folderName);
        taskServices.addFolder(folder);

        return "redirect:/";
    }

    @PostMapping(value = "/editfolder")
    public String editFolder(@RequestParam(name = "folder_id") Long folderId,
                             @RequestParam(name = "folder_name") String folderName){

        Folders folder = taskServices.getFolder(folderId);

        if (folder != null) {
            folder.setName(folderName);
            taskServices.saveFolder(folder);
        }

        return "redirect:/";
    }

    @PostMapping(value = "/deletefolder")
    public String deleteFolder(@RequestParam(name = "folder_id") Long folderId){

        Folders folder = taskServices.getFolder(folderId);

        if (folder != null) {
            taskServices.deleteFolder(folder);
        }

        return "redirect:/";
    }

    @GetMapping(value = "/detailsfolder/{id}")
    public String detailsFolder(@PathVariable(name="id") Long folderId, Model model){

        Folders folder = taskServices.getFolder(folderId);
        model.addAttribute("folder", folder);

        List<TaskCategories> folderCategories = folder.getTaskCategories();
        model.addAttribute("folderCategories", folderCategories);

        List<TaskCategories> not_folderCategories = taskServices.getAllTaskCategories();
        if (not_folderCategories != null && folderCategories != null){
            not_folderCategories.removeAll(folderCategories);
        }
        model.addAttribute("not_folderCategories", not_folderCategories);

        List<TaskStatuses> taskStatuses = taskServices.getAllTaskStatuses();
        model.addAttribute("taskStatuses", taskStatuses);

        List<Tasks> folderTasks = taskServices.getFolderTasks(folderId);
        model.addAttribute("folderTasks", folderTasks);

        List<Comments> comments = taskServices.getAllComments();
        model.addAttribute("comments", comments);

        return "detailsfolder";
    }

    @GetMapping(value = "/detailscategory")
    public String detailsCategory(Model model){

        List<TaskCategories> taskCategories = taskServices.getAllTaskCategories();
        model.addAttribute("taskCategories", taskCategories);

        return "detailscategory";
    }

    @PostMapping(value = "/addcategory")
    public String addCategory(@RequestParam(name = "category_name") String categoryName){

        TaskCategories taskCategory = new TaskCategories();
        taskCategory.setName(categoryName);

        taskServices.addTaskCategory(taskCategory);

        return "redirect:/detailscategory";
    }

    @PostMapping(value = "/editcategory")
    public String editCategory(@RequestParam(name = "category_id") Long categoryId,
                               @RequestParam(name = "category_name") String categoryName){

        TaskCategories taskCategory = taskServices.getTaskCategory(categoryId);

        if (taskCategory != null) {
            taskCategory.setName(categoryName);
            taskServices.saveTaskCategory(taskCategory);
        }

        return "redirect:/detailscategory";
    }

    @PostMapping(value = "/deletecategory")
    public String deleteCategory(@RequestParam(name = "category_id") Long categoryId){

        TaskCategories taskCategory = taskServices.getTaskCategory(categoryId);
        List<Folders> folders = taskServices.getAllFolders();

        if (taskCategory != null) {

            for (Folders fld : folders) {
                List<TaskCategories> taskCategories = fld.getTaskCategories();

                if (taskCategories != null){
                    taskCategories.remove(taskCategory);
                }

                fld.setTaskCategories(taskCategories);
                taskServices.saveFolder(fld);
            }
            taskServices.deleteTaskCategory(taskCategory);
        }

        return "redirect:/detailscategory";
    }

    @PostMapping(value = "/assigncategory")
    public String assignCategory(@RequestParam(name = "folder_id") Long folderId,
                                 @RequestParam(name = "category_id") Long categoryId){

        Folders folder = taskServices.getFolder(folderId);
        TaskCategories taskCategory = taskServices.getTaskCategory(categoryId);

        if (folder != null && taskCategory != null){

            List<TaskCategories> taskCategories = folder.getTaskCategories();

            if (taskCategories == null)
                taskCategories = new ArrayList<>();

            taskCategories.add(taskCategory);

            taskServices.saveFolder(folder);
        }
        return "redirect:/detailsfolder/" + folderId;
    }

    @PostMapping(value = "/removecategory")
    public String removeCategory(@RequestParam(name = "folder_id") Long folderId,
                                 @RequestParam(name = "category_id") Long categoryId){

        Folders folder = taskServices.getFolder(folderId);
        TaskCategories taskCategory = taskServices.getTaskCategory(categoryId);

        if (folder != null && taskCategory != null){

            List<TaskCategories> taskCategories = folder.getTaskCategories();

            if (taskCategories != null)
                taskCategories.remove(taskCategory);

            taskServices.saveFolder(folder);
        }
        return "redirect:/detailsfolder/" + folderId;
    }

    @PostMapping(value = "/addtask")
    public String addTask(@RequestParam(name = "folder_id") Long folderId,
                          @RequestParam(name = "task_title") String taskTitle,
                          @RequestParam(name = "task_description") String taskDescription){

        Tasks task = new Tasks();
        Folders folder = taskServices.getFolder(folderId);
        List<TaskStatuses> taskStatuses = taskServices.getAllTaskStatuses();

        if (folder != null && taskStatuses != null){

            for (TaskStatuses sts : taskStatuses) {
                if (sts.getName().equalsIgnoreCase("TO DO")) {
                    task.setTaskStatus(sts);
                }
            }

            task.setTitle(taskTitle);
            task.setDescription(taskDescription);
            task.setFolder(folder);

            taskServices.saveTask(task);
        }
        return "redirect:/detailsfolder/" + folderId;
    }

    @PostMapping(value = "/edittask")
    public String editTask(@RequestParam(name = "folder_id") Long folderId,
                           @RequestParam(name = "task_id") Long taskId,
                           @RequestParam(name = "task_title") String taskTitle,
                           @RequestParam(name = "task_description") String taskDescription,
                           @RequestParam(name = "task_status_id") Long statusId){

        Tasks task = taskServices.getTask(taskId);
        Folders folder = taskServices.getFolder(folderId);
        TaskStatuses taskStatus = taskServices.getTaskStatus(statusId);

        if (folder != null && taskStatus != null && task != null){

            task.setTitle(taskTitle);
            task.setDescription(taskDescription);
            task.setTaskStatus(taskStatus);
            task.setFolder(folder);

            taskServices.saveTask(task);
        }
        return "redirect:/detailsfolder/" + folderId;
    }

    @PostMapping(value = "/deletetask")
    public String deleteTask(@RequestParam(name = "folder_id") Long folderId,
                             @RequestParam(name = "task_id") Long taskId){

        Tasks task = taskServices.getTask(taskId);

        if (task != null) {
            taskServices.deleteTask(task);
        }

        return "redirect:/detailsfolder/" + folderId;
    }

    @PostMapping(value = "/addcomment")
    public String editTask(@RequestParam(name = "folder_id") Long folderId,
                           @RequestParam(name = "task_id") Long taskId,
                           @RequestParam(name = "task_comment") String taskComment){

        Tasks task = taskServices.getTask(taskId);
        Comments comment = new Comments();

        if (task != null){

            comment.setComment(taskComment);
            comment.setTask(task);

            taskServices.saveComment(comment);
        }
        return "redirect:/detailsfolder/" + folderId;
    }
}