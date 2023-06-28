package com.example.teste13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class TelaMoeda extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorListener;

    private TextView moeda1;
    private TextView moeda2;
    private TextView moeda3;
    private TextView moeda4;
    private TextView moeda5;

    private String name ="";
    private String bid ="";
    private String high ="";
    private String low ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_moeda);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        moeda1 = findViewById(R.id.MOEDA1);
        moeda2 = findViewById(R.id.MOEDA2);
        moeda3 = findViewById(R.id.MOEDA3);
        moeda4 = findViewById(R.id.MOEDA4);
        moeda5 = findViewById(R.id.MOEDA5);

        sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if(sensorEvent.values[0] < sensor.getMaximumRange()){

                    fazAPI();

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

        };

        start();

    }

    public void start(){

        sensorManager.registerListener(sensorListener, sensor,200*1000);

    }public void stop(){

        sensorManager.unregisterListener(sensorListener);

    }

    @Override
    protected void onPause(){

        stop();
        super.onPause();

    }

    @Override
    protected void onResume(){

        start();
        super.onResume();

    }

    public void voltarTelaInicial(View v){

        Intent voltar = new Intent(this, MainActivity.class);
        startActivity(voltar);

    }

    public String montaString(String nome, String cotacao, String max, String min){

        return "Nome: " + nome + "\n" + "Cotacao: " + cotacao + "\n" + "Max: " + max + "\n"
                + "Min: " + min;

    }

    public void requisicao( TextView moeda, String url, String cd){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject temp = response.getJSONObject(cd);
                            name = temp.getString("name");
                            bid = temp.getString("bid");
                            high = temp.getString("high");
                            low = temp.getString("low");
                            moeda.setText(montaString(name, bid, high, low));
                        } catch (Exception ignored) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

    }

    public void fazAPI(){

        requisicao(moeda1 ,"https://economia.awesomeapi.com.br/json/last/USD-BRL","USDBRL" );
        requisicao(moeda2,"https://economia.awesomeapi.com.br/json/last/BTC-BRL", "BTCBRL");
        requisicao(moeda3,"https://economia.awesomeapi.com.br/json/last/EUR-BRL", "EURBRL");
        requisicao(moeda4,"https://economia.awesomeapi.com.br/json/last/GBP-BRL", "GBPBRL");
        requisicao(moeda5,"https://economia.awesomeapi.com.br/json/last/AUD-BRL", "AUDBRL");

    }

}