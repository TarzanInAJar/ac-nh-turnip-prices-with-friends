package com.mytechuncle.acnh.models.ui;

public class TurnipWeekLocalStorage {
    private boolean firstBuy;
    private int previousPattern;
    private int[] prices;

    public boolean isFirstBuy() {
        return firstBuy;
    }

    public void setFirstBuy(boolean firstBuy) {
        this.firstBuy = firstBuy;
    }

    public int getPreviousPattern() {
        return previousPattern;
    }

    public void setPreviousPattern(int previousPattern) {
        this.previousPattern = previousPattern;
    }

    public int[] getPrices() {
        return prices;
    }

    public void setPrices(int[] prices) {
        this.prices = prices;
    }
}
