package com.microservices.services.web;

public class SearchCriteria {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "id='" + id + '\'' +
                '}';
    }
}
