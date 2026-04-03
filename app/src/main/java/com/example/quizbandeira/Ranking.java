package com.example.quizbandeira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ranking extends AppCompatActivity {


    private TextView ranking;
    private TextView score;
    private Intent it;
    private Button tentar,telaPrincipal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ranking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        ranking=findViewById(R.id.ranking);
        score=findViewById(R.id.score);
        tentar=findViewById(R.id.tentar);
        telaPrincipal=findViewById(R.id.telaPrincipal);

        it=getIntent();
        String jogador=it.getStringExtra("NOME_JOGADOR");
        String pontos=it.getStringExtra("pontos");
        ranking.setText("RANKING DE  "+jogador.toUpperCase());
        score.setText(pontos);

        tentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                it=new Intent(Ranking.this, Quiz.class);
                String jogador=getIntent().getStringExtra("NOME_JOGADOR");
                it.putExtra("NOME_JOGADOR",jogador);
                startActivity(it);
                finish();




            }
        });

        telaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it=new Intent(Ranking.this,MainActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
                finish();


            }
        });



















    }
}