package unittesting.berekeningen.loon;

import unittesting.dao.BrutoNettoBerekeningResultaat;
import unittesting.dao.BrutoNettoPersister;
import unittesting.domein.ContractType;
import unittesting.domein.LoonContext;
import unittesting.domein.LoonType;

/**
 * Deze klasse berekend het Netto-loon voor een persoon.
 * Dit is een beperkte implementatie voor het nut van de oefening
 * Uitleg van deze berekening: http://www.loonwijzer.be/main/arbeidsrecht/loon-en-werk/brutonetto
 * <p/>
 * Buiten beschouwing: RSZ werkbonus, Bijzondere bijdragen, bijzondere kortingen
 */
public class BrutoNettoBerekening {
    private BrutoNettoPersister dao = new BrutoNettoPersister();
    private BedrijfsvoorheffingBerekening bedrijfsvoorheffingBerekening = new BedrijfsvoorheffingBerekening();
    private RszBerekening rszBerekening = new RszBerekening();

    public Double berekenNetto(Double brutoloon, ContractType contractType, LoonContext loonContext, LoonType loonType) {
        Double rszBijdrage = rszBerekening.bepaalRszBijdrage(brutoloon, contractType);

        Double belastbaarLoon = brutoloon - rszBijdrage;

        Double bedrijfsvoorheffing = bedrijfsvoorheffingBerekening.berekenBedrijfsvoorheffing(belastbaarLoon, loonType, loonContext);
        Double resultaat = belastbaarLoon - bedrijfsvoorheffing;

        BrutoNettoBerekeningResultaat berekeningsOverzicht = BrutoNettoBerekeningResultaat.builder()
                .brutoLoon(brutoloon)
                .contractType(contractType)
                .loonContext(loonContext)
                .loonType(loonType)
                .rszBijdrage(rszBijdrage)
                .belastbaarLoon(belastbaarLoon)
                .bedrijfsvoorheffing(bedrijfsvoorheffing)
                .nettoLoon(resultaat)
                .build();

        dao.slaBerekeningOp(berekeningsOverzicht);

        return resultaat;
    }


}
