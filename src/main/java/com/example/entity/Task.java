package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Task {
    /**
     * @param id первичный ключ
     * @param timestamp - время создания/измененения задачи
     * @param status - состояние задачи
     **/

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column
    @org.hibernate.annotations.CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status = Status.CREATED;



    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        if (!(timestamp instanceof LocalDateTime))
            throw new IllegalArgumentException ("объект не является датой");
        this.timestamp = timestamp;

    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }


    public enum Status {
        CREATED, RUNNING, FINISHED
    }
}
