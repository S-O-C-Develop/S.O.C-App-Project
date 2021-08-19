package com.example.soc.board.Menu.Comty;

import com.google.gson.annotations.SerializedName;

public class Compty_problem_write_condition_Data {

    @SerializedName("subject")
    private String subject;
    @SerializedName("semester")
    private int semester;
    @SerializedName("korName")
    private String korname;
    @SerializedName("grade")
    private int grade;
    @SerializedName("engName")
    private String engname;
    @SerializedName("category")
    private String category;
public Compty_problem_write_condition_Data(String category, String engname, int grade, String korname, int semester, String subject){
    this.subject = subject;
    this.semester = semester;
    this.grade = grade;
    this.category = category;
    this.engname = engname;
    this.korname = korname;
}
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getKorname() {
        return korname;
    }

    public void setKorname(String korname) {
        this.korname = korname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}


