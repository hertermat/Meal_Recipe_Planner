package ca.gbc.RecipeApp.domain;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 1
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   November 7th, 2021
//        * Description:            Defining the recipe table
//        *********************************************************************************

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Enter the title")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    @Size(max = 100)
    private String description;
    //@NotBlank
    private String date;
    @ManyToMany
    @JoinTable(name = "user_recipe", joinColumns = @JoinColumn( name = "recipe_id"),  inverseJoinColumns = @JoinColumn( name = "user_id"))
    private Set<User> users = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Recipe(String title, String description, String date, Set<User> users) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", users=" + users +
                '}';
    }
}