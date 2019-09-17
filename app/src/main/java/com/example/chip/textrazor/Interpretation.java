package com.example.chip.textrazor;
enum Type{question,statement,command}
enum command {define}
public class Interpretation {
    Type type;


    private String subject;
    private String action;
    private String answer;
    private String theBeInToBe;
    private String response;


    public Interpretation(Type type){
        this.type = type;
    }

    public Interpretation() {

    }

    public void searchMemories(String subject){
        for (int i =0; i < i; i++){
            if (subject == "memories[i].subject"){
                answer = "memories[i].answer";
            }
        }
    }

    public void setResponse(){
        switch (type){
            case question:
                searchMemories(subject);
                response = subject + "s" + "are" + answer;
                break;
            case command:
                response = "in process";
                break;
            case statement:
                response = "okay" + subject + "s" + "are" + theBeInToBe;
                break;
        }
    }
}
