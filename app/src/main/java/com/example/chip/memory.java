package com.example.chip;

public class memory {
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String[] getRelation() {
        return relation;
    }

    public void setRelation(String relation, int index) {
        this.relation[index] = relation;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    private String subject;
    private String context;
    private String relation[];
    private String meaning;

}
