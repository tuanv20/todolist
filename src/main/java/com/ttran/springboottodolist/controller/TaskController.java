package com.ttran.springboottodolist.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ttran.springboottodolist.model.Task;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

//Annotation to get around CORS policy issues
@CrossOrigin("localhost")
@RestController

// Controller Class that defines all of the endpoints for the tasklist CRUD operations 
// and directly calls the TaskService object to handle the business logic
public class TaskController{
    //Definition and Dependency injection for TaskService object 
    @Autowired
	private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    //Converts task into JSON format that is digestible by frontend 
    @ResponseBody
    public Object getTaskJson(Task task){
        HashMap<String, Object> taskMap = new HashMap<String, Object>();
        taskMap.put("id", task.getID());
        taskMap.put("task_item", task.getTaskItem());
        taskMap.put("due_date", task.getDueDate());
        taskMap.put("complete_date", task.getCompleteDate());
        return taskMap;
    }

    @PostMapping(value="/addTask", consumes=MediaType.APPLICATION_JSON_VALUE)   
    public ResponseEntity<?> addTask(@RequestBody Task task){
        Task addTask = new Task();
        addTask.setTaskItem(task.getTaskItem());
        addTask.setDueDate(task.getDueDate());
        addTask.setCompleteDate(task.getCompleteDate());
        addTask.setCompleted(task.getCompleted());
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

    @GetMapping(value="/getTasks/{searchStr}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTasks(@PathVariable Optional<String> searchStr){
        return ResponseEntity.ok(taskService.getAllTasks(searchStr.get()));
    }
    
    @GetMapping(value="/getTasks/", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks(""));
    }

    @GetMapping(value="/getCompletedTasks/{searchStr}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCompletedTasks(@PathVariable String searchStr){
        return ResponseEntity.ok(taskService.getAllCompletedTasks(searchStr));
    }

    @GetMapping(value="/getCompletedTasks/", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCompletedTasks(){
        return ResponseEntity.ok(taskService.getAllCompletedTasks(""));
    }

    @GetMapping(value="/getUnfinishedTasks/{searchStr}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUnfinishedTasks(@PathVariable String searchStr){
        return ResponseEntity.ok(taskService.getAllUnfinishedTasks(searchStr));
    }

    @GetMapping(value="/getUnfinishedTasks/", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUnfinishedTasks(){
        return ResponseEntity.ok(taskService.getAllUnfinishedTasks(""));
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
