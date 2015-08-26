package soccerteam.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soccerteam.model.Player;

import java.util.List;

@Repository
public interface PlayerSpringDataRepository extends JpaRepository<Player,Long> {

  Player save(Player person);

  Player findByFirstNameAndSecondName(String firstName, String secondName);

 // @Query("select p from Player p")
  List<Player> findByFirstName(String firstName);

}
