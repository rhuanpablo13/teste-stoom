package br.com.stoom.store.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.request.BrandRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private BrandRepository brandRepository;

    private static final String SKU_1 = "SKU_1";
    private static final String SKU_2 = "SKU_2";


    @Before
	public void setUp() throws Exception {
		BDDMockito.given(this.brandRepository.save(Mockito.any(Brand.class))).willReturn(new Brand());
	}


    @Test
	public void testPersistirBrand() throws Exception {
		BrandRequest brandRequest = new BrandRequest();
		
        // persisitindo
		Optional<Brand> branPersistido = this.brandService.create(brandRequest);

        // verificando se o retorno é presente
		assertNotNull(branPersistido);
        assertTrue(branPersistido.isPresent());
	}


    @Test
    public void testFindAll() throws Exception {
        
        // Simulando o comportamento do repositório
        Brand b1 = Brand.builder().brand(SKU_1).build();
        Brand b2 = Brand.builder().brand(SKU_2).build();

        List<Brand> mockBrands = Arrays.asList(b1, b2);
        Mockito.when(brandRepository.findAll()).thenReturn(mockBrands);

        // método do serviço
        List<Brand> result = brandService.findAll();

        // Verificando se o resultado é o esperado
        assertEquals(mockBrands, result);
    }

    @Test
    public void testFindById() throws Exception {
        
        // Simulando o comportamento do repositório
        Long mockBrandId = 1L;
        Brand mockBrand = Brand.builder().id(mockBrandId).build();
        Mockito.when(brandRepository.findById(mockBrandId)).thenReturn(Optional.of(mockBrand));

        // método do serviço
        Optional<Brand> result = brandService.findById(mockBrandId);

        // Verificando se o resultado é o esperado
        assertTrue(result.isPresent());
        assertEquals(mockBrand, result.get());
    }


    @Test
    public void testDelete() {
        Long brandId = 1L;
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(new Brand()));

        // método do serviço
        Boolean result = brandService.delete(brandId);
        assertTrue(result);
        verify(brandRepository, times(1)).deleteById(brandId);
    }
}
