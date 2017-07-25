package com.delao.dulceriamarisol;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.delao.dulceriamarisol.adapters.ClientesAdapter;
import com.delao.dulceriamarisol.adapters.DividerItemDecoration;
import com.delao.dulceriamarisol.data.DatabaseHandler;
import com.delao.dulceriamarisol.models.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {

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

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ClientesActivity.this, AltaClienteActivity.class);
                startActivity(intent);
                //Toast.makeText(ClientesActivity.this, "Fab click", Toast.LENGTH_SHORT).show();
            }
        });

        iniciaAdapter();
    }

    private void iniciaAdapter() {

        DatabaseHandler db = new DatabaseHandler(ClientesActivity.this, this);
        clienteList.addAll(db.getAllClientes());

        adapter.notifyDataSetChanged();
    }
}
