package Business;

import Core.Helper;
import Dao.PensionDao;
import Entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    private final PensionDao pensionDao;

    public PensionManager(){
        this.pensionDao = new PensionDao();
    }

    public Pension getById(int id){
        return this.pensionDao.getById(id);
    }

    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }

    public boolean save(Pension pension){
        if (this.getById(pension.getPension_id()) != null){
            Helper.showMessage("error");
            return false;
        }
        return this.pensionDao.save(pension);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensionList){
        ArrayList<Object[]> pensionObjList = new ArrayList<>();
        for (Pension obj: pensionList){
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getHotel().getHotel_id();
            rowObject[i++] = obj.getPension_type();
            pensionObjList.add(rowObject);

        }
        return pensionObjList;
    }

    public ArrayList<Pension> getByListHotelId(int hotelId){
        return this.pensionDao.getByListHotelId(hotelId);
    }
}
