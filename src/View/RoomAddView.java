package View;

import Business.HotelManager;
import Business.PensionManager;
import Business.RoomManager;
import Business.SeasonManager;
import Core.ComboItem;
import Core.Helper;
import Entity.Hotel;
import Entity.Pension;
import Entity.Room;
import Entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomAddView extends Layout{
    private JPanel container;
    private JComboBox<ComboItem> cmb_addHotel_room;
    private JComboBox<ComboItem> cmb_addSeason_room;
    private JComboBox<ComboItem> cmb_addPension_room;
    private JComboBox<Room.ROOMS> cmb_addType_room;
    private JTextField fld_room_stock;
    private JTextField fld_room_price_adult;
    private JTextField fld_room_m2;
    private JTextField fld_room_bed_number;
    private JTextField fld_room_price_child;
    private JButton btn_save_room;
    private JRadioButton radio_btn_TV;
    private JRadioButton radio_btn_Bar;
    private JRadioButton radio_btn_Console;
    private JRadioButton radio_btn_Projectile;
    private JRadioButton radio_btn_Cashbox;
    private Room room;
    private Hotel hotel;
    private Pension pension;
    private Season season;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;


    public RoomAddView(Room room){
        this.add(container);
        this.guiInitialize(1000,800);
        this.room = room;

        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();

        // Load hotels into hotel comboBox
        loadFilterHotel();

        // ActionListener for hotel comboBox
        this.cmb_addHotel_room.addActionListener(e -> {
            ComboItem selectedHotelItem = (ComboItem) this.cmb_addHotel_room.getSelectedItem();
            int selectedHotelId = selectedHotelItem.getKey();
            // Load seasons for the selected hotel
            ArrayList<Season> seasons = this.seasonManager.getByListHotelId(selectedHotelId);
            updateSeasonBox(seasons);
            // Load pensions for the selected hotel
            ArrayList<Pension> pensions = this.pensionManager.getByListHotelId(selectedHotelId);
            updatePensionComboBox(pensions);
        });

        // Load seasons into season comboBox
        loadFilterSeason();

        // Load all pensions into pension comboBox
        for (Pension pension : this.pensionManager.findAll()){
            this.cmb_addPension_room.addItem(pension.getComboItem());
        }

        // Set model for room type comboBox
        this.cmb_addType_room.setModel(new DefaultComboBoxModel<>(Room.ROOMS.values()));

        // ActionListener for save room button
        btn_save_room.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_room_stock,this.fld_room_m2,this.fld_room_bed_number,this.fld_room_price_adult,this.fld_room_price_child})){
                Helper.showMessage("fill");
            }else {
                Room obj = new Room();
                // Get selected hotel ID
                ComboItem selectedHotelItem = (ComboItem) cmb_addHotel_room.getSelectedItem();
                obj.setHotel_id(selectedHotelItem.getKey());
                // Get selected season ID
                ComboItem selectedSeasonItem = (ComboItem) cmb_addSeason_room.getSelectedItem();
                obj.setSeason_id(selectedSeasonItem.getKey());
                // Get selected pension ID
                ComboItem selectedPensionItem = (ComboItem) cmb_addPension_room.getSelectedItem();
                obj.setPension_id(selectedPensionItem.getKey());
                // Set room details
                System.out.println(selectedHotelItem.getKey());
                System.out.println(selectedSeasonItem.getKey());
                System.out.println(selectedPensionItem.getKey());
                obj.setRoom_type((Room.ROOMS) cmb_addType_room.getSelectedItem());
                obj.setRoom_stock(Integer.parseInt(fld_room_stock.getText()));
                obj.setPrice_adult(Integer.parseInt(fld_room_price_adult.getText()));
                obj.setRoom_m2(Integer.parseInt(fld_room_m2.getText()));
                obj.setBed_number(Integer.parseInt(fld_room_bed_number.getText()));
                obj.setPrice_child(Integer.parseInt(fld_room_price_child.getText()));
                obj.setRoom_tv(radio_btn_TV.isSelected());
                obj.setRoom_bar(radio_btn_Bar.isSelected());
                obj.setRoom_console(radio_btn_Console.isSelected());
                obj.setRoom_cashbox(radio_btn_Cashbox.isSelected());
                obj.setRoom_projection(radio_btn_Projectile.isSelected());

                if (this.roomManager.save(obj)){
                    Helper.showMessage("done");
                    dispose();
                } else{
                    Helper.showMessage("error");
                }
            }
        });


    }

    // Method to load hotels into hotel comboBox
    public void loadFilterHotel(){
        this.cmb_addHotel_room.removeAllItems();
        for (Hotel obj : this.hotelManager.findAll()){
            this.cmb_addHotel_room.addItem(new ComboItem(obj.getHotel_id(),obj.getHotel_name()));
        }
        this.cmb_addHotel_room.setSelectedItem(null);
    }

    // Method to load seasons into season comboBox
    public void loadFilterSeason(){
        this.cmb_addSeason_room.removeAllItems();
        for (Season obj : this.seasonManager.findAll()){
            this.cmb_addSeason_room.addItem(new ComboItem(obj.getSeason_id(), obj.getStart_date()  + " - " + obj.getFinish_date()));
        }
        this.cmb_addSeason_room.setSelectedItem(null);
    }

    // Method to update pension combo box based on selected hotel
    private void updatePensionComboBox(ArrayList<Pension> pensions){
        cmb_addPension_room.removeAllItems();
        for (Pension pension: pensions){
            cmb_addPension_room.addItem(pension.getComboItem());
        }
    }

    // Method to update season combo box based on selected hotel
    private void updateSeasonBox(ArrayList<Season> seasons){
        cmb_addSeason_room.removeAllItems();
        for (Season season: seasons){
            cmb_addSeason_room.addItem(season.getComboItem());
        }
    }


}
