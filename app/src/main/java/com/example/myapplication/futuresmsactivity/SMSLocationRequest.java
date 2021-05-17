package com.example.myapplication.futuresmsactivity;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import java.util.Date;

import android.content.Context;
import android.text.format.DateFormat;

public class SMSLocationRequest {

    private final long id;
    private final String requester;
    private final long time;
    private SMSSimpleLocation location;
    private SMSStatus status;

    SMSLocationRequest(long id, String requester, long time) {
        this.id = id;
        this.requester = requester;
        this.time = time;
        this.location = null;
        this.status = SMSStatus.RUNNING;
    }

    long getId() {
        return id;
    }

    public String getRequester() {
        return requester;
    }

    public long getTime() {
        return time;
    }

    public SMSSimpleLocation getLocation() {
        return location;
    }

    public SMSStatus getStatus() {
        return status;
    }

    public void setSuccess(SMSSimpleLocation location) {
        this.status = SMSStatus.SUCCESS;
        this.location = location;
    }

    public void setNoLocation() {
        this.status = SMSStatus.NO_LOCATION;
        this.location = null;
    }

    public void setAborted() {
        this.status = SMSStatus.ABORTED;
        this.location = null;
    }

    public void setNoGps() {
        this.status = SMSStatus.NO_GPS;
        this.location = null;
    }

    public void setNetwork(SMSSimpleLocation location) {
        this.status = SMSStatus.NETWORK;
        this.location = location;
    }

    public void setNetworkNoGps(SMSSimpleLocation location) {
        this.status = SMSStatus.NETWORK_NO_GPS;
        this.location = location;
    }

    public String toString(Context context) {
        return requester + " ("
                + DateFormat.getDateFormat(context).format(new Date(time))
                + ", "
                + DateFormat.getTimeFormat(context).format(new Date(time))
                + ")";
    }

    void setStatus(SMSStatus status) {
        this.status = status;
    }

}