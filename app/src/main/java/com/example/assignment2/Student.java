package com.example.assignment2;
//Student class to show particular rows
public class Student
{
        private int studeentId;
        private String fname;
        private String lname;   //Student properties
        private int marks;
        private String progCode;
        private int credits;

    public Student(int studeentId, String fname, String lname, int marks,String progCode, int credits) {
        this.studeentId = studeentId;
        this.fname = fname;
        this.lname = lname;
        this.marks = marks;
        this.progCode = progCode;
        this.credits = credits;
    }
    public Student()
    {
            //Default Constructor
    }
       //Getter and Setter properties

        public int getStudeentId() {
            return studeentId;
        }

        public void setStudeentId(int studeentId) {
            this.studeentId = studeentId;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getProgCode() {
            return progCode;
        }

        public void setProgCode(String progCode) {
            this.progCode = progCode;
        }

        public int getMarks() {
            return marks;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }
}
