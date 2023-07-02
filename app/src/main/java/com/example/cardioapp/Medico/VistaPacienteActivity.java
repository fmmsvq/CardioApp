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
        if (paciente.getHistoriaClinica3().equals("")&&paciente.getHistoriaClinica2().equals("")){
            historiaClinica23.setVisibility(View.GONE);
        }else if(!paciente.getHistoriaClinica2().equals("")&&!paciente.getHistoriaClinica3().equals("")){
            hC23 = historiaClinica23.getText()+paciente.getHistoriaClinica2() + " y " + paciente.getHistoriaClinica3();
            historiaClinica23.setText(hC23);
        }else if(paciente.getHistoriaClinica3().equals("")&&!paciente.getHistoriaClinica2().equals("")){
            hC23 = historiaClinica23.getText()+paciente.getHistoriaClinica2();
            historiaClinica23.setText(hC23);
        }
        nombrePaciente.setText(nombre);
        edadPaciente.setText(edad);
        historiaCLinica1.setText(hC1);


        //inicializamos la grafica 100 Hz por defecto
        graphView = findViewById(R.id.graficaPpal);
        graphView2 = findViewById(R.id.Fluido);
        imprimeFluido();
        
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
        int i,count = arrayAdapter.getCount()-1;
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
        LineGraphSeries<DataPoint> serie1 ;
        //Añadimos los datos al graph view.
            serie1 = new LineGraphSeries<>(new DataPoint[]{
                    // Añadimos cada punto de los ejes.
                    new DataPoint(0, 0.0),
                    new DataPoint(1, 2.0000000000000018),
                    new DataPoint(2, 3.959999999999997),
                    new DataPoint(3, 5.880800000000008),
                    new DataPoint(4, 7.763184000000001),
                    new DataPoint(5, 9.607920319999996),
                    new DataPoint(6, 11.415761913599997),
                    new DataPoint(7, 13.187446675328008),
                    new DataPoint(8, 14.9236977418214477),
                    new DataPoint(9, 16.62522378698502),
                    new DataPoint(10, 18.29271931124532),
                    new DataPoint(11, 19.926864925020414),
                    new DataPoint(12, 21.528327626520007),
                    new DataPoint(13, 23.0977610739896),
                    new DataPoint(14, 24.63580585250982),
                    new DataPoint(15, 26.14308973545961),
                    new DataPoint(16, 27.620227940750432),
                    new DataPoint(17, 29.06782338193542),
                    new DataPoint(18, 30.486466914296717),
                    new DataPoint(19, 31.876737576010783),
                    new DataPoint(20, 33.23920282449057)
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
            switch (seleccion) {
                case "100":
                    serie1 = new LineGraphSeries<>(new DataPoint[]{
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
                    serie2 = new LineGraphSeries<>(new DataPoint[]{
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
                    break;
                case "500":
                    serie1 = new LineGraphSeries<>(new DataPoint[]{
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
                    serie2 = new LineGraphSeries<>(new DataPoint[]{
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
                    break;
                case "1000":
                    serie1 = new LineGraphSeries<>(new DataPoint[]{
                            // Añadimos cada punto de los ejes.
                            new DataPoint(0, 444.56),
                            new DataPoint(1, 435.67),
                            new DataPoint(2, 426.96),
                            new DataPoint(3, 418.42),
                            new DataPoint(4, 410.05),
                            new DataPoint(5, 401.85),
                            new DataPoint(6, 393.81),
                            new DataPoint(7, 385.93),
                            new DataPoint(8, 378.22),
                            new DataPoint(9, 370.65),
                            new DataPoint(10, 363.24),
                            new DataPoint(11, 355.97),
                            new DataPoint(12, 348.85),
                            new DataPoint(13, 341.88),
                            new DataPoint(14, 335.04),
                            new DataPoint(15, 328.3),
                            new DataPoint(16, 321.77),
                            new DataPoint(17, 315.34),
                            new DataPoint(18, 309.03),
                            new DataPoint(19, 302.85),
                            new DataPoint(20, 296.79)
                    });
                    serie2 = new LineGraphSeries<>(new DataPoint[]{
                            // Añadimos cada punto de los ejes.
                            new DataPoint(0, 36.4),
                            new DataPoint(1, 36.0),
                            new DataPoint(2, 35.6),
                            new DataPoint(3, 35.2),
                            new DataPoint(4, 34.8),
                            new DataPoint(5, 34.4),
                            new DataPoint(6, 34.0),
                            new DataPoint(7, 33.6),
                            new DataPoint(8, 33.2),
                            new DataPoint(9, 32.8),
                            new DataPoint(10, 32.4),
                            new DataPoint(11, 32.0),
                            new DataPoint(12, 31.6),
                            new DataPoint(13, 31.2),
                            new DataPoint(14, 30.8),
                            new DataPoint(15, 30.4),
                            new DataPoint(16, 30.0),
                            new DataPoint(17, 29.6),
                            new DataPoint(18, 29.2),
                            new DataPoint(19, 28.8),
                            new DataPoint(20, 28.4)
                    });
                    break;
                case "5000":
                    serie1 = new LineGraphSeries<>(new DataPoint[]{
                            // Añadimos cada punto de los ejes.
                            new DataPoint(0, 388.99),
                            new DataPoint(1, 381.2),
                            new DataPoint(2, 373.59),
                            new DataPoint(3, 366.11),
                            new DataPoint(4, 358.79),
                            new DataPoint(5, 351.62),
                            new DataPoint(6, 344.58),
                            new DataPoint(7, 337.69),
                            new DataPoint(8, 330.94),
                            new DataPoint(9, 324.32),
                            new DataPoint(10, 317.83),
                            new DataPoint(11, 311.48),
                            new DataPoint(12, 305.25),
                            new DataPoint(13, 299.14),
                            new DataPoint(14, 293.16),
                            new DataPoint(15, 287.3),
                            new DataPoint(16, 281.55),
                            new DataPoint(17, 275.92),
                            new DataPoint(18, 270.4),
                            new DataPoint(19, 264.99),
                            new DataPoint(20, 259.69)
                    });
                    serie2 = new LineGraphSeries<>(new DataPoint[]{
                            // Añadimos cada punto de los ejes.
                            new DataPoint(0, 31.85),
                            new DataPoint(1, 31.5),
                            new DataPoint(2, 31.15),
                            new DataPoint(3, 30.8),
                            new DataPoint(4, 30.45),
                            new DataPoint(5, 30.1),
                            new DataPoint(6, 29.75),
                            new DataPoint(7, 29.4),
                            new DataPoint(8, 29.05),
                            new DataPoint(9, 28.7),
                            new DataPoint(10, 28.35),
                            new DataPoint(11, 28.0),
                            new DataPoint(12, 27.65),
                            new DataPoint(13, 27.3),
                            new DataPoint(14, 26.95),
                            new DataPoint(15, 26.6),
                            new DataPoint(16, 26.25),
                            new DataPoint(17, 25.9),
                            new DataPoint(18, 25.55),
                            new DataPoint(19, 25.2),
                            new DataPoint(20, 24.85)
                    });
                    break;
                case "10000":
                    serie1 = new LineGraphSeries<>(new DataPoint[]{
                            // Añadimos cada punto de los ejes.
                            new DataPoint(0, 333.42),
                            new DataPoint(1, 326.7),
                            new DataPoint(2, 320.22),
                            new DataPoint(3, 313.81),
                            new DataPoint(4, 307.54),
                            new DataPoint(5, 301.39),
                            new DataPoint(6, 295.36),
                            new DataPoint(7, 289.45),
                            new DataPoint(8, 283.66),
                            new DataPoint(9, 277.99),
                            new DataPoint(10, 272.43),
                            new DataPoint(11, 266.98),
                            new DataPoint(12, 261.64),
                            new DataPoint(13, 256.41),
                            new DataPoint(14, 251.28),
                            new DataPoint(15, 246.25),
                            new DataPoint(16, 241.33),
                            new DataPoint(17, 236.5),
                            new DataPoint(18, 231.77),
                            new DataPoint(19, 227.14),
                            new DataPoint(20, 222.59)
                    });
                    serie2 = new LineGraphSeries<>(new DataPoint[]{
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
                    break;
                case "50000":
                    serie1 = new LineGraphSeries<>(new DataPoint[]{
                            // Añadimos cada punto de los ejes.
                            new DataPoint(0, 277.85),
                            new DataPoint(1, 272.29),
                            new DataPoint(2, 266.85),
                            new DataPoint(3, 261.51),
                            new DataPoint(4, 256.28),
                            new DataPoint(5, 251.15),
                            new DataPoint(6, 246.13),
                            new DataPoint(7, 241.21),
                            new DataPoint(8, 236.38),
                            new DataPoint(9, 231.66),
                            new DataPoint(10, 227.02),
                            new DataPoint(11, 222.48),
                            new DataPoint(12, 218.03),
                            new DataPoint(13, 213.67),
                            new DataPoint(14, 209.4),
                            new DataPoint(15, 205.21),
                            new DataPoint(16, 201.11),
                            new DataPoint(17, 197.09),
                            new DataPoint(18, 193.14),
                            new DataPoint(19, 189.28),
                            new DataPoint(20, 185.49)
                    });
                    serie2 = new LineGraphSeries<>(new DataPoint[]{
                            // Añadimos cada punto de los ejes.
                            new DataPoint(0, 22.5),
                            new DataPoint(1, 22.25),
                            new DataPoint(2, 22.0),
                            new DataPoint(3, 21.75),
                            new DataPoint(4, 21.5),
                            new DataPoint(5, 21.25),
                            new DataPoint(6, 21.0),
                            new DataPoint(7, 20.75),
                            new DataPoint(8, 20.5),
                            new DataPoint(9, 20.2),
                            new DataPoint(10, 36.45),
                            new DataPoint(11, 20),
                            new DataPoint(12, 19.75),
                            new DataPoint(13, 19.5),
                            new DataPoint(14, 19.25),
                            new DataPoint(15, 19.0),
                            new DataPoint(16, 18.75),
                            new DataPoint(17, 18.5),
                            new DataPoint(18, 18.25),
                            new DataPoint(19, 18.0),
                            new DataPoint(20, 17.75)
                    });
                    break;
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