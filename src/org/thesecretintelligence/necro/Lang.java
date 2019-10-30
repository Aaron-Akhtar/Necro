package org.thesecretintelligence.necro;

public enum Lang {

    /**Necro's Copyright*/
    COPYRIGHT("Copyright (c) 2019 - The Secret Intelligence Squadron (TheSecretIntelligence.org), All rights reserved."),
    ERROR_INVALID_INPUT("%s% is not recognised as a valid command, type help if you require assistance."),
    /**Print correct args via next line.*/
    ERROR_INCORRECT_USAGE("Invalid Argument(s)!");

    private String content;
    Lang(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }


}
