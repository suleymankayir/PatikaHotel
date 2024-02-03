package Entity;

import java.time.LocalDate;
import java.util.Date;

public class Reservation {

    private int reservation_id;
    private int room_id;
    private String guest_name;

    private String guest_tcno;
    private LocalDate check_in_date;
    private LocalDate check_out_date;
    private int guest_num;
    private int total_price;
    private String guest_email;
    private String guest_phone;
    private Room room;
    private int child_number;
    private int adult_number;

    public Reservation(){

    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }



    public String getGuest_tcno() {
        return guest_tcno;
    }

    public void setGuest_tcno(String guest_tcno) {
        this.guest_tcno = guest_tcno;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public int getGuest_num() {
        return guest_num;
    }

    public void setGuest_num(int guest_num) {
        this.guest_num = guest_num;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getChild_number() {
        return child_number;
    }

    public void setChild_number(int child_number) {
        this.child_number = child_number;
    }

    public int getAdult_number() {
        return adult_number;
    }

    public void setAdult_number(int adult_number) {
        this.adult_number = adult_number;
    }
}
