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
import soccerteam.data.TrainerNotFoundException;
import soccerteam.data.TrainerSpringDataRepository;
import soccerteam.model.Player;
import soccerteam.model.Trainer;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/trainerRest")
public class TrainerControllerRest {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private TrainerSpringDataRepository trainerRepository;

    @Autowired
    public TrainerControllerRest(TrainerSpringDataRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @RequestMapping(value="/register",method = GET)
    public String home(Model model) {

        model.addAttribute(new TrainerForm());
        return "trainerForm";
    }


    @RequestMapping(value ="/all",method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    List<Trainer> getAllTrainers(
        @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
        @RequestParam(value="count", defaultValue="20") int count) {
            return trainerRepository.findAll();

    }


    @RequestMapping(value="/{firstAndSecond}", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    ResponseEntity<Trainer> showTrainerProfile(@PathVariable String firstAndSecond, Model model) {
        if(firstAndSecond.split("_").length != 2) {
            throw new IllegalArgumentException();
        }
        String firstName = firstAndSecond.split("_")[0];
        String secondName = firstAndSecond.split("_")[1];

        HttpStatus status = HttpStatus.OK;
        Trainer trainer = trainerRepository.findByFirstNameAndSecondName(firstName, secondName);

        if (trainer == null){
            throw new TrainerNotFoundException(firstName, secondName);
        }

        return new ResponseEntity<Trainer>(trainer, status);

    }

    @RequestMapping(value="/register",method = POST)
    public String processRegistrationSendRestPost(

            @Valid TrainerForm registerForm,
            Errors errors) {

        RestTemplate rest = new RestTemplate();
        Trainer trainer = registerForm.toTrainer();
        String url = "http://localhost:8080/trainerRest";
        rest.postForLocation(URI.create(url), trainer);
        return "redirect:/trainerRest/" + trainer.getFirstName() + "_" + trainer.getSecondName();

    }


    @RequestMapping(method=RequestMethod.POST,consumes="application/json")
    public @ResponseBody Trainer  saveSpittle(@RequestBody Trainer trainer) throws Exception {
        return trainerRepository.save(trainer);
    }


    @ExceptionHandler(TrainerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    soccerteam.api.Error trainerrNotFound(TrainerNotFoundException e) {

        return new soccerteam.api.Error(4, "Trainer [" + e.getFirstName() + " " + e.getSecondName() + "] not found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public @ResponseBody
    soccerteam.api.Error wrongArgument(IllegalArgumentException e) {

        return new soccerteam.api.Error(5, "First name and second name must be provided separated by _");
    }
}