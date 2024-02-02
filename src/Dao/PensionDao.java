package Dao;

import Core.Db;
import Entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {

    private final Connection con;
    private final HotelDao hotelDao;

    public PensionDao(){
        this.con = Db.getInstance();
        hotelDao = new HotelDao();
    }

    public Pension getById(int id){
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ?";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Pension> findAll(){
        return this.selectByQuery("SELECT * FROM public.pension ORDER BY pension_id") ;
    }

    public ArrayList<Pension> getByListHotelId(int hotelId){
        return this.selectByQuery("SELECT * FROM public.pension WHERE hotel_id = " + hotelId);
    }

    public boolean save(Pension pension){
        String query = "INSERT INTO public.pension (hotel_id,pension_type)" +
                " VALUES (?,?)";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,pension.getHotel_id());
            ps.setString(2,pension.getPension_type().toString());
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Pension> selectByQuery(String query){
        ArrayList<Pension> pensionList = new ArrayList<>();
        try{
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next()){
                pensionList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pensionList;
    }

    public Pension match(ResultSet rs) throws SQLException{
        Pension obj = new Pension();
        obj.setPension_id(rs.getInt("pension_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        obj.setPension_type(Pension.Type.valueOf(rs.getString("pension_type")));
        return obj;
    }
}
