package com.example.shyse.tipcalculator;

public class TipCalculator {

    public int total = 0;
    public int tipAmount = 0;
    public int percent = 0;
    public int split = 0;
    public int subtotal = 0;

    public TipCalculator(int total, int tipAmount, int percent, int split, int subtotal)
    {
        this.total = total;
        this.tipAmount = tipAmount;
        this.percent = percent;
        this.split = split;
        this.subtotal = subtotal;
    }

    public int calculatedTotal() {
        int calculation = total * tipAmount;
        return 0;
    }
}

