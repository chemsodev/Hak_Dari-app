package com.example.demo.alerts;

public interface AlertsInterface {
    //Error
    void showAlertPermissionError(String permissionName,String permissionType);
    void showAlertTextFieldEmptyError();
    void showAlertSelectionEmptyError(String selection);
    void showAlertFailedTo(String addOrDelete,String what);


    void showAlertSuccessfuly(String addedOrDeleted ,String what);

}
