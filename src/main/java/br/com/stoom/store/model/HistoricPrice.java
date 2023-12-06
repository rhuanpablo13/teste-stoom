package br.com.stoom.store.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.stoom.store.response.HistoricPriceResponse;
import br.com.stoom.store.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricPrice {
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historic_price_sequence")
    @SequenceGenerator(name = "historic_price_sequence", sequenceName = "HISTORIC_PRICE_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date")
    private LocalDateTime date;
    

    public HistoricPriceResponse toHistoricPriceResponse() {
        return HistoricPriceResponse.builder()
        .id(id)
        .sku(sku)
        .price(price)
        .date(sku)
        .date(DateUtils.toFormat(date, DateUtils.yyyyMMdd_HHmmss))
        .build();
    }
}
