package soccerteam.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccerteam.model.Player;
import soccerteam.model.Trainer;

import java.util.List;

@Repository
public interface TrainerSpringDataRepository extends JpaRepository<Trainer,Long> {

  Trainer save(Trainer person);

  Trainer findByFirstNameAndSecondName(String firstName, String secondName);

 // @Query("select p from Player p")
  List<Trainer> findByFirstName(String firstName);

}
