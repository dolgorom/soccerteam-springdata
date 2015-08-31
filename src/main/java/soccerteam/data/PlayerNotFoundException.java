package soccerteam.data;

public class PlayerNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;



  private String playerFirstName;
  private String playerSecondName;

  public PlayerNotFoundException(String playerFirstName, String playerSecondName) {
    this.playerFirstName = playerFirstName; this.playerSecondName = playerSecondName;
  }
  


  /****************************/
  /*** Getters ****************/
  /****************************/
  public String getPlayerFirstName() {
    return playerFirstName;
  }

  public String getPlayerSecondName() {
    return playerSecondName;
  }
  
}
