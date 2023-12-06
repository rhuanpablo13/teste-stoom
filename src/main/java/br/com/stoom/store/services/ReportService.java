package br.com.stoom.store.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.HistoricPrice;
import br.com.stoom.store.model.Product;

@Service
public class ReportService {
    
    @Autowired
    private ProductService productService;

    @Autowired
    private HistoricPriceService historicPriceService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;


    /**
     * Build report with all informations of the products
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> reportAll() throws Exception {

        HashMap<String, Object> report = new HashMap<>();

        List<HistoricPrice> maxPrice = historicPriceService.findMaxPrice();
        List<HistoricPrice> minPrice = historicPriceService.findMinPrice();

        List<Product> allActivesProducts = productService.findAll(true);
        List<Product> allDisablesProducts = productService.findAll(false);

        List<Category> allActivesCategory = categoryService.findAll(true);
        List<Category> allDisablesCategory = categoryService.findAll(false);

        List<Brand> allActivesBrand = brandService.findAll(true);
        List<Brand> allDisablesBrand = brandService.findAll(false);

        
        report.put("produto-maior-preco", maxPrice);
        report.put("produto-menor-preco", minPrice);
        
        report.put("produtos-ativos", allActivesProducts);
        report.put("produtos-inativos", allDisablesProducts);
        
        report.put("categorias-ativas", allActivesCategory);
        report.put("categorias-inativas", allDisablesCategory);
        
        report.put("marcas-ativas", allActivesBrand);
        report.put("marcas-inativas", allDisablesBrand);
        
        report.put("quantidade-produtos-ativos", allActivesProducts.size());
        report.put("quantidade-produtos-inativos", allDisablesProducts.size());
        
        HashMap<String, Object> categorias = new HashMap<>();

        // produtos por categorias ativas
        allActivesCategory.forEach(c -> {
            try {
                List<Product> temp = productService.findAllByCategory(c.getCategory());
                categorias.put(c.getCategory(), temp);
            } catch (Exception e) { }
        });
        report.put("categorias", categorias);


        HashMap<String, Object> marcas = new HashMap<>();

        // produtos por marcas ativas
        allActivesBrand.forEach(b -> {
            try {
                List<Product> temp = productService.findAllByBrand(b.getBrand());
                marcas.put(b.getBrand(), temp);
            } catch (Exception e) { }
        });
        report.put("marcas", marcas);

        return report;
    }
}
