package com.dev.app.encrypt_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.encrypt_api.dto.EncryptObject;
import com.dev.app.encrypt_api.encrypt.EncryptService;

@RestController
public class EncryptController {

    private final EncryptService encyptService;

    public EncryptController(EncryptService encyptService) {
        this.encyptService = encyptService;
    }

    @PostMapping("/{action:encrypt|decrypt}")

    public ResponseEntity<List<String>> commonApi(@PathVariable("action") String action, @RequestBody List<EncryptObject> encyptList) {

        List<String> result = encyptService.encryptOrDecryptList(action, encyptList);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
