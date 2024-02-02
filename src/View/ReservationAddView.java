package View;

import Business.ReservationManager;
import Core.Helper;
import Entity.Reservation;
import Entity.Room;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationAddView extends Layout {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JRadioButton radio_btn_carpark;
    private JRadioButton radio_btn_consierge;
    private JRadioButton radio_btn_wifi;
    private JRadioButton radio_btn_spa;
    private JRadioButton radio_btn_pool;
    private JRadioButton radio_btn_roomsrvc;
    private JRadioButton radio_btn_gym;
    private JTextField fld_room_type;
    private JTextField fld_bed_number;
    private JTextField fld_room_m2;
    private JLabel lbl_pension_type;
    private JRadioButton radio_btn_TV;
    private JRadioButton radio_btn_bar;
    private JRadioButton radio_btn_console;
    private JRadioButton radio_btn_cashbox;
    private JRadioButton radio_btn_projectile;
    private JFormattedTextField fld_check_in_date;
    private JFormattedTextField fld_check_out_date;
    private JTextField fld_adult_num;
    private JTextField fld_child_num;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_date_num;
    private JTextField fld_total_price;
    private JTextField fld_guest_name;
    private JTextField fld_guest_tcno;
    private JTextField fld_guest_email;
    private JTextField fld_guest_phone;
    private JButton btn_res_save;
    private Room room;
    private ReservationManager reservationManager;
    private EmployeeView employeeView;
    private Reservation reservation;

    ReservationAddView(Reservation reservation,Room room, String inDate, String outDate, int adultNum, int childNum) {
        this.add(container);
        this.room = room;
        if (reservation == null){
            this.reservation = new Reservation();
        } else {
            this.reservation = reservation;
        }

        this.reservationManager = new ReservationManager();
        this.employeeView = new EmployeeView();
        guiInitialize(900, 900);

        // Hotel Panel
        this.fld_hotel_name.setText(room.getHotel().getHotel_name());
        this.radio_btn_carpark.setSelected(room.getHotel().isCarpark());
        this.radio_btn_consierge.setSelected(room.getHotel().isConcierge());
        this.radio_btn_wifi.setSelected(room.getHotel().isWifi());
        this.radio_btn_spa.setSelected(room.getHotel().isSpa());
        this.radio_btn_pool.setSelected(room.getHotel().isPool());
        this.radio_btn_roomsrvc.setSelected(room.getHotel().isRoom_srvc());
        this.radio_btn_gym.setSelected(room.getHotel().isGym());

        // Room Panel
        this.fld_room_type.setText(room.getRoom_type().toString());
        this.fld_bed_number.setText(String.valueOf(room.getBed_number()));
        this.fld_room_m2.setText(String.valueOf(room.getRoom_m2()));
        this.radio_btn_TV.setSelected(room.isRoom_tv());
        this.radio_btn_bar.setSelected(room.isRoom_bar());
        this.radio_btn_console.setSelected(room.isRoom_console());
        this.radio_btn_cashbox.setSelected(room.isRoom_cashbox());
        this.radio_btn_projectile.setSelected(room.isRoom_projection());
        this.lbl_pension_type.setText(room.getPension().getPension_type().toString());

        // Reservation Panel

        this.fld_check_in_date.setText(String.valueOf(inDate));
        this.fld_check_out_date.setText(String.valueOf(outDate));
        this.fld_adult_num.setText(String.valueOf(adultNum));
        this.fld_child_num.setText(String.valueOf(childNum));
        this.fld_adult_price.setText(String.valueOf(room.getPrice_adult()));
        this.fld_child_price.setText(String.valueOf(room.getPrice_child()));

        if (this.reservation.getReservation_id() != 0){
            this.fld_guest_name.setText(this.reservation.getGuest_name());
            this.fld_guest_email.setText(this.reservation.getGuest_email());
            this.fld_guest_phone.setText(this.reservation.getGuest_phone());
            this.fld_guest_tcno.setText(this.reservation.getGuest_tcno());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate firstDate = LocalDate.parse(inDate, formatter);
        LocalDate lastDate = LocalDate.parse(outDate, formatter);
        int dateNum = (int) ChronoUnit.DAYS.between(firstDate, lastDate);

        this.fld_date_num.setText(String.valueOf(dateNum));

        int adultPrice = room.getPrice_adult();
        int childPrice = room.getPrice_child();
        int totalPrice = ((adultPrice * adultNum * dateNum) + (childPrice * childNum * dateNum));
        this.fld_total_price.setText(String.valueOf(totalPrice));


        btn_res_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_guest_name,this.fld_guest_tcno,this.fld_guest_email,this.fld_guest_phone})){
                Helper.showMessage("fill");
            } else {
                boolean result;
                Reservation res = new Reservation();
                res.setRoom_id(room.getRoom_id());
                res.setCheck_in_date(LocalDate.parse(inDate, formatter));
                res.setCheck_out_date(LocalDate.parse(outDate, formatter));
                res.setTotal_price(Integer.parseInt(this.fld_total_price.getText()));
                res.setGuest_num(adultNum+childNum);
                res.setGuest_name(this.fld_guest_name.getText());
                res.setGuest_tcno(this.fld_guest_tcno.getText());
                res.setGuest_email(this.fld_guest_email.getText());
                res.setGuest_phone(this.fld_guest_phone.getText());

                if (res.getReservation_id() != 0){
                    result = this.reservationManager.update(res);
                } else {
                    result = this.reservationManager.save(res);
                }
                if (result){
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }

        });
    }


}
