package com.example.bfhlapi.dto;

public class SummaryDto {

    private int totalElementsReceived;
    private int validElementsProcessed;
    private int invalidElementsIgnored;

    public int getTotalElementsReceived() {
        return totalElementsReceived;
    }

    public void setTotalElementsReceived(int totalElementsReceived) {
        this.totalElementsReceived = totalElementsReceived;
    }

    public int getValidElementsProcessed() {
        return validElementsProcessed;
    }

    public void setValidElementsProcessed(int validElementsProcessed) {
        this.validElementsProcessed = validElementsProcessed;
    }

    public int getInvalidElementsIgnored() {
        return invalidElementsIgnored;
    }

    public void setInvalidElementsIgnored(int invalidElementsIgnored) {
        this.invalidElementsIgnored = invalidElementsIgnored;
    }
}