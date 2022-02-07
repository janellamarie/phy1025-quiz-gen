package main;

import java.util.ArrayList;

public class Controller {

    public static Model model;
    public static Main main;

    public Controller() {
        model = new Model();
    }

    public void initQuiz(Main main){
        this.main = main;
        model.readModInfo();
        main.initStartQuiz(model.getListOfChapters());
    }

    public void changeQuizNumber(int chNum){
        model.initQuestions(chNum);
    }

    public void startQuiz(int chNum, int nQuestions, int modNum){
        model.importExcelFile(modNum);
        model.initQuestions(chNum);
        model.quizRandomizer(nQuestions);
        main.initQuizLayout(chNum, modNum, model.getListOfQuestions());
    }

    public ArrayList<Integer> submitQuiz(ArrayList<Integer> answers){
        model.checkAnswers(answers);
        return model.getAnswers();
    }

    /** SETTERS AND GETTERS **/

    public static Model getModel() {
        return model;
    }

    public static void setModel(Model model) {
        Controller.model = model;
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Controller.main = main;
    }
}
