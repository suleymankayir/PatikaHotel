package Business;

import Core.Helper;
import Dao.UserDao;
import Entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }


    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }

    public User getById(int id){
        return this.userDao.getById(id);
    }

    public boolean save(User user){
        if (user.getId() != 0){
            Helper.showMessage("error");
        }
        return this.userDao.save(user);
    }

    public boolean update(User user){
        if (this.getById(user.getId()) == null) {
            Helper.showMessage("Böyle bir kullanıcı bulunamadı");
            return false;
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id){
        if (this.getById(id) == null) {
            Helper.showMessage("Böyle bir kullanıcı bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }

    public ArrayList<User> getByListUserId(int userId) {
        return this.userDao.getByListUserId(userId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList){
        ArrayList<Object[]> userObjList = new ArrayList<>();
        for (User obj: userList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            userObjList.add(rowObject);
        }
        return userObjList;
    }

    public ArrayList<User> searchForTable(String userRole){
        String select = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();

        if (userRole != null){
            whereList.add("user_role =" + "'"+ userRole + "'");
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0){
            query += " WHERE " + whereStr;
        }
        System.out.println(query);
        return this.userDao.selectByQuery(query);
    }
}
