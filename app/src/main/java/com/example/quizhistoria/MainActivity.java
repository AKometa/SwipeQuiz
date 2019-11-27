package com.example.quizhistoria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizhistoria.R;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout tela;
    private TextView pergunta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tela = findViewById(R.id.telaToda);
        pergunta = findViewById(R.id.tvPergunta);
        final Intent intent = new Intent(this, telaFinal.class);
        Dados.populaMatriz();
        Dados.populaControle();
        //startActivity(intent);
        //Exibe oque há no array indice '0'
        pergunta.setText(Pergunta.retornaArray().get(Pergunta.contador));


        tela.setOnTouchListener(new OnSwipeTouchListener(this) {

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if (Dados.controleRespostas[Pergunta.contador] == false) {
                    Dados.contadorSwaipe++;
                    Dados.controleRespostas[Pergunta.contador] = true;
                }
                Dados.insereResposta(Pergunta.contador, false);
                if (Dados.contadorSwaipe == 5) {
                    startActivity(intent);
                } else {
                    onSwipeRight();
                }


            }


            @Override
            public void onSwipeTop() {
                super.onSwipeBottom();
                if (Dados.controleRespostas[Pergunta.contador] == false) {
                    Dados.contadorSwaipe++;
                    Dados.controleRespostas[Pergunta.contador] = true;
                }
                Dados.insereResposta(Pergunta.contador, true);
                if (Dados.contadorSwaipe == 5) {
                    startActivity(intent);
                } else {
                    onSwipeRight();
                }


            }
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if (Pergunta.contador == 0) {
                    Pergunta.contador = Pergunta.retornaTamanhoArray();
                    pergunta.setText(Pergunta.retornaArray().get(Pergunta.contador));

                } else {
                    Pergunta.contador = (Pergunta.contador - 1);
                    pergunta.setText(Pergunta.retornaArray().get(Pergunta.contador));
                }

            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                if (Pergunta.contador == Pergunta.retornaTamanhoArray()) {
                    Pergunta.contador = 0;
                    pergunta.setText(Pergunta.retornaArray().get(Pergunta.contador));

                } else {
                    Pergunta.contador = (Pergunta.contador + 1);
                    pergunta.setText(Pergunta.retornaArray().get(Pergunta.contador));
                }

                /*if(Pergunta.contador < Pergunta.retornaTamanhoArray()){
                     pergunta.setText(Pergunta.retornaArray().get(Pergunta.contador));
                     Pergunta.contador = (Pergunta.contador+1);
                }else {
                    pergunta.setText(Pergunta.retornaArray().get(Pergunta.contador));
                    Pergunta.contador = 0;
                }*/

            }
        });


    }


}
