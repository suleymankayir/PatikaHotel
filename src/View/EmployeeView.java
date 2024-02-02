package View;

import Business.*;
import Core.ComboItem;
import Core.Helper;
import Entity.Hotel;
import Entity.Reservation;
import Entity.Room;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JLabel emp_lbl_welcome;
    private JTabbedPane tabbedPane1;
    private JButton emp_logout_btn;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    public JTable tbl_hotel;
    private JButton btn_otel_add;
    private JTable tbl_type_list;
    private JTable tbl_season_list;
    public JFormattedTextField fld_search_check_in;
    public JFormattedTextField fld_search_check_out;
    private JButton btn_room_search;
    private JComboBox<ComboItem> cmb_hotel_address;
    private JComboBox<ComboItem> cmb_hotel_name;
    private JTable tbl_room;
    private JScrollPane scrl_room;
    private JButton btn_add_room;
    private JButton btn_cncl_search;
    public JTextField fld_num_adult;
    public JTextField fld_num_child;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTable tbl_reservation;
    private User user;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;

    // Column arrays for tables
    private Object[] col_hotel;
    private Object[] col_season;
    private Object[] col_pension;
    private Object[] col_room;
    private Object[] col_reservation;

    // DefaultTableModel instances for tables
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private JPopupMenu hotel_menu;
    private JPopupMenu room_menu;
    private JPopupMenu reservation_menu;

    public EmployeeView() {


    }

    public EmployeeView(User user) {

        // Initializing managers
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.user = user;
        this.add(container);
        this.guiInitialize(1200, 600);

        // If no user is logged in, dispose the view
        if (user == null) {
            dispose();
        }
        // Setting the welcome label text
        this.emp_lbl_welcome.setText("Patika Acente Sistemi");

        // Loading components and tables
        loadComponent();

        loadHotelTable(null);
        loadHotelComponent();

        loadSeasonTable(null);
        loadPensionTable(null);

        loadRoomTable(null);
        loadRoomComponent();

        loadReservationTable(null);
        loadReservationComponent();

    }

    // Method to load basic components
    private void loadComponent() {
        emp_logout_btn.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });
    }

    // Methods to load data into tables
    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        // Setting column name
        col_hotel = new Object[]{"ID", "Otel Adı", "Adres", "Email", "Telefon", "Yıldız", "Otopark", "WiFi", "Havuz", "Gym", "Konsiyerj", "Spa", "Oda Servisi"};
        // If reservationList is null, populate it with all reservations
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        }
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadReservationTable(ArrayList<Object[]> reservationList) {
        // Setting column name
        col_reservation = new Object[]{"ID", "Oda ID", "İsim", "T.C.", "Giriş Tarihi", "Çıkış Tarihi", "Misafir Sayısı", "Toplam Fiyat", "Email", "Telefon"};
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
        }
        createTable(this.tmdl_reservation, this.tbl_reservation, col_reservation, reservationList);
    }

    public void loadSeasonTable(ArrayList<Object[]> seasonList) {
        // Setting column name
        col_season = new Object[]{"Otel ID", "Başlangıç Tarihi", "Bitiş Tarihi"};
        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        }
        createTable(this.tmdl_season, this.tbl_season_list, col_season, seasonList);

    }

    public void loadPensionTable(ArrayList<Object[]> pensionList) {
        // Setting column name
        col_pension = new Object[]{"Otel ID", "Pansiyon Tipi"};
        if (pensionList == null) {
            pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        }
        createTable(this.tmdl_pension, this.tbl_type_list, col_pension, pensionList);
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        // Setting column name
        col_room = new Object[]{"Oda ID", "Otel ID", "Sezon ID", "Pansiyon ID", "Oda Tipi", "Stok", "Yetişkin Fiyat", "Çoçuk Fiyat", "Metrekare", "Yatak Sayısı", "TV", "Bar", "Konsol", "Kasa", "Projeksiyon"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }

    // Değerlendirme Formu - 8
    // Değerlendirme Formu - 10
    // Methods to load components for specific tables
    private void loadHotelComponent() {
        tableSelectedRow(this.tbl_hotel);
        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Ekle").addActionListener(e -> {
            // Değerlendirme Formu - 13
            // Opening the hotel addition view
            HotelAddView hotelAddView = new HotelAddView(new Hotel());
            hotelAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });
        // Değerlendirme Formu - 11
        this.hotel_menu.add("Sezon Ekle").addActionListener(e -> {
            // Adding a new season for the selected hotel
            int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);

            SeasonAddView seasonAddView = new SeasonAddView(this.hotelManager.getById(selectHotelId));
            seasonAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadSeasonTable(null);

                }
            });

        });
        // Değerlendirme Formu - 12
        this.hotel_menu.add("Pansiyon Ekle").addActionListener(e -> {
            // Adding a new pension for the selected hotel
            int selectedHotelId = this.getTableSelectedRow(tbl_hotel, 0);
            PensionAddView pensionAddView = new PensionAddView(this.hotelManager.getById(selectedHotelId));
            pensionAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadPensionTable(null);
                }
            });
        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu);


        btn_otel_add.addActionListener(e -> {
            HotelAddView hotelAddView = new HotelAddView(new Hotel());
            hotelAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });


    }

    private void loadRoomComponent() {
        tableSelectedRow(this.tbl_room);
        this.room_menu = new JPopupMenu();
        // Değerlendirme Formu - 18
        this.room_menu.add("Rezervasyon Ekle").addActionListener(e -> {

            // Adding a new reservation for the selected room
            int selectedRoomId = this.getTableSelectedRow(tbl_room, 0);
            String inDate = (this.fld_search_check_in.getText());
            String outDate = (this.fld_search_check_out.getText());
            int adultNum = Integer.parseInt(this.fld_num_adult.getText());
            int childNum = Integer.parseInt(this.fld_num_child.getText());
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_search_check_in, this.fld_search_check_out, this.fld_num_adult, this.fld_num_child})) {
                Helper.showMessage("fill");

            } else {
                ReservationAddView reservationAddView = new ReservationAddView(null, this.roomManager.getById(selectedRoomId), inDate, outDate, adultNum, childNum);
                reservationAddView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        // Değerlendirme Formu - 19
                        // Updating tables after reservation addition
                        reservationManager.decreaseRoomStock(selectedRoomId);
                        loadReservationTable(null);
                        loadRoomTable(null);
                    }
                });
            }


        });
        this.tbl_room.setComponentPopupMenu(room_menu);
        btn_room_search.addActionListener(e -> {
            // Searching for rooms based on given criteria
            String inDate = (this.fld_search_check_in.getText());
            String outDate = (this.fld_search_check_out.getText());
            int adultNum = Integer.parseInt(fld_num_adult.getText());
            int childNum = Integer.parseInt(fld_num_child.getText());
            if (adultNum + childNum <= 0) {
                Helper.showMessage("Lütfen geçerli şekilde tarih veya kişi sayısı giriniz");
                return;
            }

            ArrayList<Room> roomList = this.roomManager.searchForRoom(

                    fld_hotel_address.getText(),
                    fld_hotel_name.getText(),
                    fld_search_check_in.getText(),
                    fld_search_check_out.getText(),
                    fld_num_adult.getText(),
                    fld_num_child.getText()
            );

            ArrayList<Object[]> roomRow = this.roomManager.getForTable(this.col_room.length, roomList);
            loadRoomTable(roomRow);
        });
        btn_cncl_search.addActionListener(e -> {
            // Canceling the room search and resetting fields
            loadRoomTable(null);
            this.fld_hotel_name.setText(null);
            this.fld_hotel_address.setText(null);
            this.fld_search_check_in.setText("10/02/2023");
            this.fld_search_check_out.setText("15/02/2023");
            this.fld_num_child.setText(null);
            this.fld_num_adult.setText(null);
        });

        btn_add_room.addActionListener(e -> {
            // Opening the room addition view
            RoomAddView roomAddView = new RoomAddView(new Room());
            roomAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });
    }

    private void loadReservationComponent() {
        tableSelectedRow(tbl_reservation);
        this.reservation_menu = new JPopupMenu();
        this.reservation_menu.add("Güncelle").addActionListener(e -> {
            // Updating a reservation
            int selectedReservationId = this.getTableSelectedRow(tbl_reservation, 0);
            Reservation selectReservation = this.reservationManager.getById(selectedReservationId);
            int selectedRoomId = selectReservation.getRoom_id();
            Room selectedRoom = this.roomManager.getById(selectedRoomId);
            String inDate = (this.fld_search_check_in.getText());
            String outDate = (this.fld_search_check_out.getText());
            int adultNum = Integer.parseInt(this.fld_num_adult.getText());
            int childNum = Integer.parseInt(this.fld_num_child.getText());
            ReservationAddView reservationAddView = new ReservationAddView(selectReservation, selectedRoom, inDate, outDate, adultNum, childNum);
            reservationAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Updating tables after reservation update
                    loadReservationTable(null);
                    loadRoomTable(null);
                }
            });
        });
        // Değerlendirme Formu - 22
        this.reservation_menu.add("Sil").addActionListener(e -> {
            // Deleting a reservation
            if (Helper.confirm("sure")) {
                int selectedReservationId = this.getTableSelectedRow(tbl_reservation, 0);
                Reservation selectReservation = this.reservationManager.getById(selectedReservationId);
                int selectedRoomId = selectReservation.getRoom_id();

                if (this.reservationManager.delete(selectedReservationId)) {
                    // Deleting the reservation and updating tables
                    // Değerlendirme Formu - 24
                    Helper.showMessage("done");
                    // Değerlendirme Formu - 23
                    this.reservationManager.increaseRoomStock(selectedRoomId);
                    loadReservationTable(null);
                    loadRoomTable(null);
                } else {
                    // Değerlendirme Formu - 25
                    Helper.showMessage("error");
                }
            }

        });
        this.tbl_reservation.setComponentPopupMenu(reservation_menu);

    }

    // Method to create UI components with formatted text fields
    private void createUIComponents() throws ParseException {
        this.fld_search_check_in = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_search_check_out = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_search_check_in.setText("10/02/2023");
        this.fld_search_check_out.setText("15/02/2023");

    }
}
