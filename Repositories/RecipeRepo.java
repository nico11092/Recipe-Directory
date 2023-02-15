package Repositories;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import java.util.function.Function;
import java.util.List;
import java.util.stream.Collectors;

import Models.Ingredient;
import Models.Nutrition;
import Models.Recipe;
import Models.Related;

public class RecipeRepo {
    private ArrayList<Recipe> list_Recipes;

    public RecipeRepo(){
        this.list_Recipes = new ArrayList<>();
        this.init();
    }

    public void addRecipe(Recipe _recipe){ this.list_Recipes.add(_recipe); }

    public void affiche(){
        for(int i = 0 ; i < this.list_Recipes.size() ; i++){
            System.out.println(this.list_Recipes.get(i).toString());
        }
    }

    public void init(){
        try{
            File inputFile = new File("recipes.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("rcp:recipe");
            for (int temp = 0; temp < nList.getLength()-1; temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    //id, title, date
                    String id = eElement.getAttribute("id");
                    String title = eElement.getElementsByTagName("rcp:title").item(0).getTextContent();
                    String date = eElement.getElementsByTagName("rcp:date").item(0).getTextContent();

                    Recipe recette = new Recipe(id, title, date);

                    //ingredient 
                    int compteur_ing = 0;
                    int compteur_prepa = 0;
                    NodeList list_ingr =  eElement.getElementsByTagName("rcp:ingredient");
                    for(int i = 0 ; i < list_ingr.getLength() ; i++){
                        Node nIngredient = list_ingr.item(i);
                        if(nIngredient.getNodeType() == Node.ELEMENT_NODE){
                            Element eIngredient = (Element) nIngredient;

                            String name = eIngredient.getAttribute("name");

                            Ingredient ingredient = new Ingredient(name);

                            if(eIngredient.getAttribute("amount") != ""){
                                String amount = eIngredient.getAttribute("amount");
                                if(!amount.equals("*")){
                                    ingredient.setAmount(Double.valueOf(amount));
                                }
                            }

                            if(eIngredient.getAttribute("unit") != ""){
                                String unit = eIngredient.getAttribute("unit");
                                ingredient.setUnit(unit);
                            }

                            if(eIngredient.getElementsByTagName("rcp:ingredient").item(0) != null){
                                NodeList list_list_ingr =  eIngredient.getElementsByTagName("rcp:ingredient");
                                for(int a = 0 ; a < list_list_ingr.getLength() ; a++){
                                    Node nnIngredient = list_list_ingr.item(a);
                                    if(nnIngredient.getNodeType() == Node.ELEMENT_NODE){
                                        Element eeIngredient = (Element) nnIngredient;

                                        String name_ = eeIngredient.getAttribute("name");
                                        Ingredient ingredient_ = new Ingredient(name_);

                                        if(eeIngredient.getAttribute("amount") != ""){
                                            String amount_ = eeIngredient.getAttribute("amount");
                                            if(!amount_.equals("*")){
                                                ingredient_.setAmount(Double.valueOf(amount_));
                                            }
                                        }

                                        if(eeIngredient.getAttribute("unit") != ""){
                                            String unit_ = eeIngredient.getAttribute("unit");
                                            ingredient_.setUnit(unit_);
                                        }
                                        ingredient.addIngredient(ingredient_);
                                    }
                                    compteur_ing++;
                                }
                            }
                            i = i + compteur_ing;
                            compteur_ing = 0;

                            if(eIngredient.getElementsByTagName("rcp:preparation").item(0) != null){
                                NodeList list_list_prepa =  eIngredient.getElementsByTagName("rcp:preparation");
                                for(int z = 0 ; z < list_list_prepa.getLength() ; z++){
                                    Node nnIngredientPrepa = list_list_prepa.item(z);
                                    if(nnIngredientPrepa.getNodeType() == Node.ELEMENT_NODE){
                                        Element eeIngredientPrepa = (Element) nnIngredientPrepa;

                                        NodeList list_step =  eeIngredientPrepa.getElementsByTagName("rcp:step");
                                        for(int j = 0 ; j < list_step.getLength() ; j++){
                                            Node nStep = list_step.item(j);
                                            if(nStep.getNodeType() == Node.ELEMENT_NODE){
                                                Element eStep = (Element) nStep;
                                                
                                                String step = eStep.getTextContent();
                                                ingredient.addPreparation(step);
                                            }
                                        }
                                    }
                                    compteur_prepa++;
                                }
                            }
                            recette.addIngredient(ingredient);
                        }
                    }

                    //preparation 
                    NodeList list_prepa =  eElement.getElementsByTagName("rcp:preparation");
                    for(int i = compteur_prepa ; i < list_prepa.getLength() ; i++){
                        Node nPrepa = list_prepa.item(i);
                        if(nPrepa.getNodeType() == Node.ELEMENT_NODE){
                            Element ePrepa = (Element) nPrepa;

                            NodeList list_step =  ePrepa.getElementsByTagName("rcp:step");
                            for(int j = 0 ; j < list_step.getLength() ; j++){
                                Node nStep = list_step.item(j);
                                if(nStep.getNodeType() == Node.ELEMENT_NODE){
                                    Element eStep = (Element) nStep;
                                    
                                    String step = eStep.getTextContent();
                                    recette.addPreparation(step);
                                }
                            }
                            
                        }
                    }

                    //Comment
                    if(eElement.getElementsByTagName("rcp:comment").item(0) != null){
                        String comment = eElement.getElementsByTagName("rcp:comment").item(0).getTextContent();
                        recette.setComment(comment);
                    }

                    //Nutrition 
                    Element nutrition =  (Element) eElement.getElementsByTagName("rcp:nutrition").item(0);
                    int calories = Integer.parseInt(nutrition.getAttribute("calories"));
                    String fat = nutrition.getAttribute("fat");
                    String carbo = nutrition.getAttribute("carbohydrates");
                    String protein = nutrition.getAttribute("protein");
                    if(nutrition.getAttribute("alcohol") != ""){
                        String alcohol = nutrition.getAttribute("alcohol");
                        Nutrition nut = new Nutrition(calories, fat, carbo, protein, alcohol);
                        recette.setNutrition(nut);
                    }else{
                        Nutrition nut = new Nutrition(calories, fat, carbo, protein);
                        recette.setNutrition(nut);
                    }

                    //Related 
                    Element related =  (Element) eElement.getElementsByTagName("rcp:related").item(0);
                    if(related != null){
                        String ref = related.getAttribute("ref");
                        String name = related.getTextContent();
                        Related rel = new Related(ref, name);
                        recette.setRelated(rel);
                    }
                    addRecipe(recette);
                }
            }
        
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //question 4 (afficher)
    public List<String> afficher_titre(){
        return this.list_Recipes.stream().map(r -> r.getTitle()).collect(Collectors.toList());
    }

    //question 5 (retourner)
    public Double nombre_oeuf(){
        return this.list_Recipes.stream().mapToDouble(r -> r.getAllIngredient().stream().filter(i -> i.getName().contains("eggs")).mapToDouble(i -> i.getAmount()).sum()).sum();
    }

    //question 6 (retourner)
    public List<Recipe> recette_huile_olive(){
        return this.list_Recipes.stream().filter(r -> r.getAllIngredient().stream().filter(i -> i.getName().equals("olive oil")).count() > 0).collect(Collectors.toList());
    }

    //question 7 (retourner)
    public Map<Recipe, Double> nombre_oeuf_recette(){
        return this.list_Recipes.stream().collect(Collectors.toMap(Function.identity(), r -> r.getAllIngredient().stream().filter(i -> i.getName().contains("eggs")).mapToDouble(i -> i.getAmount()).sum() ));
    }

    //question 8 (retourner)
    public List<Recipe> moins_500_calories(){
        return this.list_Recipes.stream().filter(r -> r.getNutrition().getCalories() < 500).collect(Collectors.toList());
    }

    //question 9 (retourner)
    public Double getSugar(){
        return this.list_Recipes.stream().filter(r -> r.getTitle().equals("Zuppa Inglese")).mapToDouble(r -> r.getAllIngredient().stream().filter(i -> i.getName().contains("sugar")).mapToDouble(i -> i.getAmount()).sum()).sum();
    }

    //question 10 (afficher)
    public List<String> afficher_premiere_prepa(){
        return this.list_Recipes.stream().filter(r -> r.getTitle().equals("Zuppa Inglese")).flatMap(r -> r.getAllPreparation().stream().limit(2)).collect(Collectors.toList());
    }

    //question 11 (retourner)
    public List<Recipe> recette_plus_5_prepa(){
        return this.list_Recipes.stream().filter(r -> r.getAllPreparation().size()> 5).collect(Collectors.toList());
    }

    //quesion 12 (retourner)
    public List<Recipe> recette_sans_beurre(){
        return this.list_Recipes.stream().filter(r -> r.getAllIngredient().stream().filter(i -> i.getName().equals("butter")).count() == 0).collect(Collectors.toList());
    }

    //question 13 (retourner)
    public void ingr_commun(){

    }

    //question 14 (afficher)
    public Recipe recette_plus_calorique(){
        return this.list_Recipes.stream().max(Comparator.comparing(r -> r.getNutrition().getCalories())).get();
    }

    //question 15 (retourner)
    public String unite_plus_frequente(){
        return this.list_Recipes.stream().map(r -> r.getAllIngredient().stream().filter(i -> i.getUnit() != null)
            .map(Ingredient::getUnit)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream().max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).get())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream().max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).get().toString();
    }

