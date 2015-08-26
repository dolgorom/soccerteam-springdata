/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerteam.model;

import javax.persistence.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Entity
public class Player{


   public  Player(){

   }

    private int age;
    private Salary salary;
    private String firstName;

    private String secondName;

   public Player (String firstName, String secondName){
       this.firstName = firstName;
       this.secondName = secondName;


   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public enum Position{
        GOALKEEPER, DEFENDER,MIDFIELDER,FORWARD

    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private Statistics stats = new Statistics();
    private String birthcountry;
    private Position position;



    /**
     * @return the stats
     */
    public Statistics getStats() {
        return stats;
    }

    /**
     * @param stats the stats to set
     */
    public void setStats(Statistics stats) {
        this.stats = stats;
    }


    /**
     * @return the birthcountry
     */
    public String getBirthcountry() {
        return birthcountry;
    }

    /**
     * @param birthcountry the birthcountry to set
     */
    public void setBirthcountry(String birthcountry) {
        this.birthcountry = birthcountry;
    }





     /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isCountryValid(){
        if((birthcountry == null || birthcountry.isEmpty())) {
                    return false;
                }


		String regex = "\\d";

		Pattern instance = Pattern.compile(regex);
		Matcher m = instance.matcher(birthcountry);
		if(m.find()) {
			return false;
		}
		return true;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                "firstName=" + firstName +
                "secondName=" + secondName +
                "stats=" + stats +
                ", birthcountry='" + birthcountry + '\'' +
                ", position=" + position +
                ", salary=" + salary +
                '}';
    }


    public boolean isCustomValid(){
        return (this.position != null && isCountryValid() && this.stats.areValid());
    }


    public boolean isAgeValid() {
       return (age >= 20 && age <= 23);
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the secondName
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * @param secondName the secondName to set
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * @return the salary
     */
    public Salary getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
}
