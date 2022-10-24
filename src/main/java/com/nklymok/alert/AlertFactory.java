package com.nklymok.alert;

import com.nklymok.constant.Constants;
import javafx.scene.control.Alert;

public class AlertFactory {
    private static Alert EXPORT_FAILED_ALERT = null;
    private static Alert EXPORT_SUCCESS_ALERT = null;
    private static Alert IMPORT_FAILED_ALERT = null;
    private static Alert IMPORT_SUCCESS_ALERT = null;
    private static Alert ABOUT_ALERT = null;


    public static Alert getExportFailedAlert() {
        if (EXPORT_FAILED_ALERT == null) {
            EXPORT_FAILED_ALERT = new Alert(Alert.AlertType.ERROR, Constants.Error.EXPORT_001);
        }
        return EXPORT_FAILED_ALERT;
    }

    public static Alert getExportSuccessAlert() {
        if (EXPORT_SUCCESS_ALERT == null) {
            EXPORT_SUCCESS_ALERT = new Alert(Alert.AlertType.INFORMATION, Constants.Info.EXPORT_001);
        }
        return EXPORT_SUCCESS_ALERT;
    }

    public static Alert getImportFailedAlert() {
        if (IMPORT_FAILED_ALERT == null) {
            IMPORT_FAILED_ALERT = new Alert(Alert.AlertType.ERROR, Constants.Error.IMPORT_001);
        }
        return IMPORT_FAILED_ALERT;
    }

    public static Alert getImportSuccessAlert() {
        if (IMPORT_SUCCESS_ALERT == null) {
            IMPORT_SUCCESS_ALERT = new Alert(Alert.AlertType.INFORMATION, Constants.Info.IMPORT_001);
        }
        return IMPORT_SUCCESS_ALERT;
    }


    public static Alert getAboutAlert() {
        if (ABOUT_ALERT == null) {
            ABOUT_ALERT = new Alert(Alert.AlertType.INFORMATION, Constants.Info.ABOUT);
        }
        return ABOUT_ALERT;
    }

    public static Alert getCustomErrorAlert(String message) {
        return new Alert(Alert.AlertType.ERROR, message);
    }
}
