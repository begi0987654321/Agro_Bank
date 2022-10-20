package com.example.agro_bank.controller;


import com.example.agro_bank.modal.Response;
import com.example.agro_bank.service.Agro_BankService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/api/agrobank")
public class Agro_BankControler {
    private final Agro_BankService agro_bankService;

    public Agro_BankControler(Agro_BankService agro_bankService) {
        this.agro_bankService = agro_bankService;
    }

    @PostMapping("/upload_by_id")
    private HttpEntity<?> excelById(MultipartHttpServletRequest file) {
        Response response = agro_bankService.excel(file, true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload_by_city")
    private HttpEntity<?> excelByCity(MultipartHttpServletRequest file) {
        Response response = agro_bankService.excel(file, false);
        return ResponseEntity.ok(response);
    }
}
