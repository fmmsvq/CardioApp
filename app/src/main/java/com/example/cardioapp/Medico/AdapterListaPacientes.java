package com.example.cardioapp.Medico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cardioapp.Paciente.Paciente;
import com.example.cardioapp.R;

import java.util.ArrayList;

public class AdapterListaPacientes extends BaseAdapter {

    ArrayList<Paciente> pacientes;
    //String arrayPacientes[];
    private final Context context;
    //LayoutInflater inflater;
    public ListaPacientesActivity activity;

    public AdapterListaPacientes(Context context, ListaPacientesActivity _activity){
        this.context = context;
        this.activity = _activity;
        this.pacientes =new ArrayList<>(_activity.arrayPacientes);
    }
    public AdapterListaPacientes(Context applicationContext, ArrayList<Paciente> pacientes){//, int[] fotosPacientes) {
        this.context= applicationContext;
        this.pacientes=pacientes;
    }

    @Override
    public int getCount() {
        return pacientes.size();
    }

    @Override
    public Paciente getItem(int i) {
        return pacientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return pacientes.get(i).getID();
    }

    /**Este método se llama automáticamente cuando se necesita mostrar un elemento específico del ListView en la pantalla.
     * Se invoca internamente por el sistema cuando el ListView se está dibujando y necesita generar la vista para un elemento concreto.
     *   @param posicion: posición del elemento en el conjunto de datos del Adapter que se debe mostrar.
     *   @param convertView: vista anteriormente creada para este elemento, que se puede reutilizar para un mejor rendimiento.
     *   @param viewGroup: ViewGroup padre al que se adjuntará la vista.*/
    @Override
    public View getView(int posicion, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        //Paciente item = getItem(posicion);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.paciente_nav1, null);
        }
        Paciente p = pacientes.get(posicion);

        TextView nombrePaciente = view.findViewById(R.id.textViewNombre);
        TextView histClinica = view.findViewById(R.id.historia_clinica);
        TextView edad = view.findViewById(R.id.edad);

        CharSequence s = p.getEdad().toString();
        nombrePaciente.setText(p.getNombrePaciente() + " " + p.getApellidosPaciente());
        edad.setText(p.getEdad().toString()+" años");
        histClinica.setText(p.getHistoriaClinica());

        return view;
    }

}
