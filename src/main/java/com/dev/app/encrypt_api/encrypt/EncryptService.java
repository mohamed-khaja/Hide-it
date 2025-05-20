package com.dev.app.encrypt_api.encrypt;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dev.app.encrypt_api.dto.EncryptObject;

@Service
public class EncryptService {

    private static final Logger logger = Logger.getLogger(EncryptService.class.getName());

    @Value("${jasypt.encryption.algorithm}")
    private String ALGORITHM;

    @Value("${jasypt.encryption.defaultSecret}")
    private String DEFAULT_SECRET;

    private String encryptOrDecrypt(String action, String text, String secret) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(ALGORITHM);
        if (secret != null && !secret.equals("")) {
            encryptor.setPassword(secret);
        } else {
            logger.warning("NO PASSWORD PROVIDED: Using Default Password");
            encryptor.setPassword(DEFAULT_SECRET);
        }
        encryptor.setIvGenerator(new RandomIvGenerator());
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setKeyObtentionIterations(1000);
        return action.equalsIgnoreCase("encrypt") ? encryptor.encrypt(text) : encryptor.decrypt(text);
    }

    public List<String> encryptOrDecryptList(String action, List<EncryptObject> encryptObjects) {

        List<String> resultlList = encryptObjects.stream()
                .map(pair -> encryptOrDecrypt(action, pair.getText(), pair.getSecret()))
                .collect(Collectors.toList());
        return resultlList;
    }
}
