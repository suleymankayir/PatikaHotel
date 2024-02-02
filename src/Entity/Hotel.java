package Entity;

public class Hotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_address;
    private String hotel_email;
    private String hotel_phone;
    private String hotel_star;
    private boolean carpark;
    private boolean spa;
    private boolean wifi;
    private boolean pool;
    private boolean gym;
    private boolean room_srvc;
    private boolean concierge;

    public Hotel(){

    }

    public Hotel(String hotel_name, String hotel_address, String hotel_email, String hotel_phone, String hotel_star, boolean carpark, boolean spa, boolean wifi, boolean pool, boolean gym, boolean room_srvc, boolean concierge) {

        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.hotel_email = hotel_email;
        this.hotel_phone = hotel_phone;
        this.hotel_star = hotel_star;
        this.carpark = carpark;
        this.spa = spa;
        this.wifi = wifi;
        this.pool = pool;
        this.gym = gym;
        this.room_srvc = room_srvc;
        this.concierge = concierge;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotel_email() {
        return hotel_email;
    }

    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(String hotel_star) {
        this.hotel_star = hotel_star;
    }

    public boolean isCarpark() {
        return carpark;
    }

    public void setCarpark(boolean carpark) {
        this.carpark = carpark;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public boolean isRoom_srvc() {
        return room_srvc;
    }

    public void setRoom_srvc(boolean room_srvc) {
        this.room_srvc = room_srvc;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_address='" + hotel_address + '\'' +
                ", hotel_email='" + hotel_email + '\'' +
                ", hotel_phone='" + hotel_phone + '\'' +
                ", hotel_star='" + hotel_star + '\'' +
                '}';
    }
}
