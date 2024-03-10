package com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models.Client;
import com.luis.curso.springboot.inyec.depen.factura.springbootinyecdepfactura.models.Invoice;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/show")
    public Invoice show(){
        Invoice inv = new Invoice();
        Client client = new Client();
        client.setName(invoice.getClient().getName());
        client.setLastName(invoice.getClient().getLastName());
        inv.setClient(client);
        inv.setDescription(invoice.getDescription());
        inv.setItems(invoice.getItems());
        return  inv;
    }
}
