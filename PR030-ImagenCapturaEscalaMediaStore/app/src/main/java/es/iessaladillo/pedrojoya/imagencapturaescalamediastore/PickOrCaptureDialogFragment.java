package es.iessaladillo.pedrojoya.imagencapturaescalamediastore;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class PickOrCaptureDialogFragment extends DialogFragment {

    // Variables.
    private Listener mListener = null;

    // Interfaz pública para comunicación con la actividad.
    public interface Listener {
        public void onItemClick(DialogFragment dialog, int which);
    }

    // Al crear el diálogo. Retorna el diálogo configurado.
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
        b.setTitle(R.string.elige_opcion);
        b.setItems(R.array.pick_or_capture_opciones, new DialogInterface.OnClickListener() {
            // Cuando se selecciona el elemento.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Se le notifica al listener, indicándole el índice del
                // elemento seleccionado.
                mListener.onItemClick(PickOrCaptureDialogFragment.this,
                        which);
            }
        });
        b.setIcon(R.mipmap.ic_launcher);
        return b.create();
    }

    // Cuando se enlaza el fragmento con la actividad.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Establece la actividad como listener de los eventos del diálogo.
        try {
            mListener = (Listener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz, se lanza excepción.
            throw new ClassCastException(activity.toString()
                    + " debe implementar PickOrCaptureDialogFragment.Listener");
        }
    }

}