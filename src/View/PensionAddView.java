package View;

import Business.PensionManager;
import Core.ComboItem;
import Core.Helper;
import Entity.Hotel;
import Entity.Pension;
import Entity.Season;

import javax.swing.*;

public class PensionAddView extends Layout{
    private JPanel container;
    private JComboBox<Pension.Type> cmb_pension;
    private JButton btn_save_pension;
    private Pension pension;
    private Pension.Type type;
    private PensionManager pensionManager;
    private Hotel hotel;

    public PensionAddView(Hotel hotel){
        this.add(container);
        this.guiInitialize(400,400);
        this.hotel = hotel;
        this.pensionManager = new PensionManager();
        this.pension = new Pension();

        this.cmb_pension.setModel(new DefaultComboBoxModel<>(Pension.Type.values()));


        if (this.pension.getPension_id() != 0){
            Pension obj = new Pension();
            obj.setHotel_id(this.hotel.getHotel_id());
            obj.setPension_type((Pension.Type) cmb_pension.getSelectedItem());
        }
        btn_save_pension.addActionListener(e -> {
            this.pension.setPension_type((Pension.Type)cmb_pension.getSelectedItem());
            this.pension.setHotel_id(hotel.getHotel_id());

            if (this.pensionManager.save(this.pension)){
                Helper.showMessage("done");
                dispose();
            } else{
                Helper.showMessage("error");
            }
        });

    }



}
