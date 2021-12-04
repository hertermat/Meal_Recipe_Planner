package ca.gbc.RecipeApp.services;

import ca.gbc.RecipeApp.domain.Ingredient;
import ca.gbc.RecipeApp.domain.Recipe;
import ca.gbc.RecipeApp.repositories.IngredientRepository;
import ca.gbc.RecipeApp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public IngredientService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }
    public List<Ingredient> listAll(){
        return ingredientRepository.findAll();
    }
    public void save(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }
    public Ingredient get(Long id){
        return ingredientRepository.findById(id).get();
    }
    public void delete(Long id){
        ingredientRepository.deleteById(id);
    }
    public Ingredient update(Long recipeId, Long ingredientId){
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> optionalIngredient = recipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
        return optionalIngredient.get();
    }

}
