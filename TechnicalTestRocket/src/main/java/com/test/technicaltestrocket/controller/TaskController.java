package com.test.technicaltestrocket.controller;


import com.test.technicaltestrocket.model.Task;
import com.test.technicaltestrocket.repository.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public String listTasks(Model model) {
        List<Task> tasks = taskMapper.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }


    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping
    public String addTask(@Valid @ModelAttribute("task") Task task, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "add-task";
        }
        taskMapper.insert(task);
        return "redirect:/tasks";
    }


}
