package com.example.myapplication;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

// Requests the location of the user

class SMSActiveLocationRequest {
    final SMSLocationRequest request;
    final int notificationId;

    SMSActiveLocationRequest(SMSLocationRequest request, int notificationId) {
        this.request = request;
        this.notificationId = notificationId;
    }
}