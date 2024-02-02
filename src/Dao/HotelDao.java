package Dao;

import Core.Db;
import Core.Helper;
import Entity.Hotel;
import Entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    private final Connection con;

    public HotelDao(){
        this.con = Db.getInstance();
    }
    public ArrayList<Hotel> findAll(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM public.hotel ORDER BY hotel_id";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next()){
                hotelList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hotelList;
    }

    public ArrayList<Hotel> getByListHotelId(int hotelId) {
         return this.selectByQuery("SELECT * FROM public.hotel WHERE hotel_id = " + hotelId);
    }

    public ArrayList<Hotel> selectByQuery(String query){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hotelList;
    }

    public Hotel getById(int id){
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ? ";
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

    public boolean save(Hotel hotel){
        String query = "INSERT INTO public.hotel (hotel_name, hotel_address, hotel_email, hotel_phone,hotel_star,hotel_park,hotel_wifi,hotel_pool,hotel_gym,hotel_consierge,hotel_spa,hotel_room_service)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1,hotel.getHotel_name());
            ps.setString(2,hotel.getHotel_address());
            ps.setString(3,hotel.getHotel_email());
            ps.setString(4,hotel.getHotel_phone());
            ps.setString(5,hotel.getHotel_star());
            ps.setBoolean(6,hotel.isCarpark());
            ps.setBoolean(7,hotel.isWifi());
            ps.setBoolean(8,hotel.isPool());
            ps.setBoolean(9,hotel.isGym());
            ps.setBoolean(10,hotel.isConcierge());
            ps.setBoolean(11,hotel.isSpa());
            ps.setBoolean(12,hotel.isRoom_srvc());
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setHotel_name(rs.getString("hotel_name"));
        obj.setHotel_address(rs.getString("hotel_address"));
        obj.setHotel_email(rs.getString("hotel_email"));
        obj.setHotel_phone(rs.getString("hotel_phone"));
        obj.setHotel_star(rs.getString("hotel_star"));
        obj.setCarpark(rs.getBoolean("hotel_park"));
        obj.setWifi(rs.getBoolean("hotel_wifi"));
        obj.setPool(rs.getBoolean("hotel_pool"));
        obj.setGym(rs.getBoolean("hotel_gym"));
        obj.setConcierge(rs.getBoolean("hotel_consierge"));
        obj.setSpa(rs.getBoolean("hotel_spa"));
        obj.setRoom_srvc(rs.getBoolean("hotel_room_service"));

        return obj;
    }

}
