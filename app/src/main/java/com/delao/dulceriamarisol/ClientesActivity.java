package com.delao.dulceriamarisol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.delao.dulceriamarisol.data.DatabaseHandler;
import com.delao.dulceriamarisol.models.Cliente;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Clientes");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_item_guardar:
                DatabaseHandler db = new DatabaseHandler(ClientesActivity.this, this);
                Cliente c = new Cliente();
                c.setNombre("Jesús");
                c.setDireccion("Toluca");
                c.setTelefono("7225489765");
                db.addCliente(c);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
