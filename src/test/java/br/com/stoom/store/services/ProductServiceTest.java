package br.com.stoom.store.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.repository.ProductRepository;
import br.com.stoom.store.request.ProductRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BrandService brandService;

    @Mock
    private CategoryService categoryService;



    private static final String NAME_1 = "Frango Congelado";
    private static final String NAME_2 = "Biscoito Negresco";

   
    @Test
    public void testCreate() throws Exception {
        // Mock do objeto de request
        ProductRequest mockRequest = new ProductRequest();
        mockRequest.setName("Test Product");
        mockRequest.setSku("TEST123");
        mockRequest.setPrice("50.00");
        mockRequest.setBrand("TestBrand");
        mockRequest.setCategory("TestCategory");

        // Mock do objeto de resposta
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName(mockRequest.getName());
        mockProduct.setSku(mockRequest.getSku());
        mockProduct.setPrice(new BigDecimal(mockRequest.getPrice()));

        // Simular comportamento dos serviços e repositórios
        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
        when(brandService.findByBrand(anyString())).thenReturn(Optional.of(new Brand()));
        when(categoryService.findByCategory(anyString())).thenReturn(Optional.of(new Category()));

        // Chamar o método do serviço
        Optional<Product> result = productService.create(mockRequest);

        // Verificar se o resultado é o esperado
        assertTrue(result.isPresent());
        assertEquals(mockProduct, result.get());
    }

    @Test
    public void testFindAll() throws Exception {
        
        // Simulando o comportamento do repositório
        Product b1 = Product.builder().name(NAME_1).build();
        Product b2 = Product.builder().name(NAME_2).build();

        List<Product> mockProducts = Arrays.asList(b1, b2);
        Mockito.when(productRepository.findAll()).thenReturn(mockProducts);

        // método do serviço
        List<Product> result = productService.findAll();

        // Verificando se o resultado é o esperado
        assertEquals(mockProducts, result);
    }

    @Test
    public void testFindById() throws Exception {
        
        // Simulando o comportamento do repositório
        Long mockProductId = 1L;
        Product mockProduct = Product.builder().id(mockProductId).build();
        Mockito.when(productRepository.findById(mockProductId)).thenReturn(Optional.of(mockProduct));

        // método do serviço
        Optional<Product> result = productService.findById(mockProductId);

        // Verificando se o resultado é o esperado
        assertTrue(result.isPresent());
        assertEquals(mockProduct, result.get());
    }

    @Test
    public void testDelete() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));

        // método do serviço
        Boolean result = productService.delete(productId);
        assertTrue(result);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testDeleteRegistroNaoEncontrado() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        
        // chama oo serviço
        Boolean result = productService.delete(productId);
        assertFalse(result);

        // Como não tem o registro no banco o método deleteById não pode ser chamado
        verify(productRepository, never()).deleteById(anyLong());
    }
}
