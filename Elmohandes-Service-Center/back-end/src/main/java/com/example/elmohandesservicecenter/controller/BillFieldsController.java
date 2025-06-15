package com.example.elmohandesservicecenter.controller;

import com.example.elmohandesservicecenter.sevice.BillFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillFieldsController {
    @Autowired
    private BillFieldsService billFieldsService;
}
