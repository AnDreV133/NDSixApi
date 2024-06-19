package com.example.NDSixApi.controllers;

import com.example.NDSixApi.entities.Characterlist;
import com.example.NDSixApi.entities.User;
import com.example.NDSixApi.repositories.CharacterlistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.WeakReference;
import java.util.List;

@RestController
@RequestMapping("/characterlists")
public class CharacterlistsController {
    @Autowired
    private CharacterlistsRepository characterlistRepository;

    @GetMapping("/{user_id}")
    @ResponseBody
    public List<Characterlist> getAllByUserId(@PathVariable("user_id") Long userId) {
        return characterlistRepository.findByUserId(userId);
    }

    @PostMapping("/{user_id}")
    @ResponseBody
    public Characterlist addCharacterlist(
            @PathVariable("user_id") Long userId,
            @RequestBody Characterlist characterlist) {
        User user = new WeakReference<>(new User()).get();
        user.setId(userId);
        characterlist.setUser(user);
        return characterlistRepository.save(characterlist);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Characterlist updateCharacterlist(
            @PathVariable Long id,
            @RequestBody Characterlist characterlist) {
        return characterlistRepository.save(
                characterlistRepository.findOneById(id)
                        .map(it -> {
                            if (characterlist.getName() != null) it.setName(characterlist.getName());
                            if (characterlist.getJson() != null) it.setName(characterlist.getJson());
                            return it;
                        })
                        .orElse(new Characterlist())
        );
    }

//    @PutMapping("/{id}")
//    @ResponseBody
//    public Characterlist updateCharacterlist(
//            @PathVariable Long id,
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value  =  "json", required  = false) String json
//    ) {
//        return characterlistRepository.save(
//                characterlistRepository.findOneById(id)
//                        .map(it -> {
//                            if (name != null) it.setName(name);
//                            if (json != null) it.setName(json);
//                            return it;
//                        })
//                        .orElse(new Characterlist())
//        );
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)  {
        characterlistRepository.deleteById(id);
    }
}
