package soccerteam;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import soccerteam.data.PlayerSpringDataRepository;
import soccerteam.model.Player;
import soccerteam.web.PlayerController;
import sun.net.www.content.text.PlainTextInputStream;

import java.util.List;

/**
 * Created by roman_dolgoter on 09/08/2015.
 */
public class PlayerControllerTest  {

    @Test
    public void testPlayerControllerPage() throws Exception {

        PlayerController controller = new PlayerController();
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/player/register")).andExpect(view().name("playerForm"));


    }

    @Test
    public void testPlayerRegistration() throws Exception {

        PlayerSpringDataRepository mockRepository = mock(PlayerSpringDataRepository.class);
        PlayerController controller = new PlayerController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(post("/player/register").param("firstName","Bob").param("secondName","Dilan").param("age","22").param("country","canada").param("salary","100000").param("goals","1")
        .param("booking","2").param("position", "FORWARD")).andExpect(redirectedUrl("/player/Bob_Dilan"));


    }

    @Test
    public void testPlayerSave() throws Exception {

        PlayerSpringDataRepository mockRepository = mock(PlayerSpringDataRepository.class);
        PlayerController controller = new PlayerController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        Player player = new Player("Bob", "Charlton");
        mockRepository.save(player);
        System.out.print(player);
        player = new Player("Bob", "Charlton");
        player = mockRepository.save(player);
        System.out.print(player);

        List<Player> player2 = mockRepository.findByFirstName("Bob");
        System.out.print(player2);

        Player player3 = mockRepository.findByFirstNameAndSecondName("Bob", "Charlton");
        System.out.print(player3);
       // mockMvc.perform(post("/player/register").param("firstName","Bob").param("secondName","Dilan").param("age","22").param("country","canada").param("salary","100000").param("goals","1")
        //        .param("booking","2").param("position","FORWARD")).andExpect(redirectedUrl("/player/Bob_Dilan"));


    }


}