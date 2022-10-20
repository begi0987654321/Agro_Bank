package com.example.agro_bank.modal;

import lombok.Data;

@Data
public class Status {
    private String massege;
    private long code;

    public Status(String massege,long code){
        this.massege = massege;
        this.code = code;
    }

    public Status() {
    }
}
