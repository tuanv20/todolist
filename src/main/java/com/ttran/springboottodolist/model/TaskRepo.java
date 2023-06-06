package com.ttran.springboottodolist.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

//JPARepository interface that contains predefined queries for MySQL 
//database as well as custom implemented queries

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer>{
    //For some reason the query values need to match the values in the Entity file (taskItem instead of task_item)
    //Also, a PUT request uses these values too 

    //Modifying annotation for any queries that edit the database
    @Modifying
    //Automatically handles data integrity during system failure so we can just focus on business logic
    @Transactional
    @Query("UPDATE Task t set t.taskItem = ?2, t.dueDate = ?3, t.completeDate = ?4, t.completed = ?5 WHERE id=?1")
    void updateTask(int task_id, String task_item, long due_date, long complete_date, boolean completed);

    @Query(value="SELECT * FROM tasks WHERE task_item LIKE %?1 AND completed=true", nativeQuery=true)
    List<Task> findCompletedTasks(String searchStr);

    @Query(value="SELECT * FROM tasks WHERE task_item LIKE %?1% AND completed=false", nativeQuery=true)
    List<Task> findUnfinishedTasks(String searchStr);

    @Query(value="SELECT * FROM tasks WHERE task_item LIKE %?1%", nativeQuery = true)
    List<Task> findTasksByString(String searchStr);

}