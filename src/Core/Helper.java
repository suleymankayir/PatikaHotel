package Core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void showMessage(String inputText) {
        String message;
        String title = switch (inputText) {
            case "fill" -> {
                message = "Lütfen tüm alanları doldurunuz";
                yield "Hata";
            }
            case "done" -> {
                message = "İşlem başarılı";
                yield "Bilgilendirme";
            }
            case "notFound" -> {
                message = "Kayıt bulunamadı";
                yield "Bulunamadı";
            }
            case "error" -> {
                message = "Hatalı işlem yaptınız";
                yield "Hata";
            }
            case "roomError" -> {
                message = "Oda kapasitesi aştı";
                yield "Hata";
            }
            default -> {
                message = inputText;
                yield "Mesaj";
            }
        };

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }


    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    public static boolean confirm(String str){
        optionPaneTR();
        String msg;
        if (str.equals("sure")){
            msg = "Bu işlemi yapmak istediğine emin misin ?";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin?",JOptionPane.YES_NO_CANCEL_OPTION) == 0;
    }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }

}
