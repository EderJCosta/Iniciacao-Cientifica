package rederecicle.model.base;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseService<E extends BaseEntity> {
    
    public void create(E e) throws Exception;
    
    public E readByID(Long id) throws Exception;
    
    public List<E> readByCriteria(Map<Long,Object> criteria,Long limit, Long offset)throws Exception;
    
    public void update(E e)throws Exception;
    
    public void delete( Long id)throws Exception;
    
    public Map<String,String> validateForCreate(Map<String, Object> fields)throws Exception;
    
    public Map<String, String> validateForUpdate(Map<String, Object> fields)throws Exception;
    
}
