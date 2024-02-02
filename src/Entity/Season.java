package Entity;


import Core.ComboItem;

import java.time.LocalDate;

public class Season {

    private int season_id;
    private Hotel hotel;
    private int hotel_id;
    private LocalDate start_date;
    private LocalDate finish_date;

    public Season(){

    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    @Override
    public String toString() {
        return "Season{" +
                "season_id=" + season_id +

                ", hotel_id=" + hotel_id +
                ", start_date=" + start_date +
                ", finish_date=" + finish_date +
                '}';
    }
    public ComboItem getComboItem() {
        return new ComboItem(this.getSeason_id(),this.getStart_date() + " - " + this.getFinish_date());
    }
}
