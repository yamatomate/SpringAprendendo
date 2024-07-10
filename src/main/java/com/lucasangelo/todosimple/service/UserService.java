package com.lucasangelo.todosimple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repository.TaskRepository;
import com.lucasangelo.todosimple.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id); 
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encotrado, id:"+id+", tipo:"+User.class.getName()
        ));
    }

    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("não é possivel excluir pois há entidades relacionais");
        }
    }

    @Transactional
    public User update(User obj){
        User novoObj = findById(obj.getId());
        novoObj.setPassword(obj.getPassword());
        return this.userRepository.save(novoObj);
    }
}
