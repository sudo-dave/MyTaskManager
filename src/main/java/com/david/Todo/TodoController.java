package com.david.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Todos")
public class TodoController {

    //constructor dependency injection; why use that instead of 
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        try{
            List<Todo> todos= todoService.getAllTodos();
            if(todos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(todos,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        try{
            Todo newTodo = new Todo(todo.getCreatedAt(),todo.getText(), todo.getCompleted());
            todoService.saveTodo(newTodo);
            return new ResponseEntity<>(newTodo,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") long id,@RequestBody Todo todo){
        try{
            Optional<Todo> findTodo = todoService.findTodo(id);
            if(findTodo.isPresent()){
                Todo updatedTodo = findTodo.get();

                updatedTodo.setCompleted(todo.getCompleted());
                updatedTodo.setText(todo.getText());
                updatedTodo.setCreatedAt(todo.getCreatedAt());
                todoService.saveTodo(updatedTodo);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") long id){
        try{
            Optional<Todo> todo = todoService.findTodo(id);
            if (todo.isPresent()){
                return new ResponseEntity<>(todo.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTodos(){
        try{
            todoService.deleteAllTodos();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("id") long id){
        try{
            todoService.deleteTodo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
