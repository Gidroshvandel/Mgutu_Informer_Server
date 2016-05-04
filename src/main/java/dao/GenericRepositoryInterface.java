package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GenericRepositoryInterface<T> {
    Boolean addObject(T emp);
    Boolean removeObject(T emp);
    Boolean editObject(T emp);
    T getObject(Map<String,Object> map);
    T getObject(String columnName, String columnValue);
    List<T> getAllObjects();
}
