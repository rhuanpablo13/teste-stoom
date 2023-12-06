package br.com.stoom.store.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.store.messages.Messages;
import br.com.stoom.store.model.HistoricPrice;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.HistoricPriceRepository;
import br.com.stoom.store.utils.DateUtils;

@Service
public class HistoricPriceService {
    
    private Messages messages;

    @Autowired
    private HistoricPriceRepository historicPriceRepository;

    @Autowired
    private ProductService productService;


    public HistoricPriceService() {
        this.messages = new Messages(HistoricPriceService.class, HistoricPrice.class.getSimpleName());
    }


    /**
     * Find sku with minus historic price
     * @return
     */
    public List<HistoricPrice> findMinPrice() {
        messages.buscandoDados();
        return historicPriceRepository.findMinPrice();
    }

    /**
     * Find sku with maximus historic price
     * @return
     */
    public List<HistoricPrice> findMaxPrice() {
        messages.buscandoDados();
        return historicPriceRepository.findMaxPrice();
    }

    /**
     * Find sku with between dates
     * @param dataInicio
     * @param dataFim
     * @param sku
     * @return
     */
    public List<HistoricPrice> findAllBetweenDateAndSku(String dataInicioParam, String dataFimParam, String sku) {
        messages.buscandoDados();
        LocalDateTime dataInicio = DateUtils.toLocalDate(dataInicioParam, DateUtils.yyyyMMdd_HHmmss);
        LocalDateTime dataFim = DateUtils.toLocalDate(dataFimParam, DateUtils.yyyyMMdd_HHmmss);
        return historicPriceRepository.findAllBetweenDateAndSku(dataInicio, dataFim, sku);
    }

    /**
     * Find sku's with minus historic price
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<HistoricPrice> findAllBetweenDate(String dataInicioParam, String dataFimParam) {
        messages.buscandoDados();
        LocalDateTime dataInicio = DateUtils.toLocalDate(dataInicioParam, DateUtils.yyyyMMdd_HHmmss);
        LocalDateTime dataFim = DateUtils.toLocalDate(dataFimParam, DateUtils.yyyyMMdd_HHmmss);        
        return historicPriceRepository.findAllBetweenDate(dataInicio, dataFim);
    }


    /**
     * 
     * @param sku
     * @param price
     * @throws Exception
     */
    public void registryHistoric(String sku, BigDecimal price) throws Exception {
        Optional<Product> product = productService.findBySku(sku);
        if (product.isPresent()) {
            if (product.get().getActive() == false) {
                throw new Exception(messages.printMessage("Não é possível registrar um histórico de preço para um produto inativo. Produto -> SKU:{}, Nome:{}", product.get().getSku(), product.get().getName()));
            }

            messages.printMessage("Salvando novo histórico de preço para o produto: {}", product.get().toProductResponse());
            HistoricPrice hp = new HistoricPrice();
            hp.setDate(LocalDateTime.now());
            hp.setPrice(price);
            hp.setSku(sku);

            if (historicPriceRepository.save(hp) != null) {
                messages.registroInseridoSucesso();
                return;
            }
            throw new Exception(messages.registroInseridoErro());
        }
    }
}
