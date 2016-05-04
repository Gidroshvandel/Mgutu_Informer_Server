package dao.Impl;

import dao.Factory;
import dao.TeacherDAO;
import model.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO{
    @Override
    public boolean addTeacher(Teacher teacher) throws SQLException {
       return Factory.getInstance().getGenericRepositoryInterface().addObject(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {

    }

    @Override
    public Teacher getTeacherByName(String teacherName) throws SQLException {
        return (Teacher) Factory.getInstance().getGenericRepositoryInterface(Teacher.class).getObject("teacherName", teacherName);
    }

    @Override
    public List getAllTeachers() {
        return Factory.getInstance().getGenericRepositoryInterface(Teacher.class).getAllObjects();
    }

    @Override
    public Boolean deleteTeacher(Teacher teacher) throws SQLException {
        return Factory.getInstance().getGenericRepositoryInterface().removeObject(teacher);
    }
}
