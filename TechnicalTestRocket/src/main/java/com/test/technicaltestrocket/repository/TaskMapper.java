package com.test.technicaltestrocket.repository;

import com.test.technicaltestrocket.model.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> findAll();
    void insert(Task task);
}