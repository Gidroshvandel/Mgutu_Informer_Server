package dao;

import dao.Impl.GroupsDAOImpl;
import dao.Impl.ScheduleDAOImpl;
import dao.Impl.UsersDAOImpl;

public class Factory {
    private static UsersDAO usersDAO = null;
    private static ScheduleDAO scheduleDAO = null;
    private static Factory instance = null;
    private static GroupsDAO groupsDAO = null;

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

    public ScheduleDAO  getScheduleDAO(){
        if (scheduleDAO == null){
            scheduleDAO = new ScheduleDAOImpl();
        }
        return scheduleDAO;
    }

    public GroupsDAO  getGroupsDAO(){
        if (groupsDAO == null){
            groupsDAO = new GroupsDAOImpl();
        }
        return groupsDAO;
    }

}
