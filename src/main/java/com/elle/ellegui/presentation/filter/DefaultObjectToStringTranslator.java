package com.elle.ellegui.presentation.filter;

public class DefaultObjectToStringTranslator implements    IObjectToStringTranslator {

    @Override
    public String translate(Object obj) {
        return obj == null? "": obj.toString().toLowerCase();
    }

}