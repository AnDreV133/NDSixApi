package com.example.NDSixApi.repositories;

import com.example.NDSixApi.entities.Characterlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterlistsRepository extends CrudRepository<Characterlist, Long> {
    Optional<Characterlist> findOneById(Long id);

//    @Query("SELECT c.id FROM Characterlist c WHERE c.user_id =?1")
    List<Characterlist> findByUserId(Long userId);
}
