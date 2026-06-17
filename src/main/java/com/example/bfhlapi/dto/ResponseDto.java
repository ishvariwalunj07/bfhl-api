package com.example.bfhlapi.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class ResponseDto {

    private boolean success;
    private String requestId;

    private List<String> oddNumbers;
    private List<String> evenNumbers;
    private List<String> alphabets;
    private List<String> specialCharacters;

    private String sum;
    private String largestNumber;
    private String smallestNumber;

    private int alphabetCount;
    private int numberCount;
    private int specialCharacterCount;

    private boolean containsDuplicates;
    private long processingTimeMs;

    private int uniqueElementCount;
    private List<Double> sortedNumbers;

    private int vowelCount;

    private String longestAlphabeticValue;
    private String shortestAlphabeticValue;

    private Map<String, Integer> alphabetFrequency;

    private SummaryDto summary;
}