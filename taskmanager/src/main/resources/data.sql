INSERT INTO usuarios (id, name,lastname, role, password) values (1,'hugo','castillo', 'ADMIN','1234')
INSERT INTO usuarios (id, name,lastname, role, password) values (2,'alberto','castillo', 'ADMIN','1234')
INSERT INTO usuarios (id, name,lastname, role, password) values (3,'mario','castillo', 'ADMIN','1234')
INSERT INTO estado_tareas (id,status) values (1,'OPEN')
INSERT INTO estado_tareas (id,status) values (2,'PROGRESS')
INSERT INTO estado_tareas (id,status) values (3,'COMPLETED')
INSERT INTO tareas (id,title, created_at,user_id, description,state_id) values (1,'tarea uno',now(),1, 'realizar labores',1)


