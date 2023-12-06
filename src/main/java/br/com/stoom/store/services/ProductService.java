package br.com.stoom.store.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.store.messages.Messages;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import br.com.stoom.store.request.ProductRequest;
import br.com.stoom.store.services.interfaces.IService;

@Service
public class ProductService implements IService<Product, Long, ProductRequest> {

    private Messages messages;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HistoricPriceService historicPriceService;



    public ProductService() {
        this.messages = new Messages(ProductService.class, Product.class.getSimpleName());
    }

    

    /**
     * 
     * @param sku
     * @return
     */
    public Optional<Product> findBySku(String sku) {
        return productRepository.findBySku(sku);
    }
    

    /**
     * 
     * @param brandParam
     * @return
     * @throws Exception
     */
    public List<Product> findAllByBrand(String brandParam) throws Exception {
        Optional<Brand> brand = brandService.findByBrand(brandParam);
        if (brand.isPresent()) {
            return productRepository.findAllByBrand(brand.get());
        }
        throw new Exception(messages.registroNaoEncontradoBaseDados(brandParam));
    }

    
    /**
     * 
     * @param categoryParam
     * @return
     * @throws Exception
     */
    public List<Product> findAllByCategory(String categoryParam) throws Exception {
        Optional<Category> category = categoryService.findByCategory(categoryParam);
        if (category.isPresent()) {
            return productRepository.findAllByCategory(category.get());
        }
        throw new Exception(messages.registroNaoEncontradoBaseDados(categoryParam));
    }


    /**
     * Find all products active or disabled
     * @param active
     * @return
     * @throws Exception
     */
    public List<Product> findAll(Boolean active) throws Exception {
        
        if (active == null) {
            return findAll();
        }

        messages.buscandoDados();

        List<Product> products = productRepository.findAllByActive(active);
        messages.quantidadeDados(products.size());
        return products;
    }

    @Override
    public List<Product> findAll() throws Exception {
        
        messages.buscandoDados();

        // buscando as entidades
        List<Product> products = productRepository.findAll();
        messages.quantidadeDados(products.size());
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) throws Exception {

        // validando id
        if (id == null) {
            throw new Exception(messages.informeIdValido());
        }

        messages.buscandoPorId(id);

        // buscando por id
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> create(ProductRequest request) throws Exception {
        // validando request
        validate(request, true);

        // salvando nova entidade
        Product product = productRepository.save(toProduct(request));
        if (product != null) {
            messages.registroInseridoSucesso();

            messages.printMessage("Registrando histórico de preço: {} para o produto: {}", product.getSku(), product.getPrice());
            historicPriceService.registryHistoric(product.getSku(), product.getPrice());

            return Optional.of(product);
        }

        // se não salvou, retorna falha
        throw new Exception(messages.registroInseridoErro());
    }

    @Override
    public Optional<Product> update(Long id, ProductRequest request) throws Exception {
        
        validate(request, false);

        // verificando se o registro existe no banco
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product temp = toProduct(request);
            temp.setId(product.get().getId());
            
            // se existe, então atualiza os dados e salva
            temp = productRepository.save(temp);
            if (temp != null) {
                messages.registroAlteradoSucesso();

                // se houve atualização no preço
                if (product.get().getPrice() != temp.getPrice()) {
                    messages.printMessage("Registrando histórico de preço: {} para o produto: {}", temp.getSku(), temp.getPrice());
                    historicPriceService.registryHistoric(temp.getSku(), temp.getPrice());
                }

                return Optional.of(temp);
            }
        }
        
        // se deu algum erro, informa
        throw new Exception(messages.registroAlteradoErro());
    }

    @Override
    public Boolean delete(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            messages.registroDeletadoSucesso(id);
            return true;
        }

        messages.nenhumRegistroEncontrado();
        return false;
    }

    /**
     * 
     * @param request
     * @return
     */
    private Product toProduct(ProductRequest request) {
        Product p = Product.builder()
        .name(request.getName())
        .sku(request.getSku())
        .price(new BigDecimal(request.getPrice()))
        .build();

        Optional<Brand> brand = brandService.findByBrand(request.getBrand());
        p.setBrand(brand.get());

        Optional<Category> category = categoryService.findByCategory(request.getCategory());
        p.setCategory(category.get());
        return p;
    }

    /**
     * Validar os dados de entrada antes de cadastrar um novo registro
     * @param request
     * @return
     */
    private void validate(ProductRequest request, Boolean insert) throws Exception {
        messages.validandoDados();

        if (insert) {
            if (productRepository.countBySku(request.getSku()) > 0) {
                throw new Exception(messages.registroJaCadastrado());
            }
        }

        if (!brandService.findByBrand(request.getBrand()).isPresent()) {
            throw new Exception(messages.printMessage("Marca: {} não cadastrada na base de dados", request.getBrand()));
        }

        if (!categoryService.findByCategory(request.getCategory()).isPresent()) {
            throw new Exception(messages.printMessage("Categoria: {} não cadastrada na base de dados", request.getCategory()));
        }
    }
}
