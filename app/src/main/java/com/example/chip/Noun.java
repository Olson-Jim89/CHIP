package com.example.chip;

import java.util.HashMap;

public class Noun {

    public String getAttribute(String Key) {
        return attribute.get(Key);
    }

    public void setAttribute(int index, String key, String value ) {
        this.attribute.put(key,value);
    }

    protected HashMap<String,String> attribute = new HashMap<String,String>(25);

    Noun(){
        attribute.put("what" ,"");
        attribute.put("when" ,"");
        attribute.put("where","");
        attribute.put("why"  ,"");
        attribute.put("how"  ,"");
    }


}

class Person extends Noun{
    Person(){
        attribute.put("name","");
    }
}