    //question 16 (retourner)
    public Map<Recipe, Integer> nb_ingredient_recette(){
        return this.list_Recipes.stream().collect(Collectors.toMap(Function.identity(), r -> r.getAllIngredient().size()));
    }

    //question 17 (retourner)
    public Recipe recette_plus_fat(){
        return this.list_Recipes.stream().max(Comparator.comparing(r -> r.getNutrition().getFat())).get();
    }

    //question 18 (retrouner)
    public void ingredient_plus_utilise(){
        System.out.println(this.list_Recipes.stream().map(r -> r.getAllIngredient().stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream().max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey).get())
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream().max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey).get().toString());
    }

    //question 19 (afficher)
    public List<Recipe> recette_triee(){
        return this.list_Recipes.stream().sorted(Comparator.comparing(r -> r.getAllIngredient().size())).collect(Collectors.toList());
    }

    //question 20 (afficher)
    public void ingredient_fonction_recette(){

    }

    //question 21 (retourner)
    public Map<Recipe, Integer> repartition_recette_etape(){
        return this.list_Recipes.stream().collect(Collectors.toMap(Function.identity(), r -> r.getAllPreparation().size()));
    }

    //question 22 (retourner)
    public Recipe recette_facile(){
        return this.list_Recipes.stream().min(Comparator.comparing(r -> r.getAllPreparation().size())).get();
    }
}
