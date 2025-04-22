package com.kannanrameshrk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/todo")
public class TodoController {
	@GetMapping
	public String show_todo_list(Model model,HttpSession session) {
		
		@SuppressWarnings("unchecked")
		List<String> tasks=(List<String>) session.getAttribute("tasks");
		
		if(tasks==null) {
			tasks=new ArrayList<>();
			session.setAttribute("tasks", tasks);
		}
		model.addAttribute("tasks", tasks);
		return "todo";
	}
	
	@PostMapping("/add")
	public String add_todo_list(@RequestParam String task,HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> tasks=(List<String>) session.getAttribute("tasks");
		
		if(tasks==null) {
			tasks=new ArrayList<>();
			session.setAttribute("tasks", tasks);
		}
		tasks.add(task);
		return "redirect:/todo";
	}
	@PostMapping("/delete")
	public String delete_todo_list(@RequestParam int index,HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> tasks=(List<String>) session.getAttribute("tasks");
		
		tasks.remove(index);
		return "redirect:/todo";
	}
	@PostMapping("/deleteall")
	public String deleteall_todo_list(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> tasks=(List<String>) session.getAttribute("tasks");
		
		tasks.clear();
		return "redirect:/todo";
	}
}
