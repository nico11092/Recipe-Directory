package Repositories;

import java.util.ArrayList;

import Models.Recipe;

public class RecipeRepo {
    private ArrayList<Recipe> list_Recipes;

    public RecipeRepo(){
        this.list_Recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe _recipe){ this.list_Recipes.add(_recipe); }

    public void affiche(){
        for(int i = 0 ; i < this.list_Recipes.size() ; i++){
            System.out.println(this.list_Recipes.get(i).toString());
        }
    }

    public void init(){

    }
}
