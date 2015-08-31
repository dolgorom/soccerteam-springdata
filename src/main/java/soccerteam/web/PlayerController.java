package soccerteam.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import soccerteam.data.PlayerSpringDataRepository;
import soccerteam.model.Player;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private PlayerSpringDataRepository playerRepository;

    @Autowired
    public PlayerController(PlayerSpringDataRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerController() {

    }

    @RequestMapping(value="/register",method = GET)
    public String home(Model model) {

        model.addAttribute(new PlayerForm());
        return "playerForm";
    }


    @RequestMapping(value="/register",method = POST)
    public String processRegistration(
                                          @Valid PlayerForm registerForm,
            Errors errors) {
        if (errors.hasErrors()) {
            System.out.print("Error in form. returning form");
            return "playerForm";
        }
       Player player = registerForm.toPlayer();
        playerRepository.save(player);
        return "redirect:/player/" + player.getFirstName() + "_" + player.getSecondName();

    }



    @RequestMapping(value="/{firstAndSecond}", method=GET)
    public String  showPlayerProfile(@PathVariable String firstAndSecond, Model model) {
        String firstName = firstAndSecond.split("_")[0];
        String secondName = firstAndSecond.split("_")[1];
        Player player = playerRepository.findByFirstName(firstName).get(0);


        model.addAttribute(player);

        return "playerProfile";
    }



}