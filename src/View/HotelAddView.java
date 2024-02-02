package View;

import Business.HotelManager;
import Core.Helper;
import Entity.Hotel;

import javax.swing.*;

public class HotelAddView extends Layout {
    private JPanel container;
    private JButton btn_otel_add;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JRadioButton radio_btn_carpark;
    private JRadioButton radio_btn_spa;
    private JRadioButton radio_btn_wifi;
    private JRadioButton radio_btn_pool;
    private JRadioButton radio_btn_gym;
    private JRadioButton radio_btn_conc;
    private JRadioButton radio_btn_room_srvc;
    private JTextField fld_hotel_star;
    private Hotel hotel;
    private HotelManager hotelManager;
    public HotelAddView(Hotel hotel){
        this.add(container);
        this.guiInitialize(500,700);
        this.hotel = hotel;
        this.hotelManager = new HotelManager();

        btn_otel_add.addActionListener(e -> {
            // Check if required fields are filled
            if(Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_name,this.fld_hotel_address,this.fld_hotel_email,this.fld_hotel_phone,this.fld_hotel_star})){
                Helper.showMessage("fill");
            } else {
                boolean result;
                // Create a Hotel object with the entered data
                final Hotel obj = getHotel();
                // Save the hotel using the HotelManager
                result = hotelManager.save(obj);

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
    // Method to create a Hotel object with the entered data
    private Hotel getHotel() {
        String name = fld_hotel_name.getText();
        String address = fld_hotel_address.getText();
        String email = fld_hotel_email.getText();
        String phone = fld_hotel_phone.getText();
        String star = fld_hotel_star.getText();
        boolean carPark = radio_btn_carpark.isSelected();
        boolean wifi = radio_btn_wifi.isSelected();
        boolean pool = radio_btn_pool.isSelected();
        boolean gym = radio_btn_gym.isSelected();
        boolean consierge = radio_btn_conc.isSelected();
        boolean spa = radio_btn_spa.isSelected();
        boolean roomService = radio_btn_room_srvc.isSelected();
        Hotel obj = new Hotel(name,address,email,phone,star,carPark,wifi,pool,gym,consierge,spa,roomService);
        return obj;
    }


}
