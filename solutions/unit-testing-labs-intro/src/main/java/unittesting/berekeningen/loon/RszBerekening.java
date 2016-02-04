package unittesting.berekeningen.loon;

import unittesting.domein.ContractType;

public class RszBerekening {
    public Double bepaalRszBijdrage(Double brutoloon, ContractType contractType) {
        Double basisVoorRsz = null;

        switch (contractType) {
            case BEDIENDE:
                basisVoorRsz = brutoloon;
                break;
            case ARBEIDER:
                basisVoorRsz = 1.08 * brutoloon;
                break;
        }

        return rondAfOpTweeCijfers(basisVoorRsz * 0.1307);
    }

    //Kan later afgezonderd worden in aparte klasse
    private double rondAfOpTweeCijfers(Double bedrag) {
        return (double) Math.round(bedrag * 100) / 100;
    }

}
