package com.poli.paginator.controller;

import com.poli.paginator.models.ProcessModel;
import com.poli.paginator.models.TaskModel;
import com.poli.paginator.repository.ProcessRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

    public List<ProcessModel> processModelList;
    public ProcessRepository processRepository;

    public TaskController (){
        this.processRepository = new ProcessRepository();
        this.processModelList = new ArrayList() {};
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ProcessModel> getAll() {
        return processRepository.getProcessList();
    }

    @GetMapping(path = "/{state}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ProcessModel> getByState(@PathVariable("state") String state) {
        return processRepository.GetByState(state);
    }

    @PutMapping()
    public TaskModel update(@RequestBody ProcessModel obj) {
        return processRepository.update(obj);
    }

    @DeleteMapping("/{id}")
    public TaskModel remove(@PathVariable("id") int id) {
        return processRepository.remove(id);
    }

}