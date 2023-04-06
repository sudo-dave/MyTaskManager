package com.david;
import com.david.Todo.Todo;
import com.david.Todo.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
    @Bean
    CommandLineRunner commandLineRunner(
            TodoRepository todoRepository) {
        return args -> {
            List<Todo> todoList = new ArrayList<>();
            LocalDateTime now = LocalDateTime.now();
            Todo test = new Todo(now,"Hello",true);
            todoList.add(test);
            Todo test2 = new Todo(now,"World",false);
            todoList.add(test2);
            todoRepository.saveAll(todoList);
        };
    }
}
