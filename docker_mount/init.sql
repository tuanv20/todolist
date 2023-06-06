-- File that is initially mounted to Docker initdb folder to create the MySQL database
-- automatically upon container startup (only if it hasn't already been created yet)
-- MAKE SURE to clear the Docker volume if updates are made to this file
CREATE table tasks (id INT NOT NULL AUTO_INCREMENT,
     task_item CHAR(50),
     due_date BIGINT, 
     complete_date BIGINT, 
     completed BOOLEAN, 
     PRIMARY KEY (id)
);