package dao;

import dao.Impl.*;
import model.Teacher;

public class Factory {
    private static UsersDAO usersDAO = null;
    private static TeacherDAO teacherDAO = null;
    private static GroupsDAO groupsDAO = null;
    private static Factory instance = null;
    private static GenericRepositoryInterface genericRepositoryInterface = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public UsersDAO  getUsersDAO(){
        if (usersDAO == null){
            usersDAO = new UsersDAOImpl();
        }
        return usersDAO;
    }

    public GenericRepositoryInterface  getGenericRepositoryInterface(){
        if (genericRepositoryInterface == null){
            genericRepositoryInterface = new GenericRepositoryImplementation<>();
        }
        return genericRepositoryInterface;
    }
    public GenericRepositoryInterface  getGenericRepositoryInterface(Class clazz){
            genericRepositoryInterface = new GenericRepositoryImplementation<>(clazz);
        return genericRepositoryInterface;
    }

}
