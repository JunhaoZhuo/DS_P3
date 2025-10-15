package model;

import java.util.Date;

public class Preu {
    private Double quantitat;
    private String moneda;

    public Preu(Double quantitat, String moneda) {
        this.quantitat = quantitat;
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return quantitat + " " + moneda;
    }
}
