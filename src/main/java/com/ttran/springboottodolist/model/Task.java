package com.ttran.springboottodolist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String taskItem;

    @Column
    private long dueDate;

    @Column
    private long completeDate;

    @Column
    private boolean completed;

    public Task(String taskItem, long dueDate, long completeDate, boolean completed){
        this.taskItem = taskItem;
        this.dueDate = dueDate;
        this.completeDate = completeDate;
        this.completed = completed;
    }

    public Task(){
    }
    
    public int getID(){
        return this.id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getTaskItem(){
        return this.taskItem;
    }

    public void setTaskItem(String taskItem){
        this.taskItem = taskItem;
    }

    public long getDueDate(){
        return this.dueDate;
    }

    public void setDueDate(long dueDate){
        this.dueDate = dueDate;
    }

    public long getCompleteDate(){
        return this.completeDate;
    }

    public void setCompleteDate(long completeDate){
        this.completeDate = completeDate;
    }

    public boolean getCompleted(){
        return this.completed;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

}
