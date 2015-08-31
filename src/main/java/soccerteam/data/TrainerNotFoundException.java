package soccerteam.data;

public class TrainerNotFoundException extends TeamMemberNotFoundException {

  private static final long serialVersionUID = 1L;


  public TrainerNotFoundException(String firstName, String secondName) {
    super(firstName,secondName);
  }

  
}
