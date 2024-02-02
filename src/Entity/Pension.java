package Entity;

import Core.ComboItem;

public class Pension {
    private int pension_id;
    private int hotel_id;

    private Hotel hotel;
    private Pension.Type pension_type;

    public enum Type {
        ULTRA_ALL_INCLUSIVE,
        ALL_INCLUSIVE,
        ROOM_BREAKFAST,
        FULL_PENSION,
        HALF_PENSION,
        ONLY_BED,
        NO_ALCOHOL_FULL;

    }

    public Pension(){

    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Type getPension_type() {
        return pension_type;
    }

    public void setPension_type(Type pension_type) {
        this.pension_type = pension_type;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getPension_id(),this.getPension_type().toString());
    }
}
