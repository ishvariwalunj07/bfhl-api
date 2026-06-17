package com.example.bfhlapi.controller;

import com.example.bfhlapi.dto.RequestDto;
import com.example.bfhlapi.dto.ResponseDto;
import com.example.bfhlapi.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @PostMapping
    public ResponseDto processData(
            @RequestBody @Valid RequestDto request,
            @RequestHeader("X-Request-Id") String requestId) {

        return bfhlService.processData(
                request,
                requestId
        );
    }
}