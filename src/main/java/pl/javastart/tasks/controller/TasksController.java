package pl.javastart.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.javastart.tasks.entity.Task;
import pl.javastart.tasks.repository.TasksRepository;

import javax.persistence.TypedQuery;
import java.util.List;

@Controller
public class TasksController {
    private TasksRepository tasksRepository;

    public TasksController(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/add")
    public String addTask(Model model){
        model.addAttribute("task", new Task());
        return "add";
    }

    @PostMapping("/saveTask")
    @ResponseBody
    public String saveTask(Task task) {
        tasksRepository.addTask(task);
        return "Zapisano";
    }

    @GetMapping("/todo")
    public String tasksToDo(Model model) {

        List toDoList = tasksRepository.toDoList();
        model.addAttribute("toDoList", toDoList);

        return "todo";
    }





}
