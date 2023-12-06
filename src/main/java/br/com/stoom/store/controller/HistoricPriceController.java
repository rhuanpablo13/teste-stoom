package br.com.stoom.store.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.stoom.store.model.HistoricPrice;
import br.com.stoom.store.response.HistoricPriceResponse;
import br.com.stoom.store.response.Response;
import br.com.stoom.store.services.HistoricPriceService;

@Controller
@RequestMapping("/historic-price")
public class HistoricPriceController {
    
    @Autowired
    private HistoricPriceService historicPriceService;



    @GetMapping("/find-min-price")
    public ResponseEntity<Response<List<HistoricPriceResponse>>> findMinPrice() {

        Response<List<HistoricPriceResponse>> response = new Response<>();
        try {
            List<HistoricPriceResponse> hp = historicPriceService.findMinPrice()
            .stream()
            .map(HistoricPrice::toHistoricPriceResponse)
            .collect(Collectors.toList());
            response.setData(hp);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/find-max-price")
    public ResponseEntity<Response<List<HistoricPriceResponse>>> findMaxPrice()  {

        Response<List<HistoricPriceResponse>> response = new Response<>();
        try {
            List<HistoricPrice> temp = historicPriceService.findMaxPrice();
            temp.forEach(t -> System.out.println(t.toString()));
            List<HistoricPriceResponse> hp = historicPriceService.findMaxPrice()
            .stream()
            .map(HistoricPrice::toHistoricPriceResponse)
            .collect(Collectors.toList());
            response.setData(hp);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/find-all-between-date-sku")
    public ResponseEntity<Response<List<HistoricPriceResponse>>> findAllBetweenDateAndSku(
        @RequestParam("dataInicioParam") String dataInicioParam, 
        @RequestParam("dataFimParam") String dataFimParam,
        @RequestParam("sku") String sku) 
    {
        Response<List<HistoricPriceResponse>> response = new Response<>();
        try {
            List<HistoricPriceResponse> hp = historicPriceService.findAllBetweenDateAndSku(dataInicioParam, dataFimParam, sku)
            .stream()
            .map(HistoricPrice::toHistoricPriceResponse)
            .collect(Collectors.toList());
            response.setData(hp);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/find-all-between-date")
    public ResponseEntity<Response<List<HistoricPriceResponse>>> findAllBetweenDate(@RequestParam("dataInicioParam") String dataInicioParam, @RequestParam("dataFimParam") String dataFimParam) {
        
        Response<List<HistoricPriceResponse>> response = new Response<>();
        try {
            List<HistoricPriceResponse> hp = historicPriceService.findAllBetweenDate(dataInicioParam, dataFimParam)
            .stream()
            .map(HistoricPrice::toHistoricPriceResponse)
            .collect(Collectors.toList());
            response.setData(hp);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
