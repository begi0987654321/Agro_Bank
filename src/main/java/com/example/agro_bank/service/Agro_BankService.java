package com.example.agro_bank.service;

import com.example.agro_bank.modal.Response;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface Agro_BankService {
    Response excel(MultipartHttpServletRequest file, boolean b);
}
