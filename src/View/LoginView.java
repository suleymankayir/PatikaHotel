package View;

import Business.UserManager;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {

    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_login_info;
    private JPanel w_bottom;
    private JTextField fld_login_username;
    private JPasswordField fld_login_password;
    private JButton btn_login;
    private JLabel lbl_login_username;
    private JLabel lbl_login_password;
    private final UserManager userManager;

    public LoginView(){
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(400,400);

        btn_login.addActionListener(e -> {
            // to check field empty or not
            JTextField[] fieldCheckList = {this.fld_login_username,this.fld_login_password};
            if (Helper.isFieldListEmpty(fieldCheckList)){
                Helper.showMessage("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_login_username.getText(),fld_login_password.getText());
                // Değerlendirme Formu - 9
                if (loginUser == null){
                    Helper.showMessage("notFound");
                } else {
                    if (loginUser.getRole().equals("admin")){
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    } else if (loginUser.getRole().equals("employee")){
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        dispose();
                    } else {
                        Helper.showMessage("notFound");
                    }
                }
            }


        });
    }
}
