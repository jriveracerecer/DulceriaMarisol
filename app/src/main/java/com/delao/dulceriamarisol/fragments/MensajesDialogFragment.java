package com.delao.dulceriamarisol.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.delao.dulceriamarisol.R;

/**
 * Created by jrivera on 24/07/2017.
 */

public class MensajesDialogFragment extends DialogFragment {

    private String mensaje = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(mensaje)
                .setPositiveButton(R.string.texto_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
