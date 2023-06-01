package com.martinps.controller;

import com.martinps.caseuse.GetUser;
import com.martinps.entity.Post;
import com.martinps.entity.User;
import com.martinps.repository.PostRepository;
import com.martinps.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class CrudRestController {
    private UserRepository userRepository;
    private PostRepository postRepository;


    public CrudRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/posts")
    List<Post> allPost() {
        return postRepository.findAll();
    }

    @PostMapping("/saveUser")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
/*
   @PostMapping("/saveUser")
   ResponseEntity<User> newUserResponseEntity(@RequestBody User newUser) {
        return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.CREATED);
    }
*/
    @GetMapping("/{id}")
    User getOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

//    @GetMapping("/users/{id}")
//    ResponseEntity<User> getOne(@PathVariable Long id) {
//        if (userRepository.existsById(id)) {
//            return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//    }

    @PutMapping("/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setBirthDate(newUser.getBirthDate());
                    user.setName(newUser.getName());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

//    @PutMapping("/users/{id}")
//    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
//        if (userRepository.existsById(id)) {
//            return new ResponseEntity(userRepository.findById(id)
//                    .map(user -> {
//                        user.setEmail(newUser.getEmail());
//                        user.setBirthDate(newUser.getBirthDate());
//                        user.setName(newUser.getName());
//                        return userRepository.save(user);
//                    }).get(), HttpStatus.OK);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

//    @DeleteMapping("/users/{id}")
//    ResponseEntity deleteUser(@PathVariable Long id) {
//        boolean existsUserById = userRepository.existsById(id);
//        if (existsUserById) {
//            userRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//
//    }

    @GetMapping("/pageable")
    public List<User> getUserPageable(@RequestParam int page, @RequestParam int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
