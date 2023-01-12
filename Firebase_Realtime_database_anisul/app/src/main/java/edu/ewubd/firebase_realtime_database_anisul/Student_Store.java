package edu.ewubd.firebase_realtime_database_anisul;

public class Student_Store {


    private String name;
    private  String age;


    public Student_Store(String name, String age){ //constructor
        this.name = name;
        this.age = age;
    }

    public String getName() {  //data set korte
        return name;
    }

    public void setName(String name) { //data get korte
        this.name = name;
    }
}
