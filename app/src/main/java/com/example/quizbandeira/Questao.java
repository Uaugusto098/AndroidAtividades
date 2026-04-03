package com.example.quizbandeira;

import java.util.List;

public class Questao {

    private int imageId;
    private String respostas;
    private List<String> questoes;



    public Questao(int imageId,String respostas,List<String> questoes)
    {
        this.imageId=imageId;
        this.respostas=respostas;
        this.questoes=questoes;

    }


    public int getImageId()
    {
        return this.imageId;

    }

    public String getRespostas()
    {
        return this.respostas;

    }


    public List<String> getQuestoes()
    {
        return this.questoes;


    }












}
