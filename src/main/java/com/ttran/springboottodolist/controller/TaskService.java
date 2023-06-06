package com.ttran.springboottodolist.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.ttran.springboottodolist.model.Task;
import com.ttran.springboottodolist.model.TaskRepo;


//Service class that handles business logic of endpoints. Either
//queries the MySQL database using JdbcTemplate object or directly 
//calls the TaskRepo JPARepository (for custom queries)
@Service
public class TaskService {
    //Dependency Injections 
    @Autowired
	private JdbcTemplate jt;
    private TaskRepo taskRepo;

    public TaskService(JdbcTemplate jt, TaskRepo taskRepo){
        this.jt = jt;
        this.taskRepo = taskRepo;
    }

    public boolean idExists(int id){
        return taskRepo.existsById(id);
    }

    public ResponseEntity<String> addTask(Task task){
        try{
            String addsql = "INSERT INTO tasks (task_item, due_date, complete_date, completed) VALUES (?, ?, ?, ?)";
            int result = jt.update(addsql, task.getTaskItem(), task.getDueDate(), task.getCompleteDate(), task.getCompleted());
            if(result == 1){
                return new ResponseEntity<String>("Successfully Added Task to Database", HttpStatus.OK);
            }
            return new ResponseEntity<String>("Error Inserting Task to Database", HttpStatus.BAD_REQUEST);
        }
        catch(Exception e)
        {
            return new ResponseEntity<String>("Error Inserting Task to Database: " + e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    //Checks if task's id exists, if it does returns it using JPARepository 
    public Task getTaskByID(int id){
        boolean exists = taskRepo.existsById(id);
        if(exists){
            return taskRepo.getReferenceById(id);
        }
        return null;
    }

    public List<Task> getAllTasks(String searchStr){
        if(searchStr == ""){
            return taskRepo.findTasksByString("%");
        }
        return taskRepo.findTasksByString(searchStr);
    }

    public List<Task> getAllCompletedTasks(String searchStr){
        if(searchStr == ""){
            return taskRepo.findCompletedTasks("%");
        }
        return taskRepo.findCompletedTasks(searchStr);
    }

    public List<Task> getAllUnfinishedTasks(String searchStr){
        if(searchStr == ""){
            return taskRepo.findUnfinishedTasks("%");
        }
        return taskRepo.findUnfinishedTasks(searchStr);
    }

    public void deleteTask(int id){
        taskRepo.deleteById(id);
    }

    public void deleteAllTasks(){
        taskRepo.deleteAll();
    }

    public ResponseEntity<?> updateTask(int id, Task task){
        if(idExists(id)){
            try{
                taskRepo.updateTask(id, task.getTaskItem(), task.getDueDate(), task.getCompleteDate(), task.getCompleted());
            }
            catch(Exception e){
                return new ResponseEntity<String>("Error Updating Task: " + e.toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<String>("Successfully Updated Task", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Task Does Not Exist", HttpStatus.NOT_FOUND);
        }
    }
}
