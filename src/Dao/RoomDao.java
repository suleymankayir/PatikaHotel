package Dao;

import Core.Db;
import Entity.Hotel;
import Entity.Pension;
import Entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {

    private Connection con;
    private HotelDao hotelDao;
    private SeasonDao seasonDao;
    private PensionDao pensionDao;

    public RoomDao() {
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
        this.seasonDao = new SeasonDao();
        this.pensionDao = new PensionDao();
    }

    public Room getById(int id){
        Room obj = null;
        String query = " SELECT * FROM public.room WHERE room_id = ? ";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
            return obj;
    }

    public ArrayList<Room> findByAll(){
        return this.selectByQuery("SELECT * FROM public.room ORDER BY room_id");
    }

    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> rooms = new ArrayList<>();
        try{
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next()){
                rooms.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rooms;
    }

    public boolean save(Room room){
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id," +
                "season_id," +
                "pension_id," +
                "room_type," +
                "room_stock," +
                "price_adult," +
                "price_child," +
                "room_m2," +
                "room_bed_number," +
                "room_tv," +
                "room_bar," +
                "room_console," +
                "room_cashbox," +
                "room_projection" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,room.getHotel_id());
            ps.setInt(2,room.getSeason_id());
            ps.setInt(3,room.getPension_id());
            ps.setString(4,room.getRoom_type().toString());
            ps.setInt(5,room.getRoom_stock());
            ps.setInt(6,room.getPrice_adult());
            ps.setInt(7,room.getPrice_child());
            ps.setInt(8,room.getRoom_m2());
            ps.setInt(9,room.getBed_number());
            ps.setBoolean(10,room.isRoom_tv());
            ps.setBoolean(11, room.isRoom_bar());
            ps.setBoolean(12, room.isRoom_console());
            ps.setBoolean(13,room.isRoom_cashbox());
            ps.setBoolean(14,room.isRoom_projection());
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Room match(ResultSet rs) throws SQLException{
        Room room = new Room();
        room.setRoom_id(rs.getInt("room_id"));
        room.setHotel_id(rs.getInt("hotel_id"));
        room.setSeason_id(rs.getInt("season_id"));
        room.setPension_id(rs.getInt("pension_id"));
        room.setRoom_type(Room.ROOMS.valueOf(rs.getString("room_type")));
        room.setRoom_stock(rs.getInt("room_stock"));
        room.setPrice_adult(rs.getInt("price_adult"));
        room.setPrice_child(rs.getInt("price_child"));
        room.setRoom_m2(rs.getInt("room_m2"));
        room.setBed_number(rs.getInt("room_bed_number"));
        room.setRoom_tv(rs.getBoolean("room_tv"));
        room.setRoom_bar(rs.getBoolean("room_bar"));
        room.setRoom_console(rs.getBoolean("room_console"));
        room.setRoom_cashbox(rs.getBoolean("room_cashbox"));
        room.setRoom_projection(rs.getBoolean("room_projection"));
        room.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        room.setSeason(this.seasonDao.getById(rs.getInt("season_id")));
        room.setPension(this.pensionDao.getById(rs.getInt("pension_id")));
        return room;
    }


}
