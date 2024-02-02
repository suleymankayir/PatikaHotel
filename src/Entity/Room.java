package Entity;

import Core.ComboItem;

public class Room {

    private int room_id;
    private int hotel_id;
    private int season_id;
    private int pension_id;
    private Pension.Type pension_type;
    private Room.ROOMS room_type;

    private int room_stock;
    private Hotel hotel;
    private Season season;
    private Pension pension;




    public enum ROOMS{
        SINGLE,
        DOUBLE,
        JRSUITE,
        SUITE
    }

    private int price_adult;
    private int price_child;
    private int bed_number;
    private int room_m2;
    private boolean room_tv;
    private boolean room_bar;
    private boolean room_console;
    private boolean room_cashbox;
    private boolean room_projection;

    public Room() {
    }

    public Pension.Type getPension_type() {
        return pension_type;
    }

    public void setPension_type(Pension.Type pension_type) {
        this.pension_type = pension_type;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
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

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }



    public int getRoom_stock() {
        return room_stock;
    }

    public void setRoom_stock(int room_stock) {
        this.room_stock = room_stock;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public int getBed_number() {
        return bed_number;
    }

    public void setBed_number(int bed_number) {
        this.bed_number = bed_number;
    }

    public int getRoom_m2() {
        return room_m2;
    }

    public void setRoom_m2(int room_m2) {
        this.room_m2 = room_m2;
    }

    public boolean isRoom_tv() {
        return room_tv;
    }

    public void setRoom_tv(boolean room_tv) {
        this.room_tv = room_tv;
    }

    public boolean isRoom_bar() {
        return room_bar;
    }

    public void setRoom_bar(boolean room_bar) {
        this.room_bar = room_bar;
    }

    public boolean isRoom_console() {
        return room_console;
    }

    public void setRoom_console(boolean room_console) {
        this.room_console = room_console;
    }

    public boolean isRoom_cashbox() {
        return room_cashbox;
    }

    public void setRoom_cashbox(boolean room_cashbox) {
        this.room_cashbox = room_cashbox;
    }

    public boolean isRoom_projection() {
        return room_projection;
    }

    public void setRoom_projection(boolean room_projection) {
        this.room_projection = room_projection;
    }

    public int getPrice_adult() {
        return price_adult;
    }

    public void setPrice_adult(int price_adult) {
        this.price_adult = price_adult;
    }

    public int getPrice_child() {
        return price_child;
    }

    public void setPrice_child(int price_child) {
        this.price_child = price_child;
    }

    public ROOMS getRoom_type() {
        return room_type;
    }

    public void setRoom_type(ROOMS room_type) {
        this.room_type = room_type;
    }



    /*
    public int calcPrice(int roomId, int seasonId, int pensionId, int num_adult, int num_child) {
        String query = "SELECT room_price FROM public.room " +
                     "WHERE room_id = ? AND season_id = ? AND pension_id = ?";


             PreparedStatement ps = this.con.prepareStatement(query)) {

            ps.setInt(1, roomId);
            ps.setInt(2, seasonId);
            ps.setInt(3, pensionId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int adultPrice = rs.getInt("room_prc");
                    int childPrice = rs.getInt("room_prc")/2;


                    int totalPrice = (adultPrice * num_adult) + (childPrice * num_child);

                    return totalPrice;
                } else {
                    Helper.showMessage("error")
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
     */
}
