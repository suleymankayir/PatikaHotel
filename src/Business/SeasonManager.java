package Business;

import Core.ComboItem;
import Core.Helper;
import Dao.SeasonDao;
import Entity.Season;
import View.EmployeeView;

import java.util.ArrayList;

public class SeasonManager {

    private SeasonDao seasonDao;
    public EmployeeView employeeView;

    public SeasonManager(){
        this.seasonDao = new SeasonDao();
    }

    public Season getById(int id){
        return this.seasonDao.getById(id);
    }

    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }

    public boolean save(Season season){
        if (season.getSeason_id() != 0){
            Helper.showMessage("error");
            return false;
        }
        return this.seasonDao.save(season);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons){
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season obj: seasons){
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getStart_date().toString();
            rowObject[i++] = obj.getFinish_date().toString();
            seasonList.add(rowObject);
        }
        return seasonList;
    }

    public ArrayList<Season> getByListHotelId(int hotelId){
        return this.seasonDao.getByListHotelId(hotelId);
    }

    public ArrayList<Season> getByListHotelName(ComboItem hotelId){
        return this.seasonDao.getByListHotelName(hotelId);
    }
}
