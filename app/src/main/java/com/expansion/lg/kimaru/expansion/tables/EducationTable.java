package com.expansion.lg.kimaru.expansion.tables;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.expansion.lg.kimaru.expansion.mzigos.Education;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kimaru on 2/28/17.
 */


public class EducationTable extends SQLiteOpenHelper {

    public static final String TABLE_NAME="education";
    public static final String DATABASE_NAME="expansion";
    public static final int DATABASE_VERSION=1;

    //String id, level_name, level_type, hierachy, country;

    public static String varchar_field = " varchar(512) ";
    public static String primary_field = " _id INTEGER PRIMARY KEY AUTOINCREMENT ";
    public static String integer_field = " integer default 0 ";
    public static String text_field = " text ";

    public static final String ID = "_id";
    public static final String LEVEL_NAME= "name";
    public static final String TYPE = "level_type";
    public static final String HIERACHY = "hierachy";
    public static final String COUNTRY = "country";

    public static final String CREATE_DATABASE="CREATE TABLE " + TABLE_NAME + "("
            + primary_field + ", "
            + LEVEL_NAME + varchar_field + ", "
            + TYPE + varchar_field + ", "
            + COUNTRY + varchar_field + ", "
            + HIERACHY + integer_field + "); ";

    public static final String DATABASE_DROP="DROP TABLE IF EXISTS" + TABLE_NAME;

    public EducationTable(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w("RegistrationTable", "upgrading database from" + oldVersion + "to" + newVersion);
        db.execSQL(DATABASE_DROP);
    }

    public long addEducation(Education education) {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ID, education.getId());
        cv.put(LEVEL_NAME, education.getLevelName());
        cv.put(TYPE, education.getLevelType());
        cv.put(HIERACHY, education.getHierachy());
        cv.put(COUNTRY, education.getCountry());

        long id = db.insertWithOnConflict(TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_REPLACE);

        db.close();
        return id;

    }
    public List<Education> getEducationData() {

        SQLiteDatabase db=getReadableDatabase();

        String [] columns=new String[]{ID, LEVEL_NAME, TYPE, HIERACHY, COUNTRY};

        Cursor cursor=db.query(TABLE_NAME,columns,null,null,null,null,null,null);

        List<Education> educationList=new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
            Education education=new Education();

            education.setId(cursor.getInt(0));
            education.setLevelName(cursor.getString(1));
            education.setLevelType(cursor.getString(2));
            education.setHierachy(cursor.getInt(3));
            education.setCountry(cursor.getString(4));

            educationList.add(education);
        }
        db.close();
        return educationList;
    }


    public JSONObject getEducationJson() {

        SQLiteDatabase db=getReadableDatabase();

        String [] columns=new String[]{ID, LEVEL_NAME, TYPE, HIERACHY, COUNTRY};

        Cursor cursor=db.query(TABLE_NAME,columns,null,null,null,null,null,null);

        JSONObject results = new JSONObject();

        JSONArray resultSet = new JSONArray();

        for (cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
            int totalColumns = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i =0; i < totalColumns; i++){
                if (cursor.getColumnName(i) != null){
                    try {
                        if (cursor.getString(i) != null){
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        }else{
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    }catch (Exception e){
                    }
                }
            }
            resultSet.put(rowObject);
            try {
                results.put("education", resultSet);
            } catch (JSONException e) {

            }
        }
        cursor.close();
        db.close();
        return results;
    }

    public Cursor getEducationDataCursor() {

        SQLiteDatabase db=getReadableDatabase();

        String [] columns=new String[]{ID, LEVEL_NAME, TYPE, HIERACHY, COUNTRY};

        Cursor cursor=db.query(TABLE_NAME,columns,null,null,null,null,null,null);

        return cursor;
    }

    public void createEducationLevels(){
        //P1 to 8
        // id, hierachy;
        String levelName, levelType, country;
        for (int x = 1; x <= 16; x++){
            Education education = new Education();
            education.setId(x);
            education.setHierachy(x);
            if (x > 12) {
                education.setLevelName("Tertiary (Year " + (x - 12) + ")");
                education.setLevelType("tertiary");
            }else if (x > 8){
                education.setLevelName("Secondary (Form) " + (x - 8));
                education.setLevelType("secondary");
            }else {
                education.setLevelName("Primary (Class) " + x);
                education.setLevelType("primary");
            }
            this.addEducation(education);
        }
    }


}

