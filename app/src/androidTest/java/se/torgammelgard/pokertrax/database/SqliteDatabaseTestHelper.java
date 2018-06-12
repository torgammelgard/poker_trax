package se.torgammelgard.pokertrax.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class SqliteDatabaseTestHelper {

    static void insertGameStructure(int id, int smallBlind, int bigBlind, int ante, SqliteTestDbOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put("small_blind", smallBlind);
        values.put("big_blind", bigBlind);
        values.put("ante", ante);

        db.insertWithOnConflict("game_structure", null, values,
                SQLiteDatabase.CONFLICT_REPLACE);

        db.close();
    }

    static void createTable(SqliteTestDbOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS game_structure (_id INTEGER PRIMARY KEY, "
                + "small_blind INT, big_blind INT, ante INT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS game_type (_id INTEGER PRIMARY KEY, name TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS session (_id INTEGER PRIMARY KEY, "
                + "game_type INTEGER, location TEXT, game_structure INTEGER, "
                + "duration INTEGER, date DATE, result INTEGER, game_info TEXT, "
                + "FOREIGN KEY(game_type) REFERENCES game_type, "
                + "FOREIGN KEY(game_structure) REFERENCES game_structure)");
        db.close();
    }

    static void clearDatabase(SqliteTestDbOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS game_structure");
        db.execSQL("DROP TABLE IF EXISTS game_type");
        db.execSQL("DROP TABLE IF EXISTS session");
        db.close();
    }
}