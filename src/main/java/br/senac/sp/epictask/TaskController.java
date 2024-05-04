package br.senac.sp.epictask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskRepository repository;
    
    @GetMapping
    public String index(Model model){
        List<Task> tasks = repository.findAll();
        model.addAttribute("tasks", tasks);
        return "task/index";
    }


}
