package br.com.stoom.store.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stoom.store.model.HistoricPrice;

@Repository
public interface HistoricPriceRepository extends JpaRepository<HistoricPrice, Long> {
    
    @Query("SELECT hp FROM HistoricPrice hp WHERE hp.date BETWEEN :dataInicio AND :dataFim")
    List<HistoricPrice> findAllBetweenDate(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT hp FROM HistoricPrice hp WHERE hp.date BETWEEN :dataInicio AND :dataFim AND hp.sku =:sku")
    List<HistoricPrice> findAllBetweenDateAndSku(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim, @Param("sku") String sku);

    @Query("SELECT hp FROM HistoricPrice hp WHERE hp.price = (SELECT MAX(hp2.price) FROM HistoricPrice hp2)")
    List<HistoricPrice> findMaxPrice();

    @Query("SELECT hp FROM HistoricPrice hp WHERE hp.price = (SELECT MIN(hp2.price) FROM HistoricPrice hp2)")
    List<HistoricPrice> findMinPrice();

}
