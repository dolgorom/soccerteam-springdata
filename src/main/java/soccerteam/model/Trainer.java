/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerteam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author roman_dolgoter
 */
@Entity
public class Trainer{


    @Transient
    private List<Team> listOfPreviousTeams;

    @NotNull
    private String firstName;

    @NotNull
    private String secondName;
    private int age;
    private Salary salary;
    public Trainer(){
        
    }


    
    public Trainer(String firstname, String secondname, int age, List <Team> previousTeams){
        this.firstName = firstname;
        this.secondName = secondname;
        this.age = age;
        this.listOfPreviousTeams = previousTeams;
    }
     

     public Trainer(String firstname, String secondname, int age){
        this.firstName = firstname;
        this.secondName = secondname;
        this.age = age;
    }

    public Trainer(String firstName, String secondName, int age, Salary salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.salary = salary;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    /**
     * @return the listOfPreviousTeams
     */
    public List<Team> getListOfPreviousTeams() {
        return listOfPreviousTeams;
    }

    /**
     * @param listOfPreviousTeams the listOfPreviousTeams to set
     */
    public void setListOfPreviousTeams(List<Team> listOfPreviousTeams) {
        this.listOfPreviousTeams = listOfPreviousTeams;
    }


    public boolean isAgeValid() {
       return (age > 40);
    }

    public boolean isCustomValid() {
        return (listOfPreviousTeams != null);
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


    @Override
    public String toString() {
        return "Trainer{" +
                "firsName=" + firstName +
                "secondName=" + secondName +
                "age=" + age +
                "salary=" + salary +
                "listOfPreviousTeams=" + listOfPreviousTeams +
                '}';
    }
}
