package com.example.controllers;


import com.example.entity.Task;
import com.example.services.TaskServices;
import com.example.services.UpdateEntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private TaskServices service;
    @Autowired
    private UpdateEntityStatus updateEntityStatus;

    /**
     * @getById метод получения задачи
     * * @throw ошибка, если пытаемся ввести не UUID
     * @return  - возврат времени и стутуса задачи
     **/



    @GetMapping("/{id}")
    public Optional<Task> getById (@PathVariable UUID id) {
        Optional<Task> optionalTask;
        try {
            optionalTask = service.getById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return optionalTask;
    }

    /**
     * @addTask метод создания задачи
     * @return  - возврат только id
     **/
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID addTask(@RequestBody Task task) {
        service.add(task);
        updateEntityStatus.UpdateStatus(task);
        return task.getId();
    }


}
