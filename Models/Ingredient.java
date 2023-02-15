package Models;

import java.util.ArrayList;

public class Ingredient {
    private String name;
    private Double amount;
    private String unit; 
    private ArrayList<String> preparation; 
    private ArrayList<Ingredient> ingredient;

    public Ingredient(String _name){
        this.name = _name;

        this.preparation = new ArrayList<>();
        this.ingredient = new ArrayList<>();
    }

    public void setAmount(Double _amount){ this.amount = _amount; }
    public void setUnit(String _unit){ this.unit = _unit; }

    public void addPreparation(String _preparation){ this.preparation.add(_preparation); }
    public void addIngredient(Ingredient _ingredient){ this.ingredient.add(_ingredient); }
    public void addListIngredient(ArrayList<Ingredient> _list_ingr){ this.ingredient = _list_ingr; }

    public String getName(){ return this.name; }
    public Double getAmount(){ return this.amount; }
    public String getUnit(){ return this.unit; }

    public ArrayList<Ingredient> getIngredient(){
        ArrayList<Ingredient> ma_liste = new ArrayList<>();
        ma_liste.add(this);

        for(int i = 0 ; i < this.ingredient.size() ; i++){
            ma_liste.addAll(this.ingredient.get(i).getIngredient());
        }

        return ma_liste;
    }

    public ArrayList<String> getPreparation(){
        ArrayList<String> ma_liste = new ArrayList<>();
        ma_liste.addAll(this.preparation);

        for(int i = 0 ; i < this.ingredient.size() ; i++){
            ma_liste.addAll(this.ingredient.get(i).getPreparation());
        }

        return ma_liste;
    }

    public String getAmountToString(){
        if(this.amount != null){
            return ", amount = "+this.amount;
        }else{
            return "";
        }
    }

    public String getUnitToString(){
        if(this.unit != null){
            return ", unit = "+this.unit;
        }else{
            return "";
        }
    }

    @Override
    public String toString(){
        String text =  "Ingredient : name = "+this.name+getAmountToString()+getUnitToString();

        for(int i = 0 ; i < this.ingredient.size() ; i++){
            text = text + "\n     " + this.ingredient.get(i).toString();
        }
        
        for(int i = 0 ; i < this.preparation.size() ; i++){
            if(i == 0){
                text = text + "\n     Preparation :\n";
            }
            text = text + "         Etape : " + this.preparation.get(i)+"\n";
        }

        return text;

    }
}
