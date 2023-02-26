package Presentation;

import java.util.List;
import java.util.Map;

import Models.Ingredient;
import Models.Recipe;
import Repositories.RecipeRepo;

public class RecipePrincipale extends javax.swing.JFrame{
    private javax.swing.JComboBox<String> Affichage;
    private javax.swing.JComboBox<String> Questions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;

    private int value_affichage = -1;
    private int value_questions = -1;

    private RecipeRepo myRepo;

    public RecipePrincipale(){
        this.myRepo = new RecipeRepo();

        this.initComponents();
    }

    private void initComponents(){
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Affichage = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Questions = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Veuillez selectionner votre type d'affichage :");

        Affichage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Textuel ", "Graphique " }));
        Affichage.setSelectedIndex(-1);
        Affichage.setBorder(null);
        Affichage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AffichageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Affichage, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Affichage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Veuillez selectionner votre traitement sur la collection de recettes :");

        Questions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lister les titres des recettes ", "Le nombre total d'oeufs utilisés ", "Les recettes utilisant de l'huile d'olive ", "Le nombre d'oeufs par recette ", "Les recettes fournissant moins de 500 calories ", "La quantité de sucre utilisée par la recette Zuppa Inglese", "Les 2 premières étapes de la recette Zuppa Inglese ", "Les recettes qui nécessitent plus de 5 étapes ", "Les recettes qui ne contiennent pas de beurre ", "Les recettes ayant des ingrédients en communs la recette Zuppa Inglese ", "La recette la plus calorique ", "L'unité la plus fréquente ", "Le nombre de d'ingrédients par recette ", "La recette comportant le plus de fat ", "L'ingrédient le plus utilisé ", "Les recettes triées par nombre d'ingrédients ", "Pour chaque ingrédient, les recettes qui l'utilisent", "La répartition des recette par étape de réalisation ", "La recette la plus facile " }));
        Questions.setSelectedIndex(-1);
        Questions.setBorder(null);
        Questions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuestionsActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Questions, 0, 664, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Questions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }
    
    private void AffichageActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.value_affichage = this.Affichage.getSelectedIndex();
        traitement();
    } 

    private void QuestionsActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.value_questions = this.Questions.getSelectedIndex();
        traitement();
    }

    public void traitement(){
        if(this.value_affichage != -1 && this.value_questions != -1){
            String reponse = "";
            Recipe recette = null;
            Ingredient ingredient = null;
            List<String> my_list_string = null;
            List<Recipe> my_list_Recipe = null;
            Map<Recipe, Double> my_map_Recipe = null;
            Map<Recipe, Integer> my_map_Recipe_int = null;
            switch(this.value_questions){
                case 0:
                    //correspond à la question 4 
                    my_list_string = myRepo.afficher_titre();
                    for(int i = 0 ; i < my_list_string.size() ; i++){
                        reponse = reponse + my_list_string.get(i) + "\n";
                    }
                    break;
                case 1:
                    //correspond à la question 5
                    reponse = String.valueOf(myRepo.nombre_oeuf());
                    break;
                case 2:
                    //correspond à la question 6
                    my_list_Recipe = myRepo.recette_huile_olive();
                    for(int i = 0 ; i < my_list_Recipe.size() ; i++){
                        reponse = reponse + my_list_Recipe.get(i) + "\n";
                    }
                    break;
                case 3:
                    //correspond à la question 7
                    my_map_Recipe = myRepo.nombre_oeuf_recette();
                    for(Map.Entry mapentry : my_map_Recipe.entrySet() ){
                        Recipe _recette = (Recipe)mapentry.getKey();
                        reponse = reponse + _recette.getTitle()+ " : "+mapentry.getValue() + " oeuf(s)\n";
                    }
                    break;
                case 4:
                    //correspond à la question 8
                    my_list_Recipe = myRepo.moins_500_calories();
                    for(int i = 0 ; i < my_list_Recipe.size() ; i++){
                        reponse = reponse + my_list_Recipe.get(i) + "\n";
                    }
                    break;
                case 5:
                    //correspond à la question 9 
                    reponse = String.valueOf(myRepo.getSugar());
                    break;
                case 6:
                    //correspond à la question 10 
                    my_list_string = myRepo.afficher_premiere_prepa();
                    for(int i = 0 ; i < my_list_string.size() ; i++){
                        reponse = reponse + my_list_string.get(i) + "\n";
                    }
                    break;
                case 7:
                    //correspond à la question 11 
                    my_list_Recipe = myRepo.recette_plus_5_prepa();
                    for(int i = 0 ; i < my_list_Recipe.size() ; i++){
                        reponse = reponse + my_list_Recipe.get(i) + "\n";
                    }
                    break;
                case 8:
                    //correspond à la question 12
                    my_list_Recipe = myRepo.recette_sans_beurre();
                    for(int i = 0 ; i < my_list_Recipe.size() ; i++){
                        reponse = reponse + my_list_Recipe.get(i) + "\n";
                    }
                    break;
                case 9:
                    //correspond à la question 13 
                    break;
                case 10:
                    //correspond à la question 14 
                    recette = myRepo.recette_plus_calorique();
                    reponse = recette.toString() + "\n";
                    break;
                case 11:
                    //correspond à la question 15
                    reponse = myRepo.unite_plus_frequente();
                    break;
                case 12:
                    //correspond à la question 16 
                    my_map_Recipe_int = myRepo.nb_ingredient_recette();
                    for(Map.Entry mapentry : my_map_Recipe_int.entrySet() ){
                        Recipe _recette = (Recipe)mapentry.getKey();
                        reponse = reponse + _recette.getTitle()+ " : "+mapentry.getValue() + " oeuf(s)\n";
                    }
                    break;
                case 13:
                    //correspond à la question 17
                    recette = myRepo.recette_plus_fat();
                    reponse = recette.toString() + "\n";
                    break;
                case 14:
                    //correspond à la question 18
                    reponse = myRepo.ingredient_plus_utilise();
                    break;
                case 15:
                    //correspond à la question 19
                    my_list_Recipe = myRepo.recette_triee();
                    for(int i = 0 ; i < my_list_Recipe.size() ; i++){
                        reponse = reponse + my_list_Recipe.get(i) + "\n";
                    }
                    break;
                case 16:
                    //correspond à la question 20
                    break;
                case 17:
                    //correspond à la question 21
                    my_map_Recipe_int = myRepo.repartition_recette_etape();
                    for( Map.Entry mapentry : my_map_Recipe_int.entrySet() ){
                        Recipe _recette = (Recipe)mapentry.getKey();
                        reponse = reponse + _recette.getTitle()+ " : "+mapentry.getValue() + " oeuf(s)\n";
                    }
                    break;
                case 18:
                    //correspond à la question 22
                    recette = myRepo.recette_facile();
                    reponse = recette.toString() + "\n";
                    break;
                default:
                    break;
            }
            if(this.value_affichage == 0){
                System.out.println(reponse);
            }else{
                jTextArea1.setText(reponse);
            }
        }
    }

    public static void main(String args[]) throws Exception{
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(){
                new RecipePrincipale().setVisible(true);
            }
        });
    }   
}
