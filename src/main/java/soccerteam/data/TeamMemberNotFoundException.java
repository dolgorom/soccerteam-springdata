package soccerteam.data;

public class TeamMemberNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;



  protected String firstName;
  protected String secondName;

  public TeamMemberNotFoundException(String playerFirstName, String playerSecondName) {
    this.firstName = playerFirstName; this.secondName = playerSecondName;
  }




  /****************************/
  /*** Getters ****************/
  /****************************/
  public String getFirstName() {
    return firstName;
  }

  public String getSecondName() {
    return secondName;
  }
  
}
