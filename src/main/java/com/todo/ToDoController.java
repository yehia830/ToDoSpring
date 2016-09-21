package com.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yehia830 on 9/15/16.
 */
@Controller
public class ToDoController {

    @Autowired
    ToDoRepository todos;

    @Autowired
    UserRepository users;


    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            User user = new User();
            user.name = "yehia";
            user.password = "ABSdullah";
            users.save(user);
        }
    }



    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }

        Iterable<ToDo> allTodos = todos.findAll();
        List<ToDo> toDoList = new ArrayList<>();
        for (ToDo currentToDo : allTodos) {
            toDoList.add(currentToDo);
        }
        model.addAttribute("todos", toDoList);

        return "home";
    }
    @RequestMapping(path="/add-todo", method = RequestMethod.POST)
    public String addToDo(HttpSession session, String toDoText) {
        User user = (User) session.getAttribute("user");
        ToDo myToDo = new ToDo(toDoText, user);
        todos.save(myToDo);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteToDo(Model model, Integer todoID) {
        if (todoID != null) {
            todos.delete(todoID);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, password);
            users.save(user);
        }
        else if (!password.equals(user.getPassword())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/todos", method = RequestMethod.GET)
    public String todos(Model model, HttpSession session) {
        return "todos";
    }






}
