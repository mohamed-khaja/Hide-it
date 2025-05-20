package com.dev.app.encrypt_api.dto;

public class EncryptObject {

    private String text;
    private String secret;

    public EncryptObject(String text, String secret) {
        this.text = text;
        this.secret = secret;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
