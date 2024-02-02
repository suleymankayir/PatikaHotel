package Dao;

import Core.ComboItem;
import Core.Db;
import Entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {

   private final Connection con;
   private final HotelDao hotelDao;

   public SeasonDao(){
       this.con = Db.getInstance();
       hotelDao = new HotelDao();
   }


   public ArrayList<Season> findAll(){
       return this.selectByQuery("SELECT * FROM public.season ORDER BY season_id");
   }

   public ArrayList<Season> getByListHotelId(int hotelId){
       return this.selectByQuery("SELECT * FROM public.season WHERE hotel_id = " + hotelId);
   }
    public ArrayList<Season> getByListHotelName(ComboItem hotelName){
        return this.selectByQuery("SELECT * FROM public.season WHERE hotel_name LIKE " + hotelName);
    }

   public boolean save(Season season){
       String query = "INSERT INTO public.season (hotel_id,start_date,finish_date) " +
               " VALUES (?,?,?) ";

       try{
           PreparedStatement ps = this.con.prepareStatement(query);
           ps.setInt(1,season.getHotel_id());
           ps.setDate(2, Date.valueOf(season.getStart_date()));
           ps.setDate(3,Date.valueOf(season.getFinish_date()));
           return ps.executeUpdate() != -1;
       }catch (SQLException e){
           e.printStackTrace();
       }
       return true;
   }

   public Season getById(int id){
       Season obj = null;
       String query = "SELECT * FROM public.season WHERE season_id = ?";
       try{
           PreparedStatement ps = this.con.prepareStatement(query);
           ps.setInt(1,id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               obj = this.match(rs);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return obj;
   }


   public ArrayList<Season> selectByQuery(String query){
       ArrayList<Season> seasonList = new ArrayList<>();
          try{
           ResultSet rs = this.con.createStatement().executeQuery(query);
           while (rs.next()){
               seasonList.add(this.match(rs));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }

       return seasonList;
   }

   public Season match(ResultSet rs) throws SQLException{
       Season obj = new Season();
       obj.setSeason_id(rs.getInt("season_id"));
       obj.setHotel_id(rs.getInt("hotel_id"));
       obj.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
       obj.setStart_date(LocalDate.parse(rs.getString("start_date")));
       obj.setFinish_date(LocalDate.parse(rs.getString("finish_date")));

       return obj;
   }

}
