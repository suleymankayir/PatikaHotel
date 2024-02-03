package Business;

import Core.Helper;
import Dao.ReservationDao;
import Entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    private ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public boolean save(Reservation reservation) {
        if (this.getById(reservation.getReservation_id()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getReservation_id()) == null) {
            Helper.showMessage(reservation.getReservation_id() + "ID kayıtlı oda bulunamadı");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMessage(id + " ID kayıtlı oda bulunamadı");
            return false;
        }
        return this.reservationDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> resList = new ArrayList<>();
        for (Reservation obj : reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getReservation_id();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getGuest_name();
            rowObject[i++] = obj.getGuest_tcno();
            rowObject[i++] = obj.getCheck_in_date();
            rowObject[i++] = obj.getCheck_out_date();
            rowObject[i++] = obj.getGuest_num();
            rowObject[i++] = obj.getTotal_price();
            rowObject[i++] = obj.getGuest_email();
            rowObject[i++] = obj.getGuest_phone();

            resList.add(rowObject);

        }
        return resList;
    }
    public void decreaseRoomStock(int id){
        this.reservationDao.decreaseRoomStock(id);
    }

    public void increaseRoomStock(int id){
        this.reservationDao.increaseRoomStock(id);
    }
}
