package rederecicle.model.base;

import static java.lang.Math.E;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseDAO<E extends BaseEntity> {
    
    public void create(Connection conn, E e) throws Exception;
    
    public E readByID(Connection conn, Long id) throws Exception;
    
    public List<E> readByCriteria(Connection conn, Map<Long,Object> criteria,Long limit, Long offset)throws Exception;
    
    public void update(Connection conn, E e)throws Exception;
    
    public void delete(Connection conn, Long id)throws Exception;

}
