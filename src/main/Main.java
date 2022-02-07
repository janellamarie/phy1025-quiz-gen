package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static Controller controller;

    private Stage primaryStage;
    private Scene mainScene;
    private VBox mainBox;
    private BorderPane quizInfo;

    private VBox mainVBox, questionVBox;
    private ScrollPane sp;

    private ArrayList<VBox> listOfQBV;
    private ArrayList<ToggleGroup> listOfToggleGroup;

    // JavaFX layouts: VBox, HBox, BorderPane, GridPane, ScrollPane

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PHY-1025 Quiz Generator");
        this.primaryStage = primaryStage;
        // initialize main menu scene
        initMainScene();

        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public void initMainScene(){
        mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getStyleClass().add("vbox");
        mainBox.setSpacing(15);

        /** TODO: Non-brute force method **/
        // create components, add them to mainBox
        Label label = new Label("Welcome to PHY-1025 Reviewer");
        label.setId("title");
        mainBox.getChildren().add(label);

        Button button = new Button("Change Source File");
        mainBox.getChildren().add(button);
        button = new Button("Start Quiz");
        // set the method to be called when the button is clicked
        button.setOnAction(e->controller.initQuiz(this));
        mainBox.getChildren().add(button);
        button = new Button("Start Exam");
        mainBox.getChildren().add(button);
        button = new Button("Exit");
        mainBox.getChildren().add(button);
        button.setOnAction(e-> Platform.exit());

        mainScene = new Scene(mainBox, 800,600);
        mainScene.getStylesheets().add("main/main-style.css");

        /** TODO: add menu
         * https://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
         */

        primaryStage.setScene(mainScene);
    }

    public void initStartQuiz(ArrayList<Chapter> listOfChapters){
        quizInfo = new BorderPane();

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(5);
        gp.setVgap(10);

        // combo box for choosing module number
        Label label = new Label("Module Number: ");
        ComboBox modComboBox = new ComboBox();
        // combo box for choosing chapter number
        ComboBox chComboBox = new ComboBox();

        /** TODO: Non-brute force method */
        chComboBox.getStyleClass().add("chapter-combobox");
        final ObservableList<String> moduleOne = FXCollections.observableArrayList("1","2","3");
        final ObservableList<String> moduleTwo = FXCollections.observableArrayList("4","5","6","7");
        final ObservableList<String> moduleThree = FXCollections.observableArrayList("8","9","10");
        final ObservableList<String> moduleFour = FXCollections.observableArrayList("15","16","17","19","20");
        final ObservableList<String> moduleFive = FXCollections.observableArrayList("22","23");

        modComboBox.getItems().addAll("1", "2", "3", "4", "5");
        modComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                switch (t1.toString()){
                    case "1": chComboBox.setItems(moduleOne);
                        break;
                    case "2": chComboBox.setItems(moduleTwo);
                        break;
                    case "3": chComboBox.setItems(moduleThree);
                        break;
                    case "4": chComboBox.setItems(moduleFour);
                        break;
                    case "5": chComboBox.setItems(moduleFive);
                        break;
                }
            }
        });

        gp.add(label, 0, 1);
        gp.add(modComboBox, 1,1);

        // choose quiz number
        label = new Label("Chapter Number: ");

        // display current number of questions in bank from excel file
        Label totalItems = new Label ("Total Number of Questions in Bank: ");
        TextField tfItems = new TextField();
        tfItems.setEditable(false);
        gp.add(totalItems, 0,4);

        // update chapter title label and number of questions in bank as the user changes quiz number
        TextField tfChapter = new TextField ("Chapter Title");
        tfChapter.setEditable(false);
        tfChapter.getStyleClass().add("textfield-chapter");
        chComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            /** TODO: Non-brute force method **/
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                switch (t1.toString()){
                    case "1": tfChapter.setText("Introduction");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "2": tfChapter.setText("Newton's First Law: Inertia");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "3": tfChapter.setText("Linear Motion");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "15": tfChapter.setText("Temperature, Heat, Expansion");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "16": tfChapter.setText("Heat Transfer");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "17": tfChapter.setText("Change of Phase");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "19": tfChapter.setText("Vibrations and Waves");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "20": tfChapter.setText("Sound");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "22": tfChapter.setText("Electrostatics");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    case "23": tfChapter.setText("Electric Current");
                        updatetfItems(listOfChapters, chComboBox, tfItems);
                        break;
                    default: tfChapter.setText("Chapter Title");
                }
            }
        });

        // add(col, row)
        gp.add(label, 0,2);
        gp.add(chComboBox, 1,2);
        gp.add(tfChapter, 3,2);

        gp.add(tfItems, 1,4);

        // quiz info edit (number of questions)
        label = new Label ("Number of Items for Current Quiz: ");
        TextField nItems = new TextField();
        nItems.setText("10");

        gp.add(label, 0,5);
        gp.add(nItems, 1, 5);

        // button to start quiz
        Button startQuiz = new Button ("Start Quiz");
        startQuiz.setId("start-quiz");
        gp.add(startQuiz, 4, 8);

        // get information from combo boxes and text fields
        startQuiz.setOnAction(e-> getInfoForQuiz(chComboBox, modComboBox, gp, nItems));

        quizInfo.setCenter(gp);

        label = new Label("Quiz Generator");
        label.setId("title");

        VBox temp = new VBox(5);
        temp.getChildren().add(label);

        label = new Label("To generate a quiz, select a Module number and Chapter number.\n" +
                            "You can also select the total number of items the quiz will have, the default is 10.");
        label.setTextAlignment(TextAlignment.JUSTIFY);
        temp.getChildren().add(label);

        temp.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(temp, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(temp, new Insets(100,0,0,0));
        quizInfo.setTop(temp);

        mainScene = new Scene(quizInfo, 800, 600);
        mainScene.getStylesheets().add("main/main-style.css");
        primaryStage.setScene(mainScene);
    }

    private void getInfoForQuiz(ComboBox chComboBox, ComboBox modComboBox, GridPane gp, TextField nItems){
        Label warning = new Label();

        if (chComboBox.getSelectionModel().getSelectedItem() != null && modComboBox.getSelectionModel().getSelectedItem() != null)
            controller.startQuiz(Integer.parseInt(
                    (String) chComboBox.getSelectionModel().getSelectedItem()),
                    Integer.parseInt(nItems.getText()),
                    Integer.parseInt(
                            (String) modComboBox.getSelectionModel().getSelectedItem())
            );
        else if (chComboBox.getSelectionModel().getSelectedItem() == null || modComboBox.getSelectionModel().getSelectedItem() == null) {
            removeWarnings(gp, warning);
            addWarning(warning, gp, chComboBox, modComboBox, nItems);

        }
    }

    public void initQuizLayout(int quizNumber, int modNumber, ArrayList<Question> questions){
        sp = new ScrollPane();
        sp.setFitToWidth(true);
        sp.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.80));

        mainVBox = new VBox();
        mainVBox.setSpacing(10);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.prefWidthProperty().bind(sp.widthProperty().multiply(0.80));

        Label label = new Label("Quiz for Chapter " + quizNumber );
        label.setId("title");

        mainVBox.getChildren().add(label);

        listOfToggleGroup = new ArrayList<>();
        listOfQBV = new ArrayList<>();

        createQuestionVBox(questions);

        Button submitQuiz = new Button("Submit");
        submitQuiz.setId("important-button");
        submitQuiz.setOnAction(e -> getAllAnswers(listOfToggleGroup, questions, quizNumber, modNumber));

        mainVBox.getChildren().add(submitQuiz);

        sp.setContent(mainVBox);
        mainScene = new Scene(sp, 800, 600);
        mainScene.getStylesheets().add("main/main-style.css");
        primaryStage.setScene(mainScene);
    }

    public void getAllAnswers(ArrayList<ToggleGroup> toggleGroupArrayList, ArrayList<Question> questions, int quizNumber, int modNum){
        ArrayList<Integer> answers = new ArrayList<>();

        mainVBox.getChildren().removeAll();

        /* always +1 for a non-zero number so that i can negative */

        for(int i = 0; i < toggleGroupArrayList.size(); i++){
            answers.add(toggleGroupArrayList.get(i).getToggles().indexOf(
                    toggleGroupArrayList.get(i).getSelectedToggle()
                ) + 1
            );
        }

        answers = controller.submitQuiz(answers);
        updateQuizView(answers, questions, quizNumber, modNum);
    }

    /** updates scene when the user submits the quiz */
    public void updateQuizView(ArrayList<Integer> answers, ArrayList<Question> questions, int chNum, int modNum){
        Label score = new Label(), label;
        int n = 0;
        mainVBox = new VBox();
        mainVBox.setSpacing(10);
        mainVBox.setAlignment(Pos.CENTER);

        label = new Label("Results for Quiz for Chapter " + chNum);
        label.setId("title");
        mainVBox.getChildren().add(label);

        label = new Label("SCORE: ");

        HBox hbScore = new HBox();
        hbScore.getChildren().add(label);

        for(int i = 0; i < answers.size(); i++){
            if(answers.get(i) > 0)
                n++;
        }

        score.setText(Integer.toString(n));
        hbScore.getChildren().add(score);
        mainVBox.getChildren().add(hbScore);

        listOfToggleGroup = new ArrayList<>();
        listOfQBV = new ArrayList<>();
        createQuestionVBox(questions);

        // changes the ID per VBox - red if wrong; green if correct
        for(int i = 0; i < listOfQBV.size(); i++){
            if(answers.get(i) < 0) {
                listOfQBV.get(i).setId("wrong");
            } else
                listOfQBV.get(i).setId("correct");
        }

        updateToggleGroup(answers, questions);

        sp.setContent(mainVBox);

        Button retryButton = new Button("Take Another Quiz");
        retryButton.setId("important-button");
        retryButton.setOnAction(e -> controller.startQuiz(chNum, questions.size(), modNum));

        Button exitButton = new Button("Return to Main Menu");
        exitButton.setOnAction(e -> initMainScene());

        HBox hbButton = new HBox(10);
        hbButton.setAlignment(Pos.BOTTOM_RIGHT);

        hbButton.getChildren().addAll(exitButton, retryButton);
        mainVBox.getChildren().add(hbButton);

        // moves the scroll to the top
        sp.setVvalue(0.0);

        mainScene.getStylesheets().add("main/main-style.css");
        primaryStage.setScene(mainScene);
    }

    /** changes the selected toggle to the right answer */
    private void updateToggleGroup(ArrayList<Integer> answers, ArrayList<Question> questions){
        int temp;
        for(int i = 0; i < listOfToggleGroup.size(); i++) {
            if(answers.get(i) < 0){
//                temp = (answers.get(i)*-1)-1;
                temp = questions.get(i).getCorrectIndex()-1;
                listOfToggleGroup.get(i).getToggles().get(temp).setSelected(true);
            } else {
                temp = questions.get(i).getCorrectIndex()-1;
            }

            listOfToggleGroup.get(i).getToggles().get(temp).setSelected(true);
        }

        for(int i = 0; i < listOfToggleGroup.size(); i++) {
            for(int j = 0; j < listOfToggleGroup.get(i).getToggles().size(); j++) {
                ((RadioButton) listOfToggleGroup.get(i).getToggles().get(j)).setDisable(true);
            }
        }
    }

    private void createQuestionVBox(ArrayList<Question> questions){
        for(int i = 0; i < questions.size(); i++){
            questionVBox = new VBox();

            questionVBox.getStyleClass().add("vbox-quiz");
            questionVBox.prefWidthProperty().bind(mainVBox.widthProperty().multiply(0.80));

            // label for question
            Label question = new Label(i+1 + ". " + questions.get(i).getQuestion());
            questionVBox.getChildren().add(question);
            question.setWrapText(true);
            question.setPrefWidth(750);

            question.setTextAlignment(TextAlignment.JUSTIFY);
            question.getStyleClass().add("label-question");

            // radio button for items
            ToggleGroup choiceToggleGroup = new ToggleGroup();
            listOfToggleGroup.add(choiceToggleGroup);

            for(int j = 0; j < questions.get(i).getChoices().size(); j++){
                RadioButton rb = new RadioButton(questions.get(i).getChoices().get(j));
                questionVBox.getChildren().add(rb);
                rb.setToggleGroup(choiceToggleGroup);
                questionVBox.setSpacing(20);
            }

            listOfQBV.add(questionVBox);
            mainVBox.getChildren().add(questionVBox);
        }
    }

    /** update the items in the text field that shows the total number of items in the textbox */
    private void updatetfItems(ArrayList<Chapter> listOfChapters, ComboBox chComboBox, TextField tfItems){
        for(int i = 0; i < listOfChapters.size(); i++){
            int toFind = Integer.parseInt((String)chComboBox.getSelectionModel().getSelectedItem());
            if(listOfChapters.get(i).getNum() == toFind)
                tfItems.setText(Integer.toString(listOfChapters.get(i).getnTotal()));
        }
    }

    private void addWarning(Label warning, GridPane gp, ComboBox chComboBox, ComboBox modComboBox, TextField nItems){
        if (chComboBox.getSelectionModel().getSelectedItem() == null) {
            warning.setText("*");
            gp.add(warning, 4, 2);
            warning.setId("warning");
        }

        if (modComboBox.getSelectionModel().getSelectedItem() == null) {
            warning = new Label("*");
            gp.add(warning, 3, 1);
            warning.setId("warning");
        }

        if(nItems.getText() == null) {
            warning = new Label("*");
            gp.add(warning, 2, 5);
            warning.setId("warning");
        }
    }

    private void removeWarnings(GridPane gp, Label warning){
        ObservableList<Node> children = gp.getChildren();

        for(Node node: children) {
            if(gp.getRowIndex(node) == 2 && gp.getColumnIndex(node) == 4){
                gp.getChildren().remove(warning);
            }
        }
    }

    public static void main(String[] args) {
        controller = new Controller();

        launch(args);
    }
}
