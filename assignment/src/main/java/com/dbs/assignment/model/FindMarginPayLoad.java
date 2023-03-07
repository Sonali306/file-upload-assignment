package com.dbs.assignment.model;

public class FindMarginPayLoad {
    String instruction;
    String baseCcy;
    String termCcy;
    String tradeTier;
    String amount;

    public FindMarginPayLoad(String instruction, String baseCcy, String termCcy, String tradeTier, String amount) {
        this.instruction = instruction;
        this.baseCcy = baseCcy;
        this.termCcy = termCcy;
        this.tradeTier = tradeTier;
        this.amount = amount;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FindMarginPayLoad{" +
                "instruction='" + instruction + '\'' +
                ", baseCcy='" + baseCcy + '\'' +
                ", termCcy='" + termCcy + '\'' +
                ", tradeTier='" + tradeTier + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
