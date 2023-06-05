package com.example.cardioapp.Medico;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cardioapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class VistaPacienteActivity extends AppCompatActivity {
    GraphView graphView;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_paciente);
        //inicializamos la grafica 500 Hz por defecto
        graphView = findViewById(R.id.grafica500);

        //Botón de volver
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        spinner = findViewById(R.id.spinner);
        List<String> listaDropdown = new ArrayList<>();
        listaDropdown.add(0, "500Hz");
        listaDropdown.add("1000");
        listaDropdown.add("5000");
        listaDropdown.add("10000");
        listaDropdown.add("50000");
        //listaDropdown.add("Gareth Bale");
        //listaDropdown.add("Luis Suarez");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDropdown);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        /*FrameLayout  posiciona las vistas usando todo el contenedor, sin distribuirlas espacialmente.
          Este Layout suele utilizarse cuando queremos que varias vistas ocupen un mismo lugar.
          Podemos hacer que solo una sea visible, o superponerlas.Para modificar la visibilidad de un elemento
          utilizaremos la propiedad visibility. */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Register a callback to be invoked when an item in this AdapterView has been selected.
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Elige")){
                }else {
                    String item = parent.getItemAtPosition(position).toString();
                    FrameLayout frameLayout = findViewById(R.id.grafica);
                    if(arrayAdapter.getItem(position)=="1000"){
                        GraphView graphV = findViewById(R.id.grafica1000);
                        graphV.setVisibility(view.VISIBLE);
                    }else if(arrayAdapter.getItem(position)=="5000"){
                        GraphView graphV = findViewById(R.id.grafica5000);
                        graphV.setVisibility(view.VISIBLE);
                    }else if(arrayAdapter.getItem(position)=="10000") {
                        GraphView graphV = findViewById(R.id.grafica10000);
                        graphV.setVisibility(view.VISIBLE);
                    }else if(arrayAdapter.getItem(position)=="50000") {
                        GraphView graphV = findViewById(R.id.grafica10000);
                        graphV.setVisibility(view.VISIBLE);
                    }
                    //inflate grafica
                    creaGrafica();
                    //CAMBIAR POR IMPRIMIR EL GRAPHVIEW QUE CORRESPONDE
                    Toast.makeText(parent.getContext(),"Seleccionado: " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void creaGrafica() {
        //Añadimos los datos al graph view.
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // Añadimos cada punto de los ejes.
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 9),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 1),
                new DataPoint(8, 2)
        });

        // Título del graph view.
        graphView.setTitle("My Graph View");

        // Color del texto del graph view.
        graphView.setTitleColor(R.color.purple_200);

        // Tamaño de letra del título
        graphView.setTitleTextSize(18);

        // Añadimos la lsta de puntos al gráfico
        graphView.addSeries(series);
    }

}