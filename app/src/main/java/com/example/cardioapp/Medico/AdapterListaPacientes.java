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
        ViewHolder viewHolder;
        //ListaPacientesActivity instance = new ListaPacientesActivity(this);
        Paciente item = getItem(posicion);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_lista_pacientes, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TextView nombrePaciente = this.activity.findViewById(R.id.paciente_nombre);

        //TextView apellidoPaciente = convertView.findViewById(R.id.paciente_nombre);
        CharSequence s = item.getNombrePaciente();
        nombrePaciente.setText("maria");
        //apellidoPaciente.setText(item.getApellidosPaciente());
        // Inflar o reutilizar la vista
        /*if (convertView == null) {
            //convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_lista_pacientes, viewGroup, false);
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_lista_pacientes, viewGroup, false);
        }
        // Obtener los datos del elemento en la posición actual
        Paciente item = getItem(posicion);
        //Paciente p = item
        // Configurar la vista con los datos correspondientes
            // Por ejemplo, si tienes un TextView en el layout "list_item_layout":
        TextView nombrePaciente = convertView.findViewById(R.id.paciente_nombre);
        //TextView apellidoPaciente = convertView.findViewById(R.id.paciente_apellido);
        String s= item.getApellidosPaciente();
        //apellidoPaciente.setText(s);
        nombrePaciente.setText(item.getNombrePaciente());*/

        return convertView;
    }

    private class ViewHolder {
        TextView nombrePaciente;
       //TextView apellidoPaciente;

        public ViewHolder(View view) {
            nombrePaciente = view.findViewById(R.id.paciente_nombre);
           // apellidoPaciente = view.findViewById(R.id.paciente_apellido);
        }
    }


}
