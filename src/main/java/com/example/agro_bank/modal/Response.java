package com.example.agro_bank.modal;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {
    private Status status;
    private Object object;


    public Response(Status status) {
        this.status =status;
    }
}
