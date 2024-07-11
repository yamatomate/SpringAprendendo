package com.gustavo.todosimple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.todosimple.models.Task;
import com.gustavo.todosimple.models.User;
import com.gustavo.todosimple.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(
            () -> new RuntimeException("task não encotrada, id:"+id+" ,tipo: "+Task.class.getName())
        );
    }

    @SuppressWarnings("null") /* coisa chata esse aviso */
    public Task create (Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId((Long) null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj){
        Task novoObj = findById(obj.getId());
        novoObj.setDescription(obj.getDescription());
        return this.taskRepository.save(novoObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
             throw new RuntimeException(
                "Não foi possivel deletar a task, id:"+id);
        }
    }
}
