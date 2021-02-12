package com.example.repository;


import com.example.entity.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.UUID;

public interface TaskRepository extends PagingAndSortingRepository<Task, UUID> {


}
