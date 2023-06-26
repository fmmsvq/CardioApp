package com.example.cardioapp.Medico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.example.cardioapp.LoginActivity;
import com.example.cardioapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VistaPacienteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    GraphView graphView;
    Spinner spinner;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_paciente);

        //inicializamos la grafica 500 Hz por defecto
        graphView = findViewById(R.id.grafica500);

        //Botón de volver
        /*if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/
        spinner = findViewById(R.id.spinner);

        //DropDown
        List<String> listaDropdown = new ArrayList<>();
        listaDropdown.add(0, "100");
        listaDropdown.add("500");
        listaDropdown.add("1000");
        listaDropdown.add("5000");
        listaDropdown.add("10000");
        listaDropdown.add("50000");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDropdown);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    /**@param s es la frecuencia*/
    private void creaGrafica(String s) {
        LineGraphSeries<DataPoint> serie1 = null, serie2 = null;
        compruebaVisibles(s);
        //Añadimos los datos al graph view.
        //getDataPointsDeArchivo(s);
        if(s.equals("100")){
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
        }else if(s.equals("500")){
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
        serie2.setColor(Color.RED);
        graphView.addSeries(serie1);
        graphView.addSeries(serie2);

        // Título del graph view.
        graphView.setTitle(s+"Hz");
        // Tamaño de letra del título
        graphView.setTitleTextSize(60);
        // Color del texto del graph view.
        graphView.setTitleColor(R.color.colorPrimaryDark);
        graphView.getGridLabelRenderer().setHorizontalAxisTitle("T");
        graphView.getGridLabelRenderer().setVerticalAxisTitle("R"+s+" y  X"+s);
        graphView.setVisibility(View.VISIBLE);

//        graphView.getGridLabelRenderer().setVerticalLabelsColor(R.color.colorPrimaryDark);
    }

    public void compruebaVisibles(String seleccion){
        //Revisamos los graficos visibles
        int i=0,count = arrayAdapter.getCount()-1;
        graphView.removeAllSeries();
        for( i=0; i<=count; i++){
            //Ponemos invisibles todos menos el actual
            String item = arrayAdapter.getItem(i);
            boolean bool=!item.equals(seleccion);
            //Si no es nuestra seleccion del dropdown marcamos como invisible
            if(bool){
                graphView.setVisibility(View.INVISIBLE);
            }
        }
    }
    /*private void getDataPointsDeArchivo(String s) {
        // Lee los datos del archivo y crea un arreglo de DataPoints
        LineGraphSeries<DataPoint> series1;
        LineGraphSeries<DataPoint> series2;
        try {
            File file = new File(getFilesDir(), "data.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));//C:/Users/FATIMA/StudioProjects/CardioApp/app/src/main/res/data.txt"));
            Path currentRelativePath = Paths.get("");
            String string = currentRelativePath.toAbsolutePath().toString();
            Toast.makeText(this,"Path: " +string, Toast.LENGTH_SHORT).show();

            String linea;
            List<DataPoint> dataPointsX = new ArrayList<>();
            List<DataPoint> dataPointsR = new ArrayList<>();

            while ((linea = reader.readLine()) != null) {
                String[] coordenadas = linea.split(","); // Suponiendo que los valores están separados por comas
                //leemos 100Hz por defecto
                double t = Double.parseDouble(coordenadas[0]);
                double r = Double.parseDouble(coordenadas[1]);
                double x = Double.parseDouble(coordenadas[2]);
                if(Objects.equals(s, "500")){
                    t = Double.parseDouble(coordenadas[0]);
                    r = Double.parseDouble(coordenadas[3]);
                    x = Double.parseDouble(coordenadas[4]);
                }else if(Objects.equals(s, "1000")){
                    t = Double.parseDouble(coordenadas[0]);
                    r = Double.parseDouble(coordenadas[5]);
                    x = Double.parseDouble(coordenadas[6]);
                }else if(Objects.equals(s, "5000")){
                    t = Double.parseDouble(coordenadas[0]);
                    r = Double.parseDouble(coordenadas[7]);
                    x = Double.parseDouble(coordenadas[8]);
                }else if(Objects.equals(s, "10000")){
                    t = Double.parseDouble(coordenadas[0]);
                    r = Double.parseDouble(coordenadas[9]);
                    x = Double.parseDouble(coordenadas[10]);
                }else if(Objects.equals(s, "50000")){
                    t = Double.parseDouble(coordenadas[0]);
                    r = Double.parseDouble(coordenadas[11]);
                    x = Double.parseDouble(coordenadas[12]);
                }

                DataPoint dataPointX = new DataPoint(t, r);
                DataPoint dataPointR = new DataPoint(t, x);
                dataPointsX.add(dataPointX);
                dataPointsR.add(dataPointR);


            }
            series1 = new LineGraphSeries<>(dataPointsX.toArray(new DataPoint[0]));
            series2 = new LineGraphSeries<>(dataPointsR.toArray(new DataPoint[0]));
            // Añadimos la lista de puntos al gráfico
            graphView.addSeries(series1);
            graphView.addSeries(series2);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*FrameLayout para que varias vistas ocupen un mismo lugar, en este caso varias graficas. Podemos hacer que
     solo una sea visible, o superponerlas.Para modificar la visibilidad de un elemento utilizaremos la propiedad visibility. */
        /**Callback para invocarlo cuando se selecciona un elemento del AdapterView.**/
        public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {
            // Item seleccionado. Recuperar el elemento seleccionado con parent.getItemAtPosition(pos)
                String item = parent.getItemAtPosition(posicion).toString();
                String selectedOption = parent.getItemAtPosition(posicion).toString();
            switch (selectedOption) {
                case "100":
                    mostrarGraphV1();
                    break;
                case "500": //if(arrayAdapter.getItem(posicion)=="1000"){
                    mostrarGraphV2();
                    break;
                case "1000":
                    mostrarGraphV3();
                    break;
                case "5000":
                    mostrarGraphV4();
                    break;
                case "10000":
                    mostrarGraphV5();
                    break;
                case "50000":
                    mostrarGraphV6();
                    break;
            }
                //inflate grafica
                creaGrafica(arrayAdapter.getItem(posicion));
                //TODO CAMBIAR POR IMPRIMIR EL GRAPHVIEW QUE CORRESPONDE
                Toast.makeText(parent.getContext(),"Seleccionado: " +item, Toast.LENGTH_SHORT).show();
        }

    private void mostrarGraphV6() {
        GraphView graphV = findViewById(R.id.grafica50000);
        graphV.setVisibility(View.VISIBLE);

    }
    private void mostrarGraphV5() {
        GraphView graphV = findViewById(R.id.grafica10000);
        graphV.setVisibility(View.VISIBLE);
    }
    private void mostrarGraphV4() {
        GraphView graphV = findViewById(R.id.grafica5000);
        graphV.setVisibility(View.VISIBLE);
    }
    private void mostrarGraphV3() {
        GraphView graphV = findViewById(R.id.grafica1000);
        graphV.setVisibility(View.VISIBLE);
    }
    private void mostrarGraphV2() {
        GraphView graphV = findViewById(R.id.grafica500);
        graphV.setVisibility(View.VISIBLE);
    }
    private void mostrarGraphV1() {
        GraphView graphV = findViewById(R.id.grafica500);
        graphV.setVisibility(View.VISIBLE);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        }


    public void onClickVolver(View view) {
        startActivity(new Intent(VistaPacienteActivity.this, PrincipalActivity.class));
    }

}