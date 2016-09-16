package com.todo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Yehia830 on 9/15/16.
 */
public interface ToDoRepository extends CrudRepository<ToDo, Integer> {
}

