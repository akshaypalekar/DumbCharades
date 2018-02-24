package com.example.yugandhara.dumbcharades;

import android.annotation.TargetApi;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.Build;
        import android.util.Log;
        import android.widget.Toast;

        import java.util.Random;

public class DBAdapter {
    public static final String KEY_ROWID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_DIFFICULTY = "difficulty";
    public static final String KEY_USED = "used";
    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "movies";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table movies (id integer primary key autoincrement, "
                    + "name text not null, language text not null, difficulty text not null, used int DEFAULT 0 );";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }



    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);//SQLiteOpenHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        } // SQLiteDatabase.CursorFactory is null by default

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                db.execSQL(DATABASE_CREATE);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS movies");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertMovie(String name, String language, String difficulty)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_LANGUAGE, language);
        initialValues.put(KEY_DIFFICULTY, difficulty);
        return db.insert(DATABASE_TABLE, null, initialValues); // 2nd parameter -  null indicates all column values.
    }



    //---retrieves a particular movie---
    public Cursor getMovie(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(DATABASE_TABLE, new String[] {KEY_NAME}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---retrieves random IDs with required difficulty and language---
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Integer getMovieId(String language, String difficulty) throws SQLException {

        Cursor mCursor;
        String used= "0";

        if(language.equalsIgnoreCase("random"))
        {
            mCursor =
                    db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID}, KEY_DIFFICULTY + "=?" + " and " + KEY_USED + "=?", new String[]{difficulty, used}, null,
                            null, null, null, null);
        }
        else
        {
            mCursor =
                    db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID}, KEY_LANGUAGE + "=?" + " and " + KEY_DIFFICULTY + "=?" + " and " + KEY_USED + "=?", new String[]{language, difficulty, used}, null,
                            null, null, null, null);
        }


        int length = mCursor.getCount();  //Finds no of movies that follow conditions
        length = length + 1;
        //Generates random number
        Random r = new Random();
        int random_id_pos = r.nextInt(length - 1) + 1;

        // mCursor.moveToFirst();
        if(random_id_pos==(length-1))
        {
            random_id_pos--; //used if last item of array is picked as indexes start from 0
        }

        int movie_id = 0;
        mCursor.moveToPosition(random_id_pos); //Get record in cursor at the random position found
        movie_id = mCursor.getInt(0); //fetch movie name

        ContentValues args = new ContentValues();
        args.put(KEY_USED, "1");
        db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + movie_id, null);

        return movie_id;
    }

    public void updateMovie()
    {
        ContentValues args = new ContentValues();
        args.put(KEY_USED, "0");
        db.update(DATABASE_TABLE, args, null, null);
    }

}


