package com.example.agro_bank.service.impl;


import com.example.agro_bank.entity.Agro_BankExcel_by_city;
import com.example.agro_bank.entity.Agro_BankExcel_by_id;
import com.example.agro_bank.modal.Response;
import com.example.agro_bank.modal.Status;
import com.example.agro_bank.repository.AgroBankRepositoryByCity;
import com.example.agro_bank.repository.AgroBankRepositoryById;
import com.example.agro_bank.service.Agro_BankService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.*;


@Service
public class Agro_BankServiceImpl implements Agro_BankService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AgroBankRepositoryById agroBankRepositoryById;
    @Autowired
    AgroBankRepositoryByCity agroBankRepositoryByCity;

    @Override
    public Response excel(MultipartHttpServletRequest file, boolean b) {
        Response response = new Response();
        Status status = new Status();
        try {
            Workbook workbook = new XSSFWorkbook
                    (Objects.requireNonNull(file.getFile("file")).getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum() + 1;
            List<Agro_BankExcel_by_id> by_ids = new ArrayList<>();
            List<Agro_BankExcel_by_city> by_cities = new ArrayList<>();
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (b) {
                    Agro_BankExcel_by_id agro_bankExcel = new Agro_BankExcel_by_id();
                    agro_bankExcel.setCityCode(row.getCell(0).getNumericCellValue());
                    agro_bankExcel.setCity(String.valueOf(row.getCell(1)));
                    by_ids.add(agro_bankExcel);
                } else {
                    Agro_BankExcel_by_city agro_bankExcel_by_city = new Agro_BankExcel_by_city();
                    agro_bankExcel_by_city.setCityCode(row.getCell(0).getNumericCellValue());
                    agro_bankExcel_by_city.setCity(String.valueOf(row.getCell(1)));
                    by_cities.add(agro_bankExcel_by_city);
                }

            }
            if (b) {
                by_ids.sort(Comparator.comparing(Agro_BankExcel_by_id::getCityCode));
                agroBankRepositoryById.saveAll(by_ids);
            } else {
                by_cities.sort(Comparator.comparing(Agro_BankExcel_by_city::getCity));
                agroBankRepositoryByCity.saveAll(by_cities);
            }
        } catch (IOException e) {
            status.setCode(500);
            status.setMassege(e.toString());
            e.printStackTrace();
        }
        response.setObject(status);
        return response;
    }

}
