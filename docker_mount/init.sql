CREATE table tasks (id INT NOT NULL AUTO_INCREMENT,
     task_item CHAR(50),
     due_date BIGINT, 
     complete_date BIGINT, 
     completed BOOLEAN, 
     PRIMARY KEY (id)
);