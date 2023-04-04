package com.david;
import com.david.Todo.Todo;
import com.david.Todo.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
    @Bean
    CommandLineRunner commandLineRunner(
            TodoRepository todoRepository) {
        return args -> {
            LocalDateTime now = LocalDateTime.now();
            Todo test = new Todo(now,"yoo",true);
            todoRepository.save(test);
        };
    }
}
