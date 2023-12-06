package br.com.stoom.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.store.messages.Messages;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.request.CategoryRequest;
import br.com.stoom.store.services.interfaces.IService;

@Service
public class CategoryService implements IService<Category, Long, CategoryRequest> {

    private Messages messages;

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService() {
        this.messages = new Messages(CategoryService.class, Category.class.getSimpleName());
    }


    /**
     * 
     * @param active
     * @return
     * @throws Exception
     */
    public List<Category> findAll(Boolean active) throws Exception {

        if (active == null) {
            return findAll();
        }

        messages.buscandoDados();

        // buscando as entidades
        List<Category> catogories = categoryRepository.findAllByActive(active);
        messages.quantidadeDados(catogories.size());
        return catogories;
    }

    @Override
    public List<Category> findAll() throws Exception {

        messages.buscandoDados();

        // buscando as entidades
        List<Category> catogories = categoryRepository.findAll();
        messages.quantidadeDados(catogories.size());
        return catogories;
    }

    @Override
    public Optional<Category> findById(Long id) throws Exception {

        // validando id
        if (id == null) {
            throw new Exception(messages.informeIdValido());
        }

        messages.buscandoPorId(id);

        // buscando por id
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> create(CategoryRequest entity) throws Exception {
        
        // validando request
        validate(entity);

        // salvando nova entidade
        Category category = categoryRepository.save(toCategory(entity));
        if (category != null) {
            messages.registroInseridoSucesso();
            return Optional.of(category);
        }

        // se não salvou, retorna falha
        throw new Exception(messages.registroInseridoErro());
    }

    @Override
    public Optional<Category> update(Long id, CategoryRequest request) throws Exception {
        
        // verificando se o registro existe no banco
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category temp = toCategory(request);
            temp.setId(category.get().getId());
            
            // se existe, então atualiza os dados e salva
            temp = categoryRepository.save(temp);
            if (temp != null) {
                messages.registroAlteradoSucesso();
                return Optional.of(temp);
            }
        }
        
        // se deu algum erro, informa
        throw new Exception(messages.registroAlteradoErro());
    }

    @Override
    public Boolean delete(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            messages.registroDeletadoSucesso(id);
            return true;
        }
        
        messages.nenhumRegistroEncontrado();
        return false;
    }
    
    
    /**
     * 
     * @param category
     * @return
     */
    public Optional<Category> findByCategory(String category) {
        return categoryRepository.findByCategory(category);
    }


    /**
     * 
     * @param request
     * @return
     */
    private Category toCategory(CategoryRequest request) {
        return Category.builder()
        .category(request.getCategory())
        .build();
    }

    /**
     * Validar os dados de entrada antes de cadastrar um novo registro
     * @param request
     * @return
     */
    private void validate(CategoryRequest request) throws Exception {
        messages.validandoDados();
        if (categoryRepository.countByCategoryName(request.getCategory()) > 0) {
            throw new Exception(messages.registroJaCadastrado());
        }
    }
}
