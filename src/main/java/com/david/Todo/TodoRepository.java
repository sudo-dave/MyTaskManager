package com.david.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
// this whre you put custom querys, transccations and such
    // Repo is like the al the intefaces you can use for the service/biz layer
    // Defind find interface methods, JPA dose the querys for you
}

