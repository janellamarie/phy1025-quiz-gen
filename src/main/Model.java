package main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Model {

    private ArrayList<Question> listOfQuestions;
    private ArrayList<Chapter> listOfChapters;
    private ArrayList<Integer> answers;

    private XSSFWorkbook wb;

    /** TODO: close workbook */

    public Model() {
        listOfChapters = new ArrayList<>();
    }

    /**
     * @param n = chapter number
     */

    public void initQuestions(int n){
        listOfQuestions = new ArrayList<>();

        // navigate through excel sheet
        Sheet sheet = wb.getSheet("CH"+n);

        // for each Row in the Sheet
        for (Row row: sheet){
            Question temp = new Question();
            ArrayList<String> tempChoices =  new ArrayList<>();

            // for each Cell in the Row
            for(Cell cell: row){
                if(cell.getColumnIndex() == 0)
                    temp.setQuestion(cell.toString());
                else {
                    if (cell.toString() != null && cell.toString().compareToIgnoreCase("") != 0) {
                        if (checkIfCorrectAnswer(cell)) {
                            temp.setCorrectIndex(cell.getColumnIndex());
                        }
                        tempChoices.add(cell.toString());
                    }
                }
                temp.setChoices(tempChoices);
            }
            listOfQuestions.add(temp);
        }
    }

    private boolean checkIfCorrectAnswer(Cell cell){
        boolean isCorrectAnswer;

        XSSFCellStyle style = (XSSFCellStyle) cell.getCellStyle();
        XSSFFont font = style.getFont();
        isCorrectAnswer = font.getBold();

        return isCorrectAnswer;
    }

    public void readModInfo(){
        for(int i = 1; i < 6; i++){
            importExcelFile(i);
            Sheet sheet = wb.getSheet("ModInfo");

            for(Row row: sheet) {
                Chapter temp = new Chapter();
                for(Cell cell: row) {
                    if(cell.getColumnIndex() == 0)
                        temp.setNum((int)Double.parseDouble(cell.toString()));
                    else if (cell.getColumnIndex() == 1)
                        temp.setChTitle(cell.toString());
                    else
                        temp.setnTotal((int)cell.getNumericCellValue());
                }
                listOfChapters.add(temp);
            }
        }
    }

    public void importExcelFile(int modNum){
        // open excel file
        try{
            wb = new XSSFWorkbook(new File("src/excel files/M" + modNum + ".xlsx"));
        } catch (Exception e){
            // display error message
            System.out.println(e.toString());
        }
    }

    public void checkAnswers(ArrayList<Integer> answers){
        int score = 0, temp = 99;
        for(int i = 0; i < listOfQuestions.size(); i++){
            if(listOfQuestions.get(i).getCorrectIndex() == answers.get(i)){
                score++;
            } else {
                temp = answers.get(i) * -1;
                answers.set(i, temp);
            }
        }

        /** TODO: "AI", store score per run */
        System.out.println("Score: " + score);

        this.answers = answers;
    }

    public void quizRandomizer(int nQuestions) {
        ArrayList<Question> initialList = listOfQuestions;
        ArrayList<Question> randomList = new ArrayList<Question>();
        Random random = new Random();
        int j = 0;

        while(j < nQuestions){
            int randInt = random.nextInt(initialList.size());
            if (!findInList(initialList.get(randInt), randomList)) {
                randomList.add(initialList.get(randInt));
                j++;
            }
        }
        listOfQuestions = randomList;
    }

    private boolean findInList(Question toFind, ArrayList<Question> list){
        boolean isFound = false;

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getQuestion().compareToIgnoreCase(toFind.getQuestion()) == 0)
                isFound = true;
        }

        return isFound;
    }

    /** SETTERS AND GETTERS **/

    public ArrayList<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public void setListOfQuestions(ArrayList<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public ArrayList<Chapter> getListOfChapters() {
        return listOfChapters;
    }

    public void setListOfChapters(ArrayList<Chapter> listOfChapters) {
        this.listOfChapters = listOfChapters;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }
}
