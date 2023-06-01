package com.martinps.service;

import com.martinps.entity.User;
import com.martinps.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<User> users){
        //users.stream().peek(user -> LOG.info("Usuario insertado: " + user)).forEach(user -> userRepository.save(user)); //igual que la linea sigueinte
        users.stream().peek(user -> LOG.info("Usuario insertado: " + user)).forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
