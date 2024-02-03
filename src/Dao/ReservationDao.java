package Dao;

import Core.Db;
import Entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {

    private Connection con;
    private final RoomDao roomDao;

    public ReservationDao(){
        this.con = Db.getInstance();
        this.roomDao = new RoomDao();
    }
    public ArrayList<Reservation> findAll(){
        return this.selectByQuery("SELECT * FROM public.reservation ORDER BY reservation_id");
    }
    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> reservations = new ArrayList<>();
        try{
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                reservations.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservations;
    }

    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation " +
                "(" +
                "room_id, " +
                "guest_name, " +
                "guest_tcno, " +
                "check_in_date, " +
                "check_out_date, " +
                "guest_num, " +
                "total_price, " +
                "guest_email, " +
                "guest_phone, " +
                "adult_num, " +
                "child_num" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,reservation.getRoom_id());
            ps.setString(2,reservation.getGuest_name());
            ps.setString(3,reservation.getGuest_tcno());
            ps.setDate(4, Date.valueOf(reservation.getCheck_in_date().toString()));
            ps.setDate(5,Date.valueOf(reservation.getCheck_out_date().toString()));
            ps.setInt(6,reservation.getGuest_num());
            ps.setInt(7,reservation.getTotal_price());
            ps.setString(8,reservation.getGuest_email());
            ps.setString(9,reservation.getGuest_phone());
            ps.setInt(10,reservation.getAdult_number());
            ps.setInt(11,reservation.getChild_number());
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Reservation match(ResultSet rs) throws SQLException{
        Reservation obj = new Reservation();
        obj.setReservation_id(rs.getInt("reservation_id"));
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setGuest_name(rs.getString("guest_name"));
        obj.setGuest_tcno(rs.getString("guest_tcno"));
        obj.setCheck_in_date(LocalDate.parse(rs.getString("check_in_date")));
        obj.setCheck_out_date(LocalDate.parse(rs.getString("check_out_date")));
        obj.setGuest_num(rs.getInt("guest_num"));
        obj.setTotal_price(rs.getInt("total_price"));
        obj.setGuest_email(rs.getString("guest_email"));
        obj.setGuest_phone(rs.getString("guest_phone"));
        obj.setAdult_number(rs.getInt("adult_num"));
        obj.setChild_number(rs.getInt("child_num"));
        obj.setRoom(this.roomDao.getById(rs.getInt("room_id")));
        return obj;

    }

    public Reservation getById(int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
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
    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,id);
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }
    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservation SET " +
                "room_id = ?, " +
                "guest_name = ?, " +
                "guest_tcno = ?, " +
                "check_in_date = ?, " +
                "check_out_date = ?, " +
                "guest_num = ?, " +
                "total_price = ?, " +
                "guest_email = ?, " +
                "guest_phone = ?, " +
                "adult_num = ?, " +
                "child_num = ? " +
                "WHERE reservation_id = ? ";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,reservation.getRoom_id());
            ps.setString(2,reservation.getGuest_name());
            ps.setString(3,reservation.getGuest_tcno());
            ps.setDate(4,Date.valueOf(reservation.getCheck_in_date()));
            ps.setDate(5,Date.valueOf(reservation.getCheck_out_date()));
            ps.setInt(6,reservation.getGuest_num());
            ps.setInt(7,reservation.getTotal_price());
            ps.setString(8,reservation.getGuest_email());
            ps.setString(9,reservation.getGuest_phone());
            ps.setInt(10,reservation.getAdult_number());
            ps.setInt(11,reservation.getChild_number());
            ps.setInt(12,reservation.getReservation_id());
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }
    public void decreaseRoomStock(int id){
        String query = "UPDATE public.room SET room_stock = room_stock - 1 WHERE room_id = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void increaseRoomStock(int id){
        String query = "UPDATE public.room SET room_stock = room_stock + 1 WHERE room_id = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

