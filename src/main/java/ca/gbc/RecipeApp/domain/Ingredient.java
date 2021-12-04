package ca.gbc.RecipeApp.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long recipe_id;
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

    public Ingredient(String description, BigDecimal amount, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

//    public Long getRecipe_id() {
//        return recipe_id;
//    }
//
//    public void setRecipe_id(Long recipe_id) {
//        this.recipe_id = recipe_id;
//    }
}
