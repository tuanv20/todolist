package com.ttran.springboottodolist.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer>{
    //For some reason the query values need to match the values in the Entity file (taskItem instead of task_item)
    //Also, a PUT request uses these values too 
    @Modifying
    @Transactional
    @Query("UPDATE Task t set t.taskItem = ?2, t.dueDate = ?3, t.completeDate = ?4 WHERE id=?1")
    void updateTask(int task_id, String task_item, int due_date, int complete_date);

}