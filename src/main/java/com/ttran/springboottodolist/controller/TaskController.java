package com.ttran.springboottodolist.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ttran.springboottodolist.model.Task;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
public class TaskController{
    @Autowired
	private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @ResponseBody
    public Object getTaskJson(Task task){
        HashMap<String, Object> taskMap = new HashMap<String, Object>();
        taskMap.put("id", task.getID());
        taskMap.put("task_item", task.getTaskItem());
        taskMap.put("due_date", task.getDueDate());
        taskMap.put("complete_date", task.getCompleteDate());
        return taskMap;
    }

    @GetMapping("/")
    public String homePage(){
        return "Welcome!";
    }

    @PostMapping(value="/addTask", consumes=MediaType.APPLICATION_JSON_VALUE)   
    public ResponseEntity<?> addTask(@RequestBody Task task){
        Task addTask = new Task();
        addTask.setTaskItem(task.getTaskItem());
        addTask.setDueDate(task.getDueDate());
        addTask.setCompleteDate(task.getCompleteDate());
        return taskService.addTask(addTask);
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<?> getTask(@PathVariable String id){
        Task reqTask = taskService.getTaskByID(Integer.parseInt(id));
        if(reqTask == null){
            return new ResponseEntity<String>("That task does not exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(getTaskJson(reqTask));
    }

    @GetMapping(value="/getTasks", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTasks(String id){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @DeleteMapping(value="/deleteTask/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id){
        if(taskService.idExists(Integer.parseInt(id))){
            taskService.deleteTask(Integer.parseInt(id));
            return ResponseEntity.ok("Task deleted successfully");
        }
        return new ResponseEntity<String>("Error Deleting Task", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteAllTasks")
    public ResponseEntity<?> deleteAllTasks(){
        taskService.deleteAllTasks();
        return ResponseEntity.ok("All tasks deleted successfully");
    }

    @PutMapping(value="/updateTask/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTask(@PathVariable String id, @RequestBody Task updateTask){
        return taskService.updateTask(Integer.parseInt(id), updateTask);
    }

}
