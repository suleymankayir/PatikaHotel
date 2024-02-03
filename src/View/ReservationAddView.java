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
        // If the reservation is null, create a new one; otherwise, use the provided reservation
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

        // If reservation data exists, populate Reservation Panel components with that data
        if (this.reservation.getReservation_id() != 0){
            this.fld_guest_name.setText(this.reservation.getGuest_name());
            this.fld_guest_email.setText(this.reservation.getGuest_email());
            this.fld_guest_phone.setText(this.reservation.getGuest_phone());
            this.fld_guest_tcno.setText(this.reservation.getGuest_tcno());

        }
        // Calculate and display the number of days between check-in and check-out dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate firstDate = LocalDate.parse(inDate, formatter);
        LocalDate lastDate = LocalDate.parse(outDate, formatter);
        int dateNum = (int) ChronoUnit.DAYS.between(firstDate, lastDate);

        this.fld_date_num.setText(String.valueOf(dateNum));

        // Calculate and display the total price for the reservation
        int adultPrice = room.getPrice_adult();
        int childPrice = room.getPrice_child();
        int totalPrice = ((adultPrice * adultNum * dateNum) + (childPrice * childNum * dateNum));
        this.fld_total_price.setText(String.valueOf(totalPrice));


        btn_res_save.addActionListener(e -> {
            // Check if required fields are filled
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_guest_name,this.fld_guest_tcno,this.fld_guest_email,this.fld_guest_phone})){
                Helper.showMessage("fill");
            } else {
                boolean result;

                this.reservation.setRoom_id(room.getRoom_id());
                this.reservation.setCheck_in_date(LocalDate.parse(inDate, formatter));
                this.reservation.setCheck_out_date(LocalDate.parse(outDate, formatter));
                this.reservation.setTotal_price(Integer.parseInt(this.fld_total_price.getText()));
                this.reservation.setGuest_num(adultNum+childNum);
                this.reservation.setGuest_name(this.fld_guest_name.getText());
                this.reservation.setGuest_tcno(this.fld_guest_tcno.getText());
                this.reservation.setGuest_email(this.fld_guest_email.getText());
                this.reservation.setGuest_phone(this.fld_guest_phone.getText());
                this.reservation.setAdult_number(Integer.parseInt(this.fld_adult_num.getText()));
                this.reservation.setChild_number(Integer.parseInt(this.fld_child_num.getText()));

                // Save or update the reservation based on whether it already exists
                if (this.reservation.getReservation_id() != 0){
                    result = this.reservationManager.update(this.reservation);
                } else {
                    result = this.reservationManager.save(this.reservation);
                }

                // Show appropriate message based on the result of the operation
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
