package com.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Yehia830 on 9/19/16.
 */
public class ToDoJsonController {

    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping(path = "/todos.json" , method = RequestMethod.GET)
    public ArrayList getTodos(){
        System.out.println("hello");
        ArrayList<ToDo> toDoArrayList = new ArrayList<ToDo>();
        Iterable<ToDo> allTodos = toDoRepository.findAll();
        for(ToDo toDo : allTodos) {
            toDoArrayList.add(toDo);
        }

        return toDoArrayList;
    }
    @RequestMapping(path = "/toggletodo.json", method = RequestMethod.GET)
    public ArrayList<ToDo> toggleToDo(int todoID) {
        System.out.println("toggling todo with ID " + todoID );
        ToDo todo = toDoRepository.findOne(todoID);
        todo.text = "**" + todo.text;
        toDoRepository.save(todo);

        return getTodos();
    }

//    @RequestMapping(path = "/addGame.json", method = RequestMethod.POST)
//    public ArrayList<ToDo> addGame(HttpSession session, @RequestBody ToDo toDo) throws Exception {
//        User user = (User)session.getAttribute("user");
//
//        if (user == null) {
//            throw new Exception("Unable to add game without an active user in the session");
//        }
//        toDo.user = user;
//
//        toDo.save(game);
//
//        return getAllGames();
//    }













}
