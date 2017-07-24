package com.delao.dulceriamarisol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.delao.dulceriamarisol.adapters.ClientesAdapter;
import com.delao.dulceriamarisol.adapters.DividerItemDecoration;
import com.delao.dulceriamarisol.data.DatabaseHandler;
import com.delao.dulceriamarisol.fragments.MensajesDialogFragment;
import com.delao.dulceriamarisol.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText direccion;
    private EditText telefono;

    private List<Cliente> clienteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ClientesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Clientes");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new ClientesAdapter(clienteList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

//        nombre = (EditText) findViewById(R.id.nombreEditText);
//        direccion = (EditText) findViewById(R.id.direccionEditText);
//        telefono = (EditText) findViewById(R.id.telefonoEditText);

        iniciaAdapter();
    }

    private void iniciaAdapter() {

        DatabaseHandler db = new DatabaseHandler(ClientesActivity.this, this);
        clienteList.addAll(db.getAllClientes());

        adapter.notifyDataSetChanged();
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

        DatabaseHandler db = new DatabaseHandler(ClientesActivity.this, this);
        Cliente c = new Cliente();
        c.setNombre(nombre.getText().toString());
        c.setDireccion(direccion.getText().toString());
        c.setTelefono("7225489765");
        db.addCliente(c);
    }
}
