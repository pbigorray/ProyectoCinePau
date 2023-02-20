package com.example.proyectocinepau.model;

import java.io.Serializable;
import java.util.List;

public class ButacasCompradas implements Serializable {
        List<ButacaOcupada> butacas;

        public ButacasCompradas(List<ButacaOcupada> totBUtacas){
            butacas = totBUtacas;
        }

        public List<ButacaOcupada> getButacas() {
            return butacas;
        }

}
