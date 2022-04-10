package com.martin.enjoypadelapi.repository;

import com.martin.enjoypadelapi.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAll();

    @Query(value = "SELECT * FROM \"players\" WHERE (\"availability\" = true)", nativeQuery = true)
    List<Player> findAll(boolean availability);
}
