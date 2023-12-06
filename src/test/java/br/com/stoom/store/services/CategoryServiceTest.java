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

import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.request.CategoryRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;


    private static final String CATEGORY_1 = "LIMPEZA";
    private static final String CATEGORY_2 = "FRIOS";

    @Before
	public void setUp() throws Exception {
		BDDMockito.given(this.categoryRepository.save(Mockito.any(Category.class))).willReturn(new Category());
	}


    @Test
	public void testPersistirCategroy() throws Exception {
		CategoryRequest categoryRequest = new CategoryRequest();
		
        // persisitindo
		Optional<Category> branPersistido = this.categoryService.create(categoryRequest);

        // verificando se o retorno é presente
		assertNotNull(branPersistido);
        assertTrue(branPersistido.isPresent());
	}


    @Test
    public void testFindAll() throws Exception {
        
        // Simulando o comportamento do repositório
        Category b1 = Category.builder().category(CATEGORY_1).build();
        Category b2 = Category.builder().category(CATEGORY_2).build();

        List<Category> mockCategorys = Arrays.asList(b1, b2);
        Mockito.when(categoryRepository.findAll()).thenReturn(mockCategorys);

        // método do serviço
        List<Category> result = categoryService.findAll();

        // Verificando se o resultado é o esperado
        assertEquals(mockCategorys, result);
    }

    @Test
    public void testFindById() throws Exception {
        
        // Simulando o comportamento do repositório
        Long mockCategoryId = 1L;
        Category mockCategory = Category.builder().id(mockCategoryId).build();
        Mockito.when(categoryRepository.findById(mockCategoryId)).thenReturn(Optional.of(mockCategory));

        // método do serviço
        Optional<Category> result = categoryService.findById(mockCategoryId);

        // Verificando se o resultado é o esperado
        assertTrue(result.isPresent());
        assertEquals(mockCategory, result.get());
    }


    @Test
    public void testDelete() {
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(new Category()));

        // método do serviço
        Boolean result = categoryService.delete(categoryId);
        assertTrue(result);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
