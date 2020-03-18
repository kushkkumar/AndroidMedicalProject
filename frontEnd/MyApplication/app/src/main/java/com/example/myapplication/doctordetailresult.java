package com.example.myapplication;

public class doctordetailresult {

    String doctorid;
    String name;
    String speciality;
    String intime;
    String outtime;
    String fees;

    public doctordetailresult() {
    }

    public doctordetailresult(String doctorid, String name, String speciality, String intime, String outtime, String fees) {
        this.doctorid = doctorid;
        this.name = name;
        this.speciality = speciality;
        this.intime = intime;
        this.outtime = outtime;
        this.fees = fees;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }
}
