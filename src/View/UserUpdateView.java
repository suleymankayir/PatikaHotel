package View;

import Business.UserManager;
import Core.ComboItem;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUpdateView extends Layout{
    private JPanel container;
    private JLabel lbl_userUpdate;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JButton btn_update_save;
    private JLabel lbl_role;
    private JComboBox<ComboItem> cmb_update_role;
    private User user;
    private UserManager userManager;

    public UserUpdateView(User user){
        this.add(container);
        this.guiInitialize(400,600);
        this.user = user;
        this.userManager = new UserManager();

        // Load all users into role comboBox
        for (User u : this.userManager.findAll()){
            this.cmb_update_role.addItem(new ComboItem(u.getId(), u.getRole()));
        }

        // Pre-fill fields with existing user data if updating an existing user
        if (this.user.getId() != 0){
            this.fld_username.setText(this.user.getUsername());
            this.fld_password.setText(this.user.getPassword());
            ComboItem defaultUser = new ComboItem(this.user.getId(),this.user.getRole());
            this.cmb_update_role.getModel().setSelectedItem(defaultUser);
        }
        // ActionListener for update/save button
        btn_update_save.addActionListener(e -> {
            // Check if required fields are filled
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_username,this.fld_password})){
                Helper.showMessage("fill");
            } else {
                boolean result = false;

                // Get selected role from comboBox
                ComboItem selectedItem = (ComboItem) cmb_update_role.getSelectedItem();
                // Update user object with new data
                this.user.setUsername(fld_username.getText());
                this.user.setPassword(fld_password.getText());
                this.user.setRole(selectedItem.getValue());

                // Save or update user based on ID
                if (this.user.getId() != 0){
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }
                // Show appropriate message based on the result
                if (result){
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
