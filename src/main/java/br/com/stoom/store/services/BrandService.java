package br.com.stoom.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.store.messages.Messages;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.request.BrandRequest;
import br.com.stoom.store.services.interfaces.IService;

@Service
public class BrandService implements IService<Brand, Long, BrandRequest> {

    private Messages messages;
    
    @Autowired
    private BrandRepository brandRepository;
    

    public BrandService() {
        this.messages = new Messages(BrandService.class, Brand.class.getSimpleName());
    }

    

    /**
     *
     * @param active
     * @return
     * @throws Exception
     */
    public List<Brand> findAll(Boolean active) throws Exception {
        
        if (active == null) {
            return findAll();
        }

        messages.buscandoDados();

        // buscando as entidades
        List<Brand> brands = brandRepository.findAllByActive(active);
        messages.quantidadeDados(brands.size());
        return brands;
    }

    @Override
    public List<Brand> findAll() throws Exception {
        
        messages.buscandoDados();

        // buscando as entidades
        List<Brand> brands = brandRepository.findAll();
        messages.quantidadeDados(brands.size());
        return brands;
    }

    @Override
    public Optional<Brand> findById(Long id) throws Exception {

        // validando id
        if (id == null) {
            throw new Exception(messages.informeIdValido());
        }

        messages.buscandoPorId(id);

        // buscando por id
        return brandRepository.findById(id);
    }

    @Override
    public Optional<Brand> create(BrandRequest entity) throws Exception {
        // validando request
        validate(entity);

        // salvando nova entidade
        Brand brand = brandRepository.save(toBrand(entity));
        if (brand != null) {
            messages.registroInseridoSucesso();
            return Optional.of(brand);
        }

        // se não salvou, retorna falha
        throw new Exception(messages.registroInseridoErro());
    }

    @Override
    public Optional<Brand> update(Long id, BrandRequest request) throws Exception {
        
        // verificando se o registro existe no banco
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            Brand temp = toBrand(request);
            temp.setId(brand.get().getId());
            
            // se existe, então atualiza os dados e salva
            temp = brandRepository.save(temp);
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
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
            messages.registroDeletadoSucesso(id);
            return true;
        }

        messages.nenhumRegistroEncontrado();
        return false;
    }


    /**
     * Find a entity by name
     * @param brand
     * @return
     */
    public Optional<Brand> findByBrand(String brand) {
        return brandRepository.findByBrand(brand);
    }

    
    /**
     * 
     * @param request
     * @return
     */
    private Brand toBrand(BrandRequest request) {
        return Brand.builder()
        .brand(request.getBrand())
        .build();
    }

    /**
     * Validar os dados de entrada antes de cadastrar um novo registro
     * @param request
     * @return
     */
    private void validate(BrandRequest request) throws Exception {
        messages.validandoDados();
        if (brandRepository.countByBrandName(request.getBrand()) > 0) {
            throw new Exception(messages.registroJaCadastrado());
        }
    }
    
}
