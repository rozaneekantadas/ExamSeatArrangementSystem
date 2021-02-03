package com.suptodas.diu.examseatarrangementsystem;

public class SaveExamData {

    String program, examName, semesterName, date, slot, courseName, levelTerm;

    public SaveExamData() {
    }

    public SaveExamData(String program, String examName, String semesterName, String date, String slot, String courseName, String levelTerm) {
        this.program = program;
        this.examName = examName;
        this.semesterName = semesterName;
        this.date = date;
        this.slot = slot;
        this.courseName = courseName;
        this.levelTerm = levelTerm;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLevelTerm() {
        return levelTerm;
    }

    public void setLevelTerm(String levelTerm) {
        this.levelTerm = levelTerm;
    }
}
