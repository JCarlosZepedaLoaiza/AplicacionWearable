package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Result.Result;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    int cont =0;
    int cout2=0;
    private TextView txtTexto;
    private Button btn1;
    private Button btn2;
    private Button btn3;

    private ActivityMainBinding binding;
    private String url = "";
    List<pelis> ListaDatos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btn1=(Button) findViewById(R.id.btnpelis);
        btn2 =(Button) findViewById(R.id.btnsport);
        btn3 =(Button) findViewById(R.id.btnRegresar);
        btn3.setVisibility(View.GONE);

    }
    public void btn1(View View){
        url= "https://api.nytimes.com/svc/movies/v2/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        txtTexto =(TextView) findViewById(R.id.txtTexto);
        btn2.setVisibility(View.GONE);
        btn1.setText("Ver Más");
        btn3.setVisibility(View.VISIBLE);
        DatosServices Services =  retrofit.create(DatosServices.class);
        Call<pelis> pel = Services.getDatos();
        pel.enqueue(new Callback<pelis>() {
            @Override
            public void onResponse(Call<pelis> call, Response<pelis> response) {
                pelis p = response.body();
                Result Ro = new Result();
                if (p.getResults().size()==cont){
                    cont=0;

                }else{
                    Ro = p.getResults().get(cont);
                    cont++;
                    String txt1 = Ro.getDisplayTitle() + "\n"+ Ro.getSummaryShort();
                    txtTexto.setText(txt1);
                }



            }

            @Override
            public void onFailure(Call<pelis> call, Throwable t) {
                txtTexto.setText(t.getMessage());
            }
        });

    }
    public void btn2(View View){
        url="https://www.freetogame.com/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        txtTexto =(TextView) findViewById(R.id.txtTexto);
        btn1.setVisibility(View.GONE);
        btn2.setText("Ver Más");
        btn3.setVisibility(View.VISIBLE);

        DatosServices Services =  retrofit.create(DatosServices.class);
        Call<List<sport>> pel = Services.getDatos2();
        pel.enqueue(new Callback <List<sport>>() {
            @Override
            public void onResponse(Call<List<sport>> call, Response<List<sport>> response) {
                List<sport>  s;
                s = response.body();

                cont++;
                String txt1 = s.get(cout2).getTitle() + "       "+ s.get(cout2).getShortDescription();
                txtTexto.setText(txt1);
                cout2++;
            }

            @Override
            public void onFailure(Call<List<sport>> call, Throwable t) {

            }


        });
    }
    public void btn3(View view){
        btn2.setVisibility(View.VISIBLE);
        btn1.setVisibility(View.VISIBLE);
        btn2.setText("Juegos");
        btn1.setText("Peliculas");
        txtTexto.setText("Seleccionar");
        btn3.setVisibility(View.GONE);

    }

}