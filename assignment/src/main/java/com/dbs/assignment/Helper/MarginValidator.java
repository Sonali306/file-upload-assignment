package com.dbs.assignment.Helper;

import com.dbs.assignment.Exception.FileNotValidException;
import com.dbs.assignment.Model.Margin;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MarginValidator {

    private static boolean validateInteger(String n) {
        try {
            Integer tradeTier = Integer.parseInt(n);
            if(tradeTier<0)
                return false;
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private static boolean validateDouble(String n) {
        try {
           Double.parseDouble(n);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private static boolean validateOperator(char c) {
        if(c=='+' || c=='-') return true;
        return false;
    }
    public  static void validateMargin(Margin margin) {
        System.out.println("Inside Validation");
        List<String> exceptions = new ArrayList<>();

        if(!"*".equals(margin.getBaseCcy()) && margin.getBaseCcy().length()!=3) {
            exceptions.add("Base Ccy should be of length 3");
        }
        if(!"*".equals(margin.getTermCcy()) && margin.getTermCcy().length()!=3) {
            exceptions.add("Term Ccy should be of length 3");
        }

        if(false==validateInteger(margin.getTradeTier())) {
            exceptions.add("Trade Tier should be int and greater than 0");
        }

        if(false==validateInteger(margin.getFromAmount())) {
            exceptions.add("From amt should be int and greater than 0");
        }

        if(false==validateInteger(margin.getToAmt())) {
            exceptions.add("To amt should be int and greater than 0");
        }

        if(false==validateOperator(margin.getBidOperator())){
            exceptions.add("Bid Operator should be either + or -");
        }

        if(false==validateOperator(margin.getAskOperator())){
            exceptions.add("Ask Operator should be either + or -");
        }

        if(false==validateDouble(margin.getAskModifier())){
            exceptions.add("Ask Modifier should be double");
        }

        if(false==validateDouble(margin.getBidModifier())){
            exceptions.add("Bid Modifier should be double");
        }

        if(exceptions.size()>0) {
            throw new FileNotValidException("File is not valid."+exceptions.stream().collect(Collectors.joining(" , ")));
        }
    }
}
