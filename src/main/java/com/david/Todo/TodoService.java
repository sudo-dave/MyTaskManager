package com.david.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }
    public Optional<Todo> findTodo(long id){
        return todoRepository.findById(id);
    }
    public void deleteAllTodos(){todoRepository.deleteAll();}
    public void deleteTodo(long id){todoRepository.deleteById(id);}
    public void saveTodo(Todo todo){todoRepository.save(todo);}
}
