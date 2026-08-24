package br.com.almoxarifado.model;

public class Request {
    private String numberRequest;
    private Branch branch;

    public Request(String numberRequest, Branch branch) {
        this.numberRequest = numberRequest;
        this.branch = branch;

    }


}
