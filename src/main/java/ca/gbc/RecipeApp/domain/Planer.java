package ca.gbc.RecipeApp.domain;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Defining the meal planner table
//        *********************************************************************************

import org.springframework.data.annotation.Id;
import javax.persistence.*;

@Entity
@Table(name="MealPlaner")
public class Planer {
    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String date;


    private String morningMeal;

    private String afternoonMeal;

    private String snacks;

    private String nightMeal;
    private boolean completed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMorningMeal() {
        return morningMeal;
    }

    public void setMorningMeal(String morningMeal) {
        this.morningMeal = morningMeal;
    }

    public String getAfternoonMeal() {
        return afternoonMeal;
    }

    public void setAfternoonMeal(String afternoonMeal) {
        this.afternoonMeal = afternoonMeal;
    }

    public String getSnacks() {
        return snacks;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public String getNightMeal() {
        return nightMeal;
    }

    public void setNightMeal(String nightMeal) {
        this.nightMeal = nightMeal;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Planer{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", morningMeal='" + morningMeal + '\'' +
                ", afternoonMeal='" + afternoonMeal + '\'' +
                ", snacks='" + snacks + '\'' +
                ", nightMeal='" + nightMeal + '\'' +
                '}';
    }
}
