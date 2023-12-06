package br.com.stoom.store.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.stoom.store.response.Response;
import br.com.stoom.store.services.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
    
    @Autowired
    private ReportService reportService;



    @GetMapping("/all")
    public ResponseEntity<Response<HashMap<String, Object>>> findMinPrice() {

        Response<HashMap<String, Object>> response = new Response<>();
        try {
            HashMap<String, Object> report = reportService.reportAll();
            response.setData(report);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
