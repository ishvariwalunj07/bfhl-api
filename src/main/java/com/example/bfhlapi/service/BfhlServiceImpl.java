package com.example.bfhlapi.service;
import java.util.HashMap;
import java.util.Map;
import com.example.bfhlapi.dto.RequestDto;
import com.example.bfhlapi.dto.ResponseDto;
import org.springframework.stereotype.Service;
import com.example.bfhlapi.dto.SummaryDto;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDto processData(RequestDto request, String requestId) {

        long startTime = System.currentTimeMillis();

        List<Double> allNumbers = new ArrayList<>();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();

        Set<String> uniqueValues = new LinkedHashSet<>();
        boolean containsDuplicates = false;

        double sum = 0;

        int alphabetCount = 0;
        int numberCount = 0;
        int specialCharacterCount = 0;
        Map<String, Integer> alphabetFrequency = new HashMap<>();

        int vowelCount = 0;

        String longestAlphabeticValue = null;
        String shortestAlphabeticValue = null;

        for (String value : request.getData()) {

            if (value == null || value.trim().isEmpty()) {
                continue;
            }

            if (uniqueValues.contains(value)) {
                containsDuplicates = true;
            }

            uniqueValues.add(value);

            if (value.matches("-?\\d+(\\.\\d+)?")) {

                double num = Double.parseDouble(value);

                allNumbers.add(num);

                sum += num;
                numberCount++;

                if (num % 2 == 0) {
                    evenNumbers.add(value);
                } else {
                    oddNumbers.add(value);
                }

            } else if (value.matches("[a-zA-Z]+")) {

                String upper = value.toUpperCase();

                alphabets.add(upper);
                alphabetCount++;

                if (longestAlphabeticValue == null ||
                        upper.length() > longestAlphabeticValue.length()) {

                    longestAlphabeticValue = upper;
                }

                if (shortestAlphabeticValue == null ||
                        upper.length() < shortestAlphabeticValue.length()) {

                    shortestAlphabeticValue = upper;
                }

                for (char ch : upper.toCharArray()) {

                    String letter = String.valueOf(ch);

                    alphabetFrequency.put(
                            letter,
                            alphabetFrequency.getOrDefault(letter, 0) + 1
                    );

                    if ("AEIOU".contains(letter)) {
                        vowelCount++;
                    }
                }

            } else {

                specialCharacters.add(value);
                specialCharacterCount++;
            }
        }

        ResponseDto response = new ResponseDto();

        response.setSuccess(true);
        response.setRequestId(requestId);

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);

        response.setSum(String.valueOf(sum));

        response.setAlphabetCount(alphabetCount);
        response.setNumberCount(numberCount);
        response.setSpecialCharacterCount(specialCharacterCount);

        response.setContainsDuplicates(containsDuplicates);
        response.setUniqueElementCount(uniqueValues.size());

        if (!allNumbers.isEmpty()) {

            allNumbers.sort(Double::compareTo);

            response.setSortedNumbers(allNumbers);

            response.setSmallestNumber(
                    String.valueOf(allNumbers.get(0)));

            response.setLargestNumber(
                    String.valueOf(
                            allNumbers.get(allNumbers.size() - 1)
                    ));
        }

        long endTime = System.currentTimeMillis();
        response.setProcessingTimeMs(endTime - startTime);
        response.setAlphabetFrequency(alphabetFrequency);

        response.setVowelCount(vowelCount);

        response.setLongestAlphabeticValue(
                longestAlphabeticValue);

        response.setShortestAlphabeticValue(
                shortestAlphabeticValue);

        SummaryDto summary = new SummaryDto();

        summary.setTotalElementsReceived(
                request.getData().size());

        summary.setValidElementsProcessed(
                uniqueValues.size());

        summary.setInvalidElementsIgnored(
                request.getData().size() -
                        uniqueValues.size());

        response.setSummary(summary);
        return response;
    }
}