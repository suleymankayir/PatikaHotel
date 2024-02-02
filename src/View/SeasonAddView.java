package View;

import Business.SeasonManager;
import Core.Helper;
import Entity.Hotel;
import Entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonAddView extends Layout{

    private JButton btn_save_season;
    private JPanel container;
    private JFormattedTextField fld_season_start;
    private JFormattedTextField fld_season_end;
    private Hotel hotel;

    private Season season;
    private SeasonManager seasonManager;
    private AdminView adminView;


    public SeasonAddView(Hotel hotel){
        this.add(container);
        this.guiInitialize(500,500);

        this.hotel = hotel;
        this.seasonManager = new SeasonManager();


        // ActionListener for save season button
        btn_save_season.addActionListener(e -> {
            // Check if required fields are filled
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_season_start,this.fld_season_end})){
                Helper.showMessage("fill");
            }else {
                // Create a new Season object with the entered data
                Season obj = new Season();
                obj.setHotel_id(this.hotel.getHotel_id());
                obj.setStart_date((LocalDate.parse(this.fld_season_start.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                obj.setFinish_date((LocalDate.parse(this.fld_season_end.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

                // Save the season using the SeasonManager
                if (this.seasonManager.save(obj)){
                    Helper.showMessage("done");
                    dispose();
                }else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    // Method to create custom Swing components
    private void createUIComponents() throws ParseException {
        this.fld_season_start = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_season_start.setText("10/10/2023");
        this.fld_season_end = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_season_end.setText("16/10/2023");
    }
}
