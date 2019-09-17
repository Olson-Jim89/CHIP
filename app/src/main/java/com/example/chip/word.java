package com.example.chip;

public class word {

    word(String name){
        this.name = name;
    }

    private String name;
    private String POStag;
    private String definition;
    private String operation = "none";
    private word relatedWords[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPOStag() {
        return POStag;
    }

    public void setPOStag(String POStag) {
        this.POStag = POStag;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
