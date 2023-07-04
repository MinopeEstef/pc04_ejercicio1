package com.example.pc04_miope;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pc04_miope.entity.JokeApiResponse;
import com.example.pc04_miope.service.JokeApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Random;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private RadioGroup categoryRadioGroup;
    private RadioGroup languageRadioGroup;
    private Button getJokeButton;
    private JokeApiService jokeApiService;
    private Gson gson;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        categoryRadioGroup = findViewById(R.id.categoryRadioGroup);
        languageRadioGroup = findViewById(R.id.languageRadioGroup);
        getJokeButton = findViewById(R.id.getJokeButton);

         gson = new GsonBuilder().create();

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient httpClient = httpClientBuilder.build();

         retrofit = new Retrofit.Builder()
                .baseUrl("https://jokeapi.dev/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

         jokeApiService = retrofit.create(JokeApiService.class);
         getJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoke();
            }
        });
    }

    private void getJoke() {
        gson = new GsonBuilder().create();
        // Obtener la categoría seleccionada
        RadioButton selectedCategoryRadioButton = findViewById(categoryRadioGroup.getCheckedRadioButtonId());
        String category = selectedCategoryRadioButton.getText().toString().toLowerCase();
        if(category.equals("programacion")){
            category = "Programming";
        }else{
            String[] categories = getResources().getStringArray(R.array.categories_array);
            Random random = new Random();
            category = categories[random.nextInt(categories.length - 1) + 1];
        }
        // Obtener el idioma seleccionado
        RadioButton selectedLanguageRadioButton = findViewById(languageRadioGroup.getCheckedRadioButtonId());
        String language = selectedLanguageRadioButton.getText().toString().toLowerCase();
        if(language.equals("ingles")){
            language = "en";
        }else{
            language = "es";
        }

        // Realizar la solicitud HTTP al endpoint del API
        Call<JokeApiResponse> call = jokeApiService.getJoke(category, language);

        call.enqueue(new Callback<JokeApiResponse>() {
            @Override
            public void onResponse(Call<JokeApiResponse> call, Response<JokeApiResponse> response) {
                if (response.isSuccessful()) {
                    JokeApiResponse jokeApiResponse = response.body();
                    Log.d("category: ",jokeApiResponse.toString() );

                    if (jokeApiResponse != null) {
                        String jokeJson = gson.toJson(jokeApiResponse);
                        String formattedJson = formatJson(jokeJson);
                        // Mostrar el chiste y la información adicional en el TextView
                        resultTextView.setText(formattedJson);
                    }
                } else {
                    // Mostrar un mensaje de error si la solicitud no fue exitosa
                    resultTextView.setText("Error en la solicitud");
                }
            }

            @Override
            public void onFailure(Call<JokeApiResponse> call, Throwable t) {
                // Mostrar un mensaje de error si la solicitud falló
                resultTextView.setText("Error en la solicitud: " + t.getMessage());
            }
        });
    }
    private String formatJson(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        return gson.toJson(jsonParser.parse(json));
    }
}