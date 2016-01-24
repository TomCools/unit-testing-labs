package unittesting.dao;

import unittesting.berekeningen.loon.BrutoNettoBerekening;

public class BrutoNettoPersister {
    public void slaBerekeningOp(BrutoNettoBerekeningResultaat resultaat) {
        //emuleert opslaan in een trage database

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
