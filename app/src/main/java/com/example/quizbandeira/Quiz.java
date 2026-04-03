package com.example.quizbandeira;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {

    private RadioGroup button;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button confirmar;
    private int indiceAtual = 0;
    private int pontos = 1;
    List<Questao> paises = new ArrayList<>();
    Intent it;
    private ImageView imagem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);
        button = findViewById(R.id.radioPrincipal);
        confirmar = findViewById(R.id.responder);
        imagem = findViewById(R.id.imagePrincipal);


        bancoDeDados();
        carregarPergunta();
        button.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {

                if(checkedId!=-1)
                {
                    confirmar.setEnabled(true);
                }
            }
        });


        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarResposta();
            }
        });


    }

    private void bancoDeDados() {


        Collections.addAll(paises,
                new Questao(R.drawable.japao, "Japão", Arrays.asList("Japao", "Estados unidos", "Brasil", "Korea")),
                new Questao(R.drawable.mexico, "México", Arrays.asList("franca", "Korea", "Russia", "México")),
                new Questao(R.drawable.korea, "Korea", Arrays.asList("Japao", "Estados unidos", "Brasil", "Korea")),
                new Questao(R.drawable.canada, "Canada", Arrays.asList("Mexico", "Canada", "França", "Russia")),
                new Questao(R.drawable.franca, "França", Arrays.asList("Brasil", "Canada", "França", "Suiça")),
                new Questao(R.drawable.brasil, "Brasil", Arrays.asList("Estados Unidos", "Canada", "Brasil", "Mexico")),
                new Questao(R.drawable.suica, "Suiça", Arrays.asList("Suiça", "Brasil", "Japão", "Canada")),
                new Questao(R.drawable.portugal, "Portugal", Arrays.asList("Russia", "Canada", "Portugal", "Estados Unidos")),
                new Questao(R.drawable.russia, "Russia", Arrays.asList("Estados Unidos", "Russia", "Brasil", "França")),
                new Questao(R.drawable.estados, "Estados Unidos", Arrays.asList("Canada", "México", "Estados Unidos", "Japão"))
        );
        Collections.shuffle(paises);


    }

    private void carregarPergunta() {
        Questao questaoAtual;
        if (indiceAtual < paises.size()) {
            questaoAtual = paises.get(indiceAtual);
            // Atualiza a imagem (certifique-se de ter a variável e o ID mapeados)
            if (imagem != null) {
                imagem.setImageResource(questaoAtual.getImageId());
            }
            // Atualiza os textos dos botões
            List<String> opcoes = questaoAtual.getQuestoes();
            rb1.setText(opcoes.get(0));
            rb2.setText(opcoes.get(1));
            rb3.setText(opcoes.get(2));
            rb4.setText(opcoes.get(3));
            button.clearCheck();
            confirmar.setEnabled(false);



        } else {

            it = new Intent(Quiz.this, Ranking.class);
            String pontoString=Integer.toString(pontos);
            String jogador=getIntent().getStringExtra("NOME_JOGADOR");
            it.putExtra("NOME_JOGADOR",jogador);
            it.putExtra("pontos",pontoString);
            startActivity(it);
            finish();


        }
    }


    private void validarResposta() {
        int idSelecionado = button.getCheckedRadioButtonId();


        // Pega o texto da opção que o usuário marcou
        RadioButton botaoSelecionado = findViewById(idSelecionado);
        String respostaUsuario = botaoSelecionado.getText().toString();

        // Pega a resposta certa do objeto
        String respostaCerta = paises.get(indiceAtual).getRespostas();

        // Valida
        if (respostaUsuario.equals(respostaCerta)) {

            pontos++;

        }
        indiceAtual++;
        carregarPergunta();


    }









}













