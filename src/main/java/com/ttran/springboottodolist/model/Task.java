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
    private int dueDate;

    @Column
    private int completeDate;

    public Task(String taskItem, int dueDate, int completeDate){
        this.taskItem = taskItem;
        this.dueDate = dueDate;
        this.completeDate = completeDate;
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

    public int getDueDate(){
        return this.dueDate;
    }

    public void setDueDate(int dueDate){
        this.dueDate = dueDate;
    }

    public int getCompleteDate(){
        return this.completeDate;
    }

    public void setCompleteDate(int completeDate){
        this.completeDate = completeDate;
    }

}
