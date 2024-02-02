package Dao;

import Core.Db;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;

    public UserDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user ORDER BY user_id";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public ArrayList<User> getByListUserId(int userId){
        return this.selectByQuery("SELECT * FROM public.user WHERE user_id = " + userId);
    }

    public ArrayList<User> selectByQuery(String query){
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public User getById(int id){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ? ";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public boolean save(User user){
        String query = "INSERT INTO public.user (user_name,user_password,user_role) VALUES (?,?,?) ";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getRole());
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(User user){
        String query = "UPDATE public.user SET user_name = ?, user_password = ?, user_role = ? WHERE user_id = ? ";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getRole());
            ps.setInt(4,user.getId());
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,id);
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }



    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(rs.getString("user_role"));

        return obj;
    }

}
