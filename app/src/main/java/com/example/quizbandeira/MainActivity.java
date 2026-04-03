package com.example.quizbandeira;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private Button botaoquiz,sair;

    Intent it;

    private TextInputEditText input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        botaoquiz=findViewById(R.id.botaoQuiz);
        sair=findViewById(R.id.botaoSair);
        input=findViewById(R.id.input);



        botaoquiz.setEnabled(false);

        input.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String textDigit=s.toString().trim();
                if(textDigit.length()>2)
                    botaoquiz.setEnabled(true);

                else
                    botaoquiz.setEnabled(false);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


            botaoquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // CORREÇÃO: Pegamos o texto EXATAMENTE na hora do clique
                String nomeJogador = input.getText().toString().trim();
                it = new Intent(MainActivity.this, Quiz.class);
                // BÔNUS: Já coloquei o Intent extra para mandar o nome para a tela do Quiz!
                it.putExtra("NOME_JOGADOR", nomeJogador);
                startActivity(it);


            }

        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();

            }
        });



















    }
}