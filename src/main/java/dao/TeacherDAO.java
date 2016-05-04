package dao;

import model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDAO {
    boolean addTeacher(Teacher teacher) throws SQLException;   //добавить
    void updateTeacher(Teacher teacher) throws SQLException;//обновить студента
    Teacher getTeacherByName(String teacherName) throws SQLException;	   //получить стедента по id
    List getAllTeachers() throws SQLException;			   //получить всех студентов
    Boolean deleteTeacher(Teacher teacher) throws SQLException;//удалить студента
}
