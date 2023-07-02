package com.example.cardioapp.Medico;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardioapp.Paciente.Paciente;
import com.example.cardioapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class VistaPacienteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    GraphView graphView, graphView2;
    Spinner spinner;
    ArrayAdapter<String> arrayAdapter;
    TextView nombrePaciente, edadPaciente, historiaCLinica1, historiaClinica23;
    Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_paciente);
        Intent intent = getIntent();
        String nombre, edad, hC23, hC1;

        paciente = (Paciente) intent.getSerializableExtra("paciente");

        nombrePaciente = findViewById(R.id.nombrePaciente);
        edadPaciente = findViewById(R.id.edadPaciente);
        historiaCLinica1 = findViewById(R.id.historiaClinica);
        historiaClinica23= findViewById(R.id.historiaClinica23);

        nombre = paciente.getNombrePaciente() + " " + paciente.getApellidosPaciente();
        edad =  edadPaciente.getText() + paciente.getEdad().toString();
        hC1 = historiaCLinica1.getText()+paciente.getHistoriaClinica();
        if(paciente.getHistoriaClinica3() != null){
            hC23 = historiaClinica23.getText()+paciente.getHistoriaClinica2() + " y " + paciente.getHistoriaClinica3();
        }else{
            hC23 = historiaClinica23.getText()+paciente.getHistoriaClinica2();
        }
        nombrePaciente.setText(nombre);
        edadPaciente.setText(edad);
        historiaCLinica1.setText(hC1);
        historiaClinica23.setText(hC23);

        //inicializamos la grafica 100 Hz por defecto
        graphView = findViewById(R.id.graficaPpal);
        graphView2 = findViewById(R.id.Fluido);
        imprimeFluido();

        //Botón de volver
        /*if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/
        spinner = findViewById(R.id.spinner);

        //Asignacion de los campos del DropDown
        List<String> listaDropdown = new ArrayList<>();
        listaDropdown.add(0, "100");
        listaDropdown.add("500");
        listaDropdown.add("1000");
        listaDropdown.add("5000");
        listaDropdown.add("10000");
        listaDropdown.add("50000");

        //Pasamos al spinner los datos del DropDown para que haga de selector de las gráficas
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDropdown);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    public void compruebaVisibles(String seleccion){
        //Revisamos los graficos visibles
        int i=0,count = arrayAdapter.getCount()-1;
        graphView.removeAllSeries();
        for( i=0; i<=count; i++){
            //Ponemos invisibles todos menos el actual
            String item = arrayAdapter.getItem(i);
            boolean bool=!item.equals(seleccion);
            //Si no es nuestra seleccion del dropdown, marcamos como invisible
            if(bool){
                graphView.setVisibility(View.INVISIBLE);
            }
        }
    }
    public void imprimeFluido() {
        LineGraphSeries<DataPoint> serie1 = null;
        //Añadimos los datos al graph view.
            serie1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    // Añadimos cada punto de los ejes.
                    new DataPoint(0, 555.7),
                    new DataPoint(1, 544.59),
                    new DataPoint(2, 533.69),
                    new DataPoint(3, 523.0),
                    new DataPoint(4, 512.56),
                    new DataPoint(5, 502.31),
                    new DataPoint(6, 492.2),
                    new DataPoint(7, 482.42),
                    new DataPoint(8, 472.77),
                    new DataPoint(9, 463.31),
                    new DataPoint(10, 454.05),
                    new DataPoint(11, 444.97),
                    new DataPoint(12, 436.07),
                    new DataPoint(13, 427.35),
                    new DataPoint(14, 418.8),
                    new DataPoint(15, 410.4),
                    new DataPoint(16, 402.21),
                    new DataPoint(17, 394.17),
                    new DataPoint(18, 386.29),
                    new DataPoint(19, 378.56),
                    new DataPoint(20, 370.99)
            });
        graphView2.addSeries(serie1);

        // Título del graph view.
        graphView2.setTitle("% Retención de líquido");
        // Tamaño de letra del título
        graphView2.setTitleTextSize(60);
        // Color del texto del graph view.
        graphView2.setTitleColor(R.color.colorPrimaryDark);
        //Leyenda para e gráfico
        graphView2.getGridLabelRenderer().setHorizontalAxisTitle("T");
        //Visibilidad de la grágica actual
        graphView2.setVisibility(View.VISIBLE);

    }

    /*Se usa un FrameLayout para que todas las gráficas ocupen el mismo espacio, haremos que
     sea visible solo una según la selección del dropdown. */
        /**Callback para invocarlo cuando se selecciona un elemento del AdapterView.**/
        public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {
            String seleccion = arrayAdapter.getItem(posicion);
            LineGraphSeries<DataPoint> serie1 = null, serie2 = null;
            compruebaVisibles(seleccion);
            //Añadimos los datos al graph view.
            if(seleccion.equals("100")){
                serie1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 555.7),
                        new DataPoint(1, 544.59),
                        new DataPoint(2, 533.69),
                        new DataPoint(3, 523.0),
                        new DataPoint(4, 512.56),
                        new DataPoint(5, 502.31),
                        new DataPoint(6, 492.2),
                        new DataPoint(7, 482.42),
                        new DataPoint(8, 472.77),
                        new DataPoint(9, 463.31),
                        new DataPoint(10, 454.05),
                        new DataPoint(11, 444.97),
                        new DataPoint(12, 436.07),
                        new DataPoint(13, 427.35),
                        new DataPoint(14, 418.8),
                        new DataPoint(15, 410.4),
                        new DataPoint(16, 402.21),
                        new DataPoint(17, 394.17),
                        new DataPoint(18, 386.29),
                        new DataPoint(19, 378.56),
                        new DataPoint(20, 370.99)
                });
                serie2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 45.5),
                        new DataPoint(1, 45.0),
                        new DataPoint(2, 44.5),
                        new DataPoint(3, 44.0),
                        new DataPoint(4, 43.5),
                        new DataPoint(5, 43.0),
                        new DataPoint(6, 42.5),
                        new DataPoint(7, 42.0),
                        new DataPoint(8, 41.5),
                        new DataPoint(9, 41.0),
                        new DataPoint(10, 40.5),
                        new DataPoint(11, 40.0),
                        new DataPoint(12, 39.5),
                        new DataPoint(13, 39.0),
                        new DataPoint(14, 38.5),
                        new DataPoint(15, 38.0),
                        new DataPoint(16, 37.5),
                        new DataPoint(17, 37.0),
                        new DataPoint(18, 36.5),
                        new DataPoint(19, 36.0),
                        new DataPoint(20, 35.5)
                });
            }else if(seleccion.equals("500")){
                serie1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 500.13),
                        new DataPoint(1, 490.13),
                        new DataPoint(2, 480.32),
                        new DataPoint(3, 470.72),
                        new DataPoint(4, 461.3),
                        new DataPoint(5, 452.08),
                        new DataPoint(6, 443.04),
                        new DataPoint(7, 434.18),
                        new DataPoint(8, 425.49),
                        new DataPoint(9, 416.98),
                        new DataPoint(10, 408.64),
                        new DataPoint(11, 400.47),
                        new DataPoint(12, 392.46),
                        new DataPoint(13, 384.61),
                        new DataPoint(14, 376.92),
                        new DataPoint(15, 369.3),
                        new DataPoint(16, 361.9),
                        new DataPoint(17, 354.75),
                        new DataPoint(18, 347.66),
                        new DataPoint(19, 340.7),
                        new DataPoint(20, 333.89)
                });
                serie2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 40.95),
                        new DataPoint(1, 40.5),
                        new DataPoint(2, 40.05),
                        new DataPoint(3, 39.6),
                        new DataPoint(4, 39.15),
                        new DataPoint(5, 38.7),
                        new DataPoint(6, 38.25),
                        new DataPoint(7, 37.8),
                        new DataPoint(8, 37.35),
                        new DataPoint(9, 36.9),
                        new DataPoint(10, 36.45),
                        new DataPoint(11, 36.0),
                        new DataPoint(12, 35.55),
                        new DataPoint(13, 35.1),
                        new DataPoint(14, 34.65),
                        new DataPoint(15, 34.2),
                        new DataPoint(16, 33.75),
                        new DataPoint(17, 33.3),
                        new DataPoint(18, 32.85),
                        new DataPoint(19, 32.4),
                        new DataPoint(20, 31.95)
                });
            }else if(seleccion.equals("1000")){
                serie1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 500.13),
                        new DataPoint(1, 490.13),
                        new DataPoint(2, 480.32),
                        new DataPoint(3, 470.72),
                        new DataPoint(4, 461.3),
                        new DataPoint(5, 452.08),
                        new DataPoint(6, 443.04),
                        new DataPoint(7, 434.18),
                        new DataPoint(8, 425.49),
                        new DataPoint(9, 416.98),
                        new DataPoint(10, 408.64),
                        new DataPoint(11, 400.47),
                        new DataPoint(12, 392.46),
                        new DataPoint(13, 384.61),
                        new DataPoint(14, 376.92),
                        new DataPoint(15, 369.3),
                        new DataPoint(16, 361.9),
                        new DataPoint(17, 354.75),
                        new DataPoint(18, 347.66),
                        new DataPoint(19, 340.7),
                        new DataPoint(20, 333.89)
                });
                serie2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 40.95),
                        new DataPoint(1, 40.5),
                        new DataPoint(2, 40.05),
                        new DataPoint(3, 39.6),
                        new DataPoint(4, 39.15),
                        new DataPoint(5, 38.7),
                        new DataPoint(6, 38.25),
                        new DataPoint(7, 37.8),
                        new DataPoint(8, 37.35),
                        new DataPoint(9, 36.9),
                        new DataPoint(10, 36.45),
                        new DataPoint(11, 36.0),
                        new DataPoint(12, 35.55),
                        new DataPoint(13, 35.1),
                        new DataPoint(14, 34.65),
                        new DataPoint(15, 34.2),
                        new DataPoint(16, 33.75),
                        new DataPoint(17, 33.3),
                        new DataPoint(18, 32.85),
                        new DataPoint(19, 32.4),
                        new DataPoint(20, 31.95)
                });
            }else if(seleccion.equals("5000")){
                serie1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 500.13),
                        new DataPoint(1, 490.13),
                        new DataPoint(2, 480.32),
                        new DataPoint(3, 470.72),
                        new DataPoint(4, 461.3),
                        new DataPoint(5, 452.08),
                        new DataPoint(6, 443.04),
                        new DataPoint(7, 434.18),
                        new DataPoint(8, 425.49),
                        new DataPoint(9, 416.98),
                        new DataPoint(10, 408.64),
                        new DataPoint(11, 400.47),
                        new DataPoint(12, 392.46),
                        new DataPoint(13, 384.61),
                        new DataPoint(14, 376.92),
                        new DataPoint(15, 369.3),
                        new DataPoint(16, 361.9),
                        new DataPoint(17, 354.75),
                        new DataPoint(18, 347.66),
                        new DataPoint(19, 340.7),
                        new DataPoint(20, 333.89)
                });
                serie2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 40.95),
                        new DataPoint(1, 40.5),
                        new DataPoint(2, 40.05),
                        new DataPoint(3, 39.6),
                        new DataPoint(4, 39.15),
                        new DataPoint(5, 38.7),
                        new DataPoint(6, 38.25),
                        new DataPoint(7, 37.8),
                        new DataPoint(8, 37.35),
                        new DataPoint(9, 36.9),
                        new DataPoint(10, 36.45),
                        new DataPoint(11, 36.0),
                        new DataPoint(12, 35.55),
                        new DataPoint(13, 35.1),
                        new DataPoint(14, 34.65),
                        new DataPoint(15, 34.2),
                        new DataPoint(16, 33.75),
                        new DataPoint(17, 33.3),
                        new DataPoint(18, 32.85),
                        new DataPoint(19, 32.4),
                        new DataPoint(20, 31.95)
                });
            }else if(seleccion.equals("10000")){
                serie1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 500.13),
                        new DataPoint(1, 490.13),
                        new DataPoint(2, 480.32),
                        new DataPoint(3, 470.72),
                        new DataPoint(4, 461.3),
                        new DataPoint(5, 452.08),
                        new DataPoint(6, 443.04),
                        new DataPoint(7, 434.18),
                        new DataPoint(8, 425.49),
                        new DataPoint(9, 416.98),
                        new DataPoint(10, 408.64),
                        new DataPoint(11, 400.47),
                        new DataPoint(12, 392.46),
                        new DataPoint(13, 384.61),
                        new DataPoint(14, 376.92),
                        new DataPoint(15, 369.3),
                        new DataPoint(16, 361.9),
                        new DataPoint(17, 354.75),
                        new DataPoint(18, 347.66),
                        new DataPoint(19, 340.7),
                        new DataPoint(20, 333.89)
                });
                serie2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 40.95),
                        new DataPoint(1, 40.5),
                        new DataPoint(2, 40.05),
                        new DataPoint(3, 39.6),
                        new DataPoint(4, 39.15),
                        new DataPoint(5, 38.7),
                        new DataPoint(6, 38.25),
                        new DataPoint(7, 37.8),
                        new DataPoint(8, 37.35),
                        new DataPoint(9, 36.9),
                        new DataPoint(10, 36.45),
                        new DataPoint(11, 36.0),
                        new DataPoint(12, 35.55),
                        new DataPoint(13, 35.1),
                        new DataPoint(14, 34.65),
                        new DataPoint(15, 34.2),
                        new DataPoint(16, 33.75),
                        new DataPoint(17, 33.3),
                        new DataPoint(18, 32.85),
                        new DataPoint(19, 32.4),
                        new DataPoint(20, 31.95)
                });
            }else if(seleccion.equals("50000")){
                serie1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 500.13),
                        new DataPoint(1, 490.13),
                        new DataPoint(2, 480.32),
                        new DataPoint(3, 470.72),
                        new DataPoint(4, 461.3),
                        new DataPoint(5, 452.08),
                        new DataPoint(6, 443.04),
                        new DataPoint(7, 434.18),
                        new DataPoint(8, 425.49),
                        new DataPoint(9, 416.98),
                        new DataPoint(10, 408.64),
                        new DataPoint(11, 400.47),
                        new DataPoint(12, 392.46),
                        new DataPoint(13, 384.61),
                        new DataPoint(14, 376.92),
                        new DataPoint(15, 369.3),
                        new DataPoint(16, 361.9),
                        new DataPoint(17, 354.75),
                        new DataPoint(18, 347.66),
                        new DataPoint(19, 340.7),
                        new DataPoint(20, 333.89)
                });
                serie2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        // Añadimos cada punto de los ejes.
                        new DataPoint(0, 40.95),
                        new DataPoint(1, 40.5),
                        new DataPoint(2, 40.05),
                        new DataPoint(3, 39.6),
                        new DataPoint(4, 39.15),
                        new DataPoint(5, 38.7),
                        new DataPoint(6, 38.25),
                        new DataPoint(7, 37.8),
                        new DataPoint(8, 37.35),
                        new DataPoint(9, 36.9),
                        new DataPoint(10, 36.45),
                        new DataPoint(11, 36.0),
                        new DataPoint(12, 35.55),
                        new DataPoint(13, 35.1),
                        new DataPoint(14, 34.65),
                        new DataPoint(15, 34.2),
                        new DataPoint(16, 33.75),
                        new DataPoint(17, 33.3),
                        new DataPoint(18, 32.85),
                        new DataPoint(19, 32.4),
                        new DataPoint(20, 31.95)
                });
            }
            //Ponemos en otro color la línea 2 para diferenciarlas
            serie2.setColor(Color.MAGENTA);
            graphView.addSeries(serie1);
            graphView.addSeries(serie2);

            // Título del graph view.
            graphView.setTitle(seleccion+"Hz");
            // Tamaño de letra del título
            graphView.setTitleTextSize(60);
            // Color del texto del graph view.
            graphView.setTitleColor(R.color.colorPrimaryDark);
            //Leyenda para e gráfico
            graphView.getGridLabelRenderer().setHorizontalAxisTitle("T");
            graphView.getGridLabelRenderer().setVerticalAxisTitle("R"+seleccion+" y  X"+seleccion);
            //Visibilidad de la grágica actual
            graphView.setVisibility(View.VISIBLE);

        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClickVolver(View view) {
        startActivity(new Intent(VistaPacienteActivity.this, PrincipalActivity.class));
    }

}