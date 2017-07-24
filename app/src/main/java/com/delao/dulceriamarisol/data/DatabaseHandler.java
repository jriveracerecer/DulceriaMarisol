package com.delao.dulceriamarisol.data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import com.delao.dulceriamarisol.R;
import com.delao.dulceriamarisol.models.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrivera on 18/07/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dulceriaMarisol";
    private static final String TABLE_CLIENTES = "clientes";
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_DIRECCION = "direccion";
    private static final String KEY_TELEFONO = "telefono";

    private DatabaseHandler db;
    private Context context;
    private Activity activity;

    public DatabaseHandler(Context context, Activity activity){
        super(context, DATABASE_NAME, null, 1);
        this.db = this;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLIENTES_TABLE = "CREATE TABLE " + TABLE_CLIENTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOMBRE + " TEXT,"
                + KEY_DIRECCION + " TEXT," + KEY_TELEFONO + " TEXT" + ")";
        db.execSQL(CREATE_CLIENTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);

        // Create tables again
        onCreate(db);
    }

    public void addCliente(Cliente cliente){
        new InsertData().execute(cliente);
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> clienteList = new ArrayList<Cliente>();
        String query = "SELECT Nombre, Direccion FROM " + TABLE_CLIENTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente c = new Cliente();
                c.setNombre(cursor.getString(0));
                c.setDireccion(cursor.getString(1));
                clienteList.add(c);
            } while (cursor.moveToNext());
        }

        return clienteList;
    }

    private class InsertData extends AsyncTask<Cliente, Void, Void>{

        @Override
            protected void onPreExecute() {
                //super.onPreExecute();
//                activity.findViewById(R.id.nombreEditText).setEnabled(false);
//                activity.findViewById(R.id.direccionEditText).setEnabled(false);
//                activity.findViewById(R.id.telefonoEditText).setEnabled(false);
            }

            @Override
            protected Void doInBackground(Cliente... params) {
                SQLiteDatabase data = db.getWritableDatabase();

                ContentValues values = new ContentValues();
                //values.put(KEY_ID, 1);
                values.put(KEY_NOMBRE, params[0].getNombre());
                values.put(KEY_DIRECCION, params[0].getDireccion());
                values.put(KEY_TELEFONO, params[0].getTelefono());

                data.insert(TABLE_CLIENTES, null, values);
                data.close();

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                //Toast.makeText(context, "Insertado", Toast.LENGTH_SHORT).show();
                //super.onPostExecute(aVoid);
//                activity.findViewById(R.id.nombreEditText).setEnabled(true);
//                activity.findViewById(R.id.direccionEditText).setEnabled(true);
//                activity.findViewById(R.id.telefonoEditText).setEnabled(true);
        }
    }
}
