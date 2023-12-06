package br.com.stoom.store.services.interfaces;

import java.util.List;
import java.util.Optional;

public interface IService <T, ID, R> {

    
    /**
     * find all entities
     * @return
     * @throws Exception
     */
    List<T> findAll() throws Exception;

    /**
     * find entity by id
     * @param id
     * @return
     * @throws Exception
     */
    Optional<T> findById(ID id) throws Exception;

    /**
     * create a entity into database and return entity including ID created
     * @param request
     * @return
     * @throws Exception
     */
    Optional<T> create (R request) throws Exception;

    /**
     * update atributes from entity and persist in database
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    Optional<T> update (ID id, R request) throws Exception;

    /**
     * Delete entity from database
     * @param id
     * @return
     * @throws Exception
     */
    Boolean delete(ID id) throws Exception;
}
