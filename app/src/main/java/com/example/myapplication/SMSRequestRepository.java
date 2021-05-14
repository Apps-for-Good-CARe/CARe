package com.example.myapplication;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SMSRequestRepository {

    private SQLiteDatabase database;
    private final SMSRequestSQLiteHelper dbHelper;
    private final String[] allColumns = { SMSRequestSQLiteHelper.COLUMN_ID,
            SMSRequestSQLiteHelper.COLUMN_REQUESTER,
            SMSRequestSQLiteHelper.COLUMN_TIME,
            SMSRequestSQLiteHelper.COLUMN_LONGITUDE,
            SMSRequestSQLiteHelper.COLUMN_LATITUDE,
            SMSRequestSQLiteHelper.COLUMN_STATUS };

    public SMSRequestRepository(Context context) {
        dbHelper = new SMSRequestSQLiteHelper(context);
    }

    public SMSLocationRequest createRequest(String requester) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(SMSRequestSQLiteHelper.COLUMN_REQUESTER, requester);
            values.put(SMSRequestSQLiteHelper.COLUMN_TIME,
                    System.currentTimeMillis());
            values.put(SMSRequestSQLiteHelper.COLUMN_LONGITUDE, (String) null);
            values.put(SMSRequestSQLiteHelper.COLUMN_LATITUDE, (String) null);
            values.put(SMSRequestSQLiteHelper.COLUMN_STATUS,
                    SMSStatus.RUNNING.getId());
            long insertId = database.insert(SMSRequestSQLiteHelper.TABLE_REQUESTS,
                    null, values);
            Cursor cursor = database.query(SMSRequestSQLiteHelper.TABLE_REQUESTS,
                    allColumns, SMSRequestSQLiteHelper.COLUMN_ID + " = "
                            + insertId, null, null, null, null);
            cursor.moveToFirst();
            SMSLocationRequest newRequest = cursorToRequest(cursor);
            cursor.close();
            return newRequest;
        } finally {
            close();
        }
    }

    public void updateRequest(SMSLocationRequest request) {
        open();
        ContentValues values = new ContentValues();
        values.put(SMSRequestSQLiteHelper.COLUMN_REQUESTER, request.getRequester());
        values.put(SMSRequestSQLiteHelper.COLUMN_TIME, request.getTime());
        values.put(SMSRequestSQLiteHelper.COLUMN_LONGITUDE,
                request.getStatus() == SMSStatus.SUCCESS ? request.getLocation()
                        .getLongitude() : null);
        values.put(SMSRequestSQLiteHelper.COLUMN_LATITUDE,
                request.getStatus() == SMSStatus.SUCCESS ? request.getLocation()
                        .getLatitude() : null);
        values.put(SMSRequestSQLiteHelper.COLUMN_STATUS, request.getStatus()
                .getId());
        database.update(SMSRequestSQLiteHelper.TABLE_REQUESTS, values,
                SMSRequestSQLiteHelper.COLUMN_ID + " = ?",
                new String[] { Long.toString(request.getId()) });
        close();
    }

    public List<SMSLocationRequest> getAllRequests() {
        open();
        try {
            List<SMSLocationRequest> requests = new ArrayList<SMSLocationRequest>();

            Cursor cursor = database.query(SMSRequestSQLiteHelper.TABLE_REQUESTS,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                SMSLocationRequest request = cursorToRequest(cursor);
                requests.add(request);
                cursor.moveToNext();
            }
            cursor.close();
            return requests;
        } finally {
            close();
        }
    }

    public void deleteAllRequests() {
        open();
        database.delete(SMSRequestSQLiteHelper.TABLE_REQUESTS, null, null);
        close();
    }

    void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    void close() {
        dbHelper.close();
    }

    private SMSLocationRequest cursorToRequest(Cursor cursor) {
        SMSLocationRequest request = new SMSLocationRequest(cursor.getLong(0),
                cursor.getString(1), cursor.getLong(2));
        SMSStatus status = SMSStatus.getForId(cursor.getInt(5));
        if (status == SMSStatus.SUCCESS) {
            request.setSuccess(new SMSSimpleLocation(cursor.getFloat(3), cursor
                    .getFloat(4)));
        } else {
            request.setStatus(status);
        }
        return request;
    }
}