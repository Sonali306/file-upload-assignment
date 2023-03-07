package com.dbs.assignment.Model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
public class Margin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int marginId;
    private String instruction;
    @Column(length = 3)
    private String baseCcy;
    @Column(length = 3)
    private String termCcy;
    private String tradeTier;
    private String fromAmount;
    private String toAmt;
    private String amountCcy;
    private char bidOperator;
    private String bidModifier;
    private char askOperator;
    private String askModifier;
    private String remarks;

    public Margin() {

    }
    public Margin(String instruction, String baseCcy, String termCcy, String tradeTier, String fromAmount, String toAmt,
                  String amountCcy, char bidOperator, String bidModifier, char askOperator, String askModifier, String remarks) {
        this.instruction = instruction;
        this.baseCcy = baseCcy;
        this.termCcy = termCcy;
        this.tradeTier = tradeTier;
        this.fromAmount = fromAmount;
        this.toAmt = toAmt;
        this.amountCcy = amountCcy;
        this.bidOperator = bidOperator;
        this.bidModifier = bidModifier;
        this.askOperator = askOperator;
        this.askModifier = askModifier;
        this.remarks = remarks;
    }

    public int getMarginId() {
        return marginId;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    public String getTermCcy() {
        return termCcy;
    }

    public void setTermCcy(String termCcy) {
        this.termCcy = termCcy;
    }

    public String getTradeTier() {
        return tradeTier;
    }

    public void setTradeTier(String tradeTier) {
        this.tradeTier = tradeTier;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmt() {
        return toAmt;
    }

    public void setToAmt(String toAmt) {
        this.toAmt = toAmt;
    }

    public String getAmountCcy() {
        return amountCcy;
    }

    public void setAmountCcy(String amountCcy) {
        this.amountCcy = amountCcy;
    }

    public char getBidOperator() {
        return bidOperator;
    }

    public void setBidOperator(char bidOperator) {
        this.bidOperator = bidOperator;
    }

    public String getBidModifier() {
        return bidModifier;
    }

    public void setBidModifier(String bidModifier) {
        this.bidModifier = bidModifier;
    }

    public char getAskOperator() {
        return askOperator;
    }

    public void setAskOperator(char askOperator) {
        this.askOperator = askOperator;
    }

    public String getAskModifier() {
        return askModifier;
    }

    public void setAskModifier(String askModifier) {
        this.askModifier = askModifier;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Margin{" +
                "marginId=" + marginId +
                ", instruction='" + instruction + '\'' +
                ", baseCcy='" + baseCcy + '\'' +
                ", termCcy='" + termCcy + '\'' +
                ", tradeTier='" + tradeTier + '\'' +
                ", fromAmount='" + fromAmount + '\'' +
                ", toAmt='" + toAmt + '\'' +
                ", amountCcy='" + amountCcy + '\'' +
                ", bidOperator=" + bidOperator +
                ", bidModifier='" + bidModifier + '\'' +
                ", askOperator=" + askOperator +
                ", askModifier='" + askModifier + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
