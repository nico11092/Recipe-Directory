package Models;

public class Nutrition {
    private int calories;
    private String fat;
    private String carbohydrates; 
    private String protein; 
    private String alcohol;

    public Nutrition(int _calories, String _fat, String _carbohydrates, String _protein){
        this.calories = _calories;
        this.fat = _fat;
        this.carbohydrates = _carbohydrates;
        this.protein = _protein;
    }

    public Nutrition(int _calories, String _fat, String _carbohydrates, String _protein, String _alcohol){
        this.calories = _calories;
        this.fat = _fat;
        this.carbohydrates = _carbohydrates;
        this.protein = _protein;
        this.alcohol = _alcohol;
    }

    public int getCalories(){ return this.calories; }
    public String getFat(){ return this.fat; }
    public String getCarbohydrates(){ return this.carbohydrates; }
    public String getProtein(){ return this.protein; }
    public String getAlcohol(){ return this.alcohol; }

    public String getAlcoholToString(){
        if(this.alcohol != null){
            return ", alcohol = "+this.alcohol;
        }else{
            return "";
        }
    }

    @Override
    public String toString(){
        return "Nutrition : calories ="+Integer.valueOf(this.calories)+", fat ="+this.fat+", carbohydrates = "+this.carbohydrates+", protein = "+this.protein+getAlcoholToString()+"\n";
    }
}
