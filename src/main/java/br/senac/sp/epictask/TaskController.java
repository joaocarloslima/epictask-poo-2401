package br.senac.sp.epictask;

import java.util.List;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskRepository repository;

    @Autowired
    OpenAiChatClient gpt;
    
    @GetMapping
    public String index(Model model,@AuthenticationPrincipal OAuth2User user){
        List<Task> tasks = repository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);
        return "task/index";
    }

    @PostMapping("/delete/{id}")
    public String destroy(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/task";
    }

    @GetMapping("new")
    public String form(Task task){
        return "task/new";
    }

    @PostMapping
    public String create(@Valid Task task, BindingResult result){
        if(result.hasErrors()) return "task/new";
        
        if (task.getDescription() == null || task.getDescription().isBlank()){
            task.setDescription(gpt.call(
                "Crie a descrição de uma tarefa com o título " 
                + task.getTitle() + 
                ". A descrição não pode ter mais do que 200 carecteres"
            ));
        }

        repository.save(task);
        return "redirect:/task";
    }




}
