package Models;

import java.util.ArrayList;

public class Recipe {
    private String id;
    private String title;
    private String date;
    private ArrayList<Ingredient> ingredient;
    private ArrayList<String> preparation;
    private String comment;
    private Nutrition nutrition;
    private Related related;

    public Recipe(String _id, String _title, String _date){
        this.id = _id;
        this.title = _title;
        this.date = _date;

        this.ingredient = new ArrayList<>();
        this.preparation = new ArrayList<>();
    }

    public void addIngredient(Ingredient _ingredient){ this.ingredient.add(_ingredient); }
    public void addPreparation(String _preparation){ this.preparation.add(_preparation); }

    public void setComment(String _comment){ this.comment = _comment; }
    public void setNutrition(Nutrition _nutrition){ this.nutrition = _nutrition; }
    public void setRelated(Related _related){ this.related = _related; }

    public String getId(){ return this.id; }
    public String getTitle(){ return this.title; }
    public String getDate(){ return this.date; }
    public String getComment(){ return this.comment; }
    public Nutrition getNutrition(){ return this.nutrition; }
    public Related getRelated(){ return this.related; }

    public ArrayList<Ingredient> getAllIngredient(){
        ArrayList<Ingredient> ma_liste = new ArrayList<>();

        for(int i = 0 ; i < this.ingredient.size() ; i++){
            ma_liste.addAll(this.ingredient.get(i).getIngredient());
        }

        return ma_liste;
    }

    public ArrayList<String> getAllPreparation(){
        ArrayList<String> ma_liste = new ArrayList<>();

        for(int i = 0 ; i < this.ingredient.size() ; i++){
            ma_liste.addAll(this.ingredient.get(i).getPreparation());
        }

        ma_liste.addAll(this.preparation);

        return ma_liste;
    }

    @Override
    public String toString(){
        String text = "Recepie n°"+this.id+"\n  Title : "+this.title+"\n  Date : "+this.date+"\n\n";

        for(int i = 0 ; i < this.ingredient.size() ; i++){
            text = text+ "  " + ingredient.get(i).toString()+"\n";
        }
 
        text = text + "\n  Preparation :\n";

        for(int i = 0 ; i < this.preparation.size() ; i++){
            text = text + "    Etape : " + this.preparation.get(i)+"\n";
        }
 
        if(this.comment != null){
            text = text + "\n  Comment : " + this.comment+"\n";
        }

        text = text + "  "+ this.nutrition.toString()+"\n";

        if(this.related != null){
            text = text + "  "+ this.related.toString()+"\n";
        }

        return text;
    }
}
