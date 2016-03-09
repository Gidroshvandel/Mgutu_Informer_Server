package dao;

import java.util.List;

public interface GenericRepositoryInterface<T> {
    Boolean addObject(T emp);
    Boolean removeObject(T emp);
    Boolean editObject(T emp);
    T getObject(String columnName, String columnValue);
    List<T> getAllObjects(String columnName);
}
