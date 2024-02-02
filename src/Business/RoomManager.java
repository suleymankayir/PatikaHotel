package Business;

import Core.Helper;
import Dao.RoomDao;
import Entity.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findByAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room obj : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getRoom_type();
            rowObject[i++] = obj.getRoom_stock();
            rowObject[i++] = obj.getPrice_adult();
            rowObject[i++] = obj.getPrice_child();
            rowObject[i++] = obj.getRoom_m2();
            rowObject[i++] = obj.getBed_number();
            rowObject[i++] = obj.isRoom_tv();
            rowObject[i++] = obj.isRoom_bar();
            rowObject[i++] = obj.isRoom_console();
            rowObject[i++] = obj.isRoom_cashbox();
            rowObject[i++] = obj.isRoom_projection();
            roomList.add(rowObject);

        }
        return roomList;
    }

    public boolean save(Room room) {
        if (this.getById(room.getRoom_id()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.roomDao.save(room);
    }


    public ArrayList<Room> searchForRoom(String hotel_name, String hotel_address, String strt_date, String fnsh_date, String adult_num, String child_num) {
        String query = "SELECT * FROM room r " +
                "JOIN hotel h ON r.hotel_id = h.hotel_id " +
                "LEFT JOIN season s ON r.season_id = s.season_id";


        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();


        strt_date = LocalDate.parse(strt_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        fnsh_date = LocalDate.parse(fnsh_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        if (hotel_name != null) {
            where.add("h.hotel_name ILIKE '%" + hotel_name + "%'");
        }
        if (hotel_address != null) {
            where.add("h.hotel_address ILIKE '%" + hotel_address + "%'");
        }

        if (adult_num != null && !adult_num.isEmpty() && child_num != null && !child_num.isEmpty()) {
            try {
                int adultNum = Integer.parseInt(adult_num);
                int childNum = Integer.parseInt(child_num);
                int total_person = adultNum + childNum;
                where.add("r.room_bed_number >= '" + (total_person) + "'");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        where.add("(s.start_date <= '" + strt_date + "')");
        where.add("(s.finish_date >= '" + fnsh_date + "')");

        where.add("r.room_stock > 0");

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if (joinStr.length() > 0) {
            query += " ON " + joinStr;
        }

        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }


        return this.roomDao.selectByQuery(query);
    }


}
