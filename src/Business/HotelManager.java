package Business;

import Core.Helper;
import Dao.HotelDao;
import Entity.Hotel;

import java.util.ArrayList;

public class HotelManager {

    private final HotelDao hotelDao;

    public HotelManager(){
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }

    public Hotel getById(int id){
        return this.hotelDao.getById(id);
    }

    public boolean save(Hotel hotel){
        if (hotel.getHotel_id() != 0){
            Helper.showMessage("error");
        }
        return this.hotelDao.save(hotel);
    }

    public ArrayList<Hotel> getByListHotelId(int hotelId){
        return this.hotelDao.getByListHotelId(hotelId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotelList){
        ArrayList<Object[]> hotelObjList = new ArrayList<>();
        for (Hotel obj : hotelList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getHotel_name();
            rowObject[i++] = obj.getHotel_address();
            rowObject[i++] = obj.getHotel_email();
            rowObject[i++] = obj.getHotel_phone();
            rowObject[i++] = obj.getHotel_star();
            rowObject[i++] = obj.isCarpark();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isGym();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isRoom_srvc();

            hotelObjList.add(rowObject);

        }
        return hotelObjList;
    }

}
