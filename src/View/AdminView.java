package View;

import Business.UserManager;
import Core.ComboItem;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel adm_lbl_welcome;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JComboBox<ComboItem> adm_cmb_role;
    private JButton adm_search_btn;
    private JLabel adm_lbl_role;
    public JTable tbl_admin;
    private JButton adm_logout_btn;
    private JButton btn_cancel;
    private User user;
    private DefaultTableModel tmdl_admin = new DefaultTableModel();
    private UserManager userManager;
    private JPopupMenu userMenu;
    Object[] col_admin;

    public AdminView(User user) {
        // Initializing the user manager
        this.userManager = new UserManager();
        // Adding the container to the layout
        this.add(container);
        // Initializing GUI dimensions
        this.guiInitialize(1000, 500);
        // Setting the user
        this.user = user;

        // If no user is logged in, dispose the view
        if (this.user == null) {
            dispose();
        }

        // Setting the welcome label text
        this.adm_lbl_welcome.setText("Patika Acente Admin Paneli");

        // Loading components
        loadComponent();

        // Loading user table with null filter
        loadUserTable(null);
        // Loading user components
        loadUserComponent();
        // Loading user filter
        loadUserFilter();


    }

    private void loadComponent(){
        // Adding action listener for logout button
        adm_logout_btn.addActionListener(e -> {
            dispose();
            // Creating a new login view
            LoginView loginView = new LoginView();
        });
    }

    public void loadUserFilter() {
        // Removing all items from role combo box
        this.adm_cmb_role.removeAllItems();
        // Populating role combo box with unique user roles

        Set<String> addedRoles = new HashSet<>(); // Set to store added roles

        for (User obj : userManager.findAll()) {
            String role = obj.getRole();
            if (!addedRoles.contains(role)) { // Check if role is already added
                this.adm_cmb_role.addItem(new ComboItem(obj.getId(), role));
                addedRoles.add(role); // Add role to set to prevent duplicates
            }
        }
        // Setting selected item to null
        this.adm_cmb_role.setSelectedItem(null);
    }

    public void loadUserTable(ArrayList<Object[]> userList) {
        // Setting column names
        this.col_admin = new Object[]{"Kullanıcı ID", "Kullanıcı İsim", "Kullanıcı Şifre", "Kullanıcı Rol"};
        // If userList is null, populate it with all users
        if (userList == null) {
            userList = this.userManager.getForTable(this.col_admin.length, this.userManager.findAll());
        }
        // Creating the table
        createTable(this.tmdl_admin, this.tbl_admin, col_admin, userList);
    }

    // Değerlendirme Formu - 7
    public void loadUserComponent() {
        // Handling selection in the table
        tableSelectedRow(this.tbl_admin);
        // Creating a popup menu for user actions
        this.userMenu = new JPopupMenu();
        // Adding action listener for "Add" option in the popup menu
        this.userMenu.add("Ekle").addActionListener(e -> {
            UserUpdateView userUpdateView = new UserUpdateView(new User());
            userUpdateView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        // Adding action listener for "Update" option in the popup menu
        this.userMenu.add("Güncelle").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_admin, 0);
            UserUpdateView userUpdateView = new UserUpdateView(this.userManager.getById(selectedUserId));
            userUpdateView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });

        });
        // Adding action listener for "Delete" option in the popup menu
        this.userMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectedUserId = this.getTableSelectedRow(tbl_admin, 0);
                if (this.userManager.delete(selectedUserId)) {
                    Helper.showMessage("done");
                    loadUserTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_admin.setComponentPopupMenu(userMenu);

        // Adding action listener for search button
        adm_search_btn.addActionListener(e -> {
            ComboItem selectedUser = (ComboItem) this.adm_cmb_role.getSelectedItem();
            String userRole = null;
            if (selectedUser != null) {
                userRole = selectedUser.getValue();
            }
            // Searching for users based on selected role
            ArrayList<User> userListBySearch = this.userManager.searchForTable(userRole);
            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(this.col_admin.length, userListBySearch);
            // Loading user table with search results
            loadUserTable(userRowListBySearch);
        });

        // Adding action listener for cancel button
        this.btn_cancel.addActionListener(e -> {
            this.adm_cmb_role.setSelectedItem(null);
            loadUserTable(null);
        });
    }
}
