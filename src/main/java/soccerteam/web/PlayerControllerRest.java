package soccerteam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import soccerteam.data.PlayerNotFoundException;
import soccerteam.data.PlayerSpringDataRepository;
import soccerteam.model.Player;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/playerRest")
public class PlayerControllerRest {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private PlayerSpringDataRepository playerRepository;

    @Autowired
    public PlayerControllerRest(PlayerSpringDataRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @RequestMapping(value="/register",method = GET)
    public String home(Model model) {

        model.addAttribute(new PlayerForm());
        return "playerForm";
    }


    @RequestMapping(value ="/all",method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    List<Player> getAllPlayers(
        @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
        @RequestParam(value="count", defaultValue="20") int count) {
            return playerRepository.findAll();

    }

    @RequestMapping(value="/{firstAndSecond}", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    ResponseEntity<Player> showPlayerProfile(@PathVariable String firstAndSecond) throws PlayerNotFoundException {
        if(firstAndSecond.split("_").length != 2) {
            throw new IllegalArgumentException();
        }
        String firstName = firstAndSecond.split("_")[0];
        String secondName = firstAndSecond.split("_")[1];
        Player player = playerRepository.findByFirstNameAndSecondName(firstName, secondName);
        HttpStatus status = player != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        if(player == null)
        throw  new PlayerNotFoundException(firstName,secondName);
        return new ResponseEntity<Player>(player, status);

    }

    @RequestMapping(value="/register",method = POST)
    public String processRegistrationSendRestPost(

            @Valid PlayerForm registerForm,
            Errors errors) {

        RestTemplate rest = new RestTemplate();
        Player player = registerForm.toPlayer();
        String url = "http://localhost:8080/playerRest";
        rest.postForLocation(URI.create(url), player);
        return "redirect:/playerRest/" + player.getFirstName() + "_" + player.getSecondName();

    }


    @RequestMapping(method=RequestMethod.POST,consumes="application/json")
    public @ResponseBody Player  saveSpittle(@RequestBody Player player) throws Exception {
        return playerRepository.save(player);
    }


    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    soccerteam.api.Error playerNotFound(PlayerNotFoundException e) {

        return new soccerteam.api.Error(4, "Player [" + e.getPlayerFirstName() + " " + e.getPlayerSecondName() + "] not found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public @ResponseBody
    soccerteam.api.Error wrongArgument(IllegalArgumentException e) {

        return new soccerteam.api.Error(5, "First name and second name must be provided separated by _");
    }
}