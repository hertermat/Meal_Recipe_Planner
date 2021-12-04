package ca.gbc.RecipeApp.repositories;

import ca.gbc.RecipeApp.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
