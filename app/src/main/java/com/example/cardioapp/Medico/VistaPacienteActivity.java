package com.example.cardioapp.Medico;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardioapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
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
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
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
        //Añadimos los datos al graph view.
        getDataPointsDeArchivo(s);
        /*LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
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
        });*/

        // Título del graph view.
        graphView.setTitle(s+"Hz");

        // Color del texto del graph view.
        graphView.setTitleColor(R.color.colorPrimaryDark);

        // Tamaño de letra del título
        graphView.setTitleTextSize(18);
    }
    private void getDataPointsDeArchivo(String s) {
        // Lee los datos del archivo y crea un arreglo de DataPoints
        LineGraphSeries<DataPoint> series1;
        LineGraphSeries<DataPoint> series2;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/data.txt"));//C:/Users/FATIMA/StudioProjects/CardioApp/app/src/main/res/data.txt"));
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
                }/*else if(s=="Fluido"){
                    t = Double.parseDouble(coordenadas[0]);
                    fluido = Double.parseDouble(coordenadas[13]);
                }*/

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


    }

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




}