package com.botola.apicmi.utils.accountSolde;

public enum Solde {
    DEUX_CENTS(200),
    CINQ_MILLE(5000),
    VINGT_MILLE(20000);

    private final int valeur;

    Solde(int v){
        this.valeur=v;
    }
    public int getSolde(){
        return this.valeur;
    }

}
