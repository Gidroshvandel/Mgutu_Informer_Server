package dao;

import model.Groups;

import java.sql.SQLException;
import java.util.List;

public interface GroupsDAO {
    String addGroup(Groups groups) throws SQLException;   //добавить студента
    void updateGroup(Groups groups) throws SQLException;//обновить студента
    Groups getGroupByName(String groupsName) throws SQLException;	   //получить стедента по id
    List getAllGroups() throws SQLException;			   //получить всех студентов
    String deleteGroup(Groups groups) throws SQLException;//удалить студента
}
