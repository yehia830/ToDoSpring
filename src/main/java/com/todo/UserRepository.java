package com.todo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Yehia830 on 9/16/16.
 */

public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByName(String name);
}
