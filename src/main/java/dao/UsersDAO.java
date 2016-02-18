package dao;

import model.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDAO {
    public String addUser(Users users) throws SQLException;   //добавить студента
    public void updateUser(Users users) throws SQLException;//обновить студента
    public Users getUserById(Long id) throws SQLException;	   //получить стедента по id
    public String loginUsers(Users users);
    public List getAllUsers() throws SQLException;			   //получить всех студентов
    public void deleteUser(Users users) throws SQLException;//удалить студента
//    public List testFunc() throws SQLException;//удалить студента
}
