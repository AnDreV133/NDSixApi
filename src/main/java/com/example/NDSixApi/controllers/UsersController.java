package com.example.NDSixApi.controllers;


import com.example.NDSixApi.coders.HashGenerator;
import com.example.NDSixApi.entities.User;
import com.example.NDSixApi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private HashGenerator hashGenerator;
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/signin")
    @ResponseBody
    public User signin(@RequestBody User user) {
        return user != null ? usersRepository.findOneByLoginAndPassword(
                hashGenerator.hash(user.getLogin()),
                hashGenerator.hash(user.getPassword())
        ).orElseGet(User::new) : new User();
    }

    @PostMapping("/signup")
    @ResponseBody
    public User signup(@RequestBody User user) {
        if (user == null
                || usersRepository.findOneByLogin(hashGenerator.hash(user.getLogin())).isPresent())
            return new User();

        user.setLogin(hashGenerator.hash(user.getLogin()));
        user.setPassword(hashGenerator.hash(user.getPassword()));

        return usersRepository.save(user);
    }

    //    @GetMapping("/{id}")
//    @ResponseBody
//    public User get(@PathVariable int id) {
//        return usersRepository.getById(id);
//    }
//
//    @GetMapping("/")
//    @ResponseBody
//    public List<User> get() {
//        return usersRepository.getAll();
//    }
//
//    @PutMapping("/{id}")
//    @ResponseBody
//    public User updateName(
//            @PathVariable Long id,
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "login", required = false) String login,
//            @RequestParam(value = "password", required = false) String password
//    ) {
//        return usersRepository.save(
//                usersRepository.findOneById(id)
//                        .map(it -> {
//                            if (name != null) it.setName(name);
//                            if (login != null) it.setLogin(hashGenerator.hash(login));
//                            if (password != null) it.setPassword(hashGenerator.hash(password));
//                            return it;
//                        })
//                        .orElse(new User())
//        );
//    }

    @PutMapping("/{id}")
    @ResponseBody
    public User updateName(
            @PathVariable Long id,
            @RequestBody User user
    ) {
        return usersRepository.save(
                usersRepository.findOneById(id)
                        .map(it -> {
                            if (user.getName() != null) it.setName(user.getName());
                            if (user.getLogin() != null) it.setLogin(hashGenerator.hash(user.getLogin()));
                            if (user.getPassword() != null) it.setPassword(hashGenerator.hash(user.getPassword()));
                            return it;
                        })
                        .orElse(new User())
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usersRepository.deleteById(id);
    }

    @GetMapping("")
    @ResponseBody
    public List<User> getAll() {
        return StreamSupport.stream(usersRepository.findAll().spliterator(), false).toList();
    }
}
