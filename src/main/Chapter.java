package main;

public class Chapter {

    private int num, nTotal;
    private String chTitle;

    public Chapter(int num, String chTitle, int nTotal){
        this.num = num;
        this.chTitle = chTitle;
        this.nTotal = nTotal;
    }

    public Chapter() {
        // empty constructor
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "num=" + num +
                ", nTotal=" + nTotal +
                ", chTitle='" + chTitle + '\'' +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getChTitle() {
        return chTitle;
    }

    public void setChTitle(String chTitle) {
        this.chTitle = chTitle;
    }

    public int getnTotal() {
        return nTotal;
    }

    public void setnTotal(int nTotal) {
        this.nTotal = nTotal;
    }
}
