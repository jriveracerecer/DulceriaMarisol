package com.delao.dulceriamarisol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.delao.dulceriamarisol.data.DatabaseHandler;
import com.delao.dulceriamarisol.fragments.MensajesDialogFragment;
import com.delao.dulceriamarisol.models.Cliente;

public class AltaClienteActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText direccion;
    private EditText telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_cliente);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = (EditText) findViewById(R.id.nombreEditText);
        direccion = (EditText) findViewById(R.id.direccionEditText);
        telefono = (EditText) findViewById(R.id.telefonoEditText);
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
                guardaCliente();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardaCliente(){

        MensajesDialogFragment dialog = new MensajesDialogFragment();

        if (nombre.getText().toString() == null || nombre.getText().toString().trim().isEmpty()){
            dialog.setMensaje(getString(R.string.validacion_nombre));
            dialog.show(getSupportFragmentManager(), "nombre");
            return;
        }

        if (direccion.getText().toString() == null || direccion.getText().toString().trim().isEmpty()){
            dialog.setMensaje(getString(R.string.validacion_direccion));
            dialog.show(getSupportFragmentManager(), "direccion");
            return;
        }

        DatabaseHandler db = new DatabaseHandler(AltaClienteActivity.this, this);
        Cliente c = new Cliente();
        c.setNombre(nombre.getText().toString());
        c.setDireccion(direccion.getText().toString());
        c.setTelefono("7225489765");
        db.addCliente(c);
    }
}
