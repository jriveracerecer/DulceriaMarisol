package com.delao.dulceriamarisol.adapters;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.delao.dulceriamarisol.R;
import com.delao.dulceriamarisol.models.Cliente;
import java.util.List;

/**
 * Created by jrivera on 24/07/2017.
 */

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ClienteViewHolder> {

    private List<Cliente> clientesList;

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, direccion;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreTextView);
            direccion = (TextView) itemView.findViewById(R.id.direccionTextView);
        }
    }

    public ClientesAdapter(List<Cliente> clientesList) {
        this.clientesList = clientesList;
    }

    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente_list_row, parent, false);
        return  new ClienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {
        Cliente c = clientesList.get(position);
        holder.nombre.setText(c.getNombre());
        holder.direccion.setText(c.getDireccion());
    }

    @Override
    public int getItemCount() {
        return clientesList.size();
    }
}
