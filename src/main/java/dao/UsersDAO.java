package dao;

import model.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDAO {
    String addUser(Users users) throws SQLException;   //добавить студента
    void updateUser(Users users) throws SQLException;//обновить студента
    Users getUserById(Long id) throws SQLException;	   //получить стедента по id
    String loginUsers(Users users);
    List getAllUsers() throws SQLException;			   //получить всех студентов
    void deleteUser(Users users) throws SQLException;//удалить студента
}
