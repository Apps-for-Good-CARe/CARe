package com.example.myapplication;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

public enum SMSStatus {

    RUNNING(1), SUCCESS(2), NO_LOCATION(3), ABORTED(4), NO_GPS(5), NETWORK(6), NETWORK_NO_GPS(
            7);

    private final int id;

    private SMSStatus(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    static SMSStatus getForId(int id) {
        for (SMSStatus s : values()) {
            if (s.id == id) {
                return s;
            }
        }
        throw new IllegalArgumentException("no status for id " + id);
    }
}
