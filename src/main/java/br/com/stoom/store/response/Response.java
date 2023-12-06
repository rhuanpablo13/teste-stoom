package br.com.stoom.store.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por padronizar o formato dos responses
 */
public class Response <T> {
    
    private T data;
    private List<String> errors;
    private List<String> messages;

    public Response() {
        errors = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public Response(T data) {
        this.data = data;
        errors = new ArrayList<>();
    }

    public void addMessage(String message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
    
    public void addError(Exception ex) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(ex.getMessage());
    }

    public String getErrors() {
        if (this.errors.isEmpty()) {
            return null;
        }
        return "[" + String.join(",", errors) + "]";
    }
    
    public Boolean containErrors() {
        return this.errors != null && !this.errors.isEmpty();
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}
