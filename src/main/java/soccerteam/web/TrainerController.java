package soccerteam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import soccerteam.data.TrainerSpringDataRepository;
import soccerteam.model.Trainer;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

    private TrainerSpringDataRepository trainerRepository;

    @Autowired
    public TrainerController(TrainerSpringDataRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public TrainerController() {

    }

    @RequestMapping(value="/register",method = GET)
    public String home(Model model) {

        model.addAttribute(new TrainerForm());
        return "trainerForm";
    }

    @RequestMapping(value="/register",method = POST)
    public String processRegistration(
                                          @Valid TrainerForm trainerForm,
            Errors errors) {
        if (errors.hasErrors()) {
            System.out.print("Error in form. returning form");
            return "trainerForm";
        }
       Trainer trainer = trainerForm.toTrainer();
        System.out.print(trainer);
        trainerRepository.save(trainer);
        return "redirect:/trainer/" + trainer.getFirstName() + "_" + trainer.getSecondName();

    }

    @RequestMapping(value="/{firstAndSecond}", method=GET)
    public String  showPlayerProfile(@PathVariable String firstAndSecond, Model model) {
        String firstName = firstAndSecond.split("_")[0];
        String secondName = firstAndSecond.split("_")[1];
        Trainer trainer = trainerRepository.findByFirstNameAndSecondName(firstName, secondName);

        model.addAttribute(trainer);

        return "/trainerProfile";
    }



}