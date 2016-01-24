package unittesting.berekeningen.loon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import unittesting.dao.BrutoNettoBerekeningResultaat;
import unittesting.dao.BrutoNettoPersister;
import unittesting.domein.ContractType;
import unittesting.domein.LoonContext;
import unittesting.domein.LoonType;
import unittesting.domein.SchaalWaarde;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Deze klasse berekend het Netto-loon voor een persoon.
 * Dit is een beperkte implementatie voor het nut van de oefening
 * Uitleg van deze berekening: http://www.loonwijzer.be/main/arbeidsrecht/loon-en-werk/brutonetto
 *
 * Buiten beschouwing: RSZ werkbonus, Bijzondere bijdragen, bijzondere kortingen
 */
public class BrutoNettoBerekening {
    private BrutoNettoPersister dao = new BrutoNettoPersister();

    public Double berekenNetto(Double brutoloon, ContractType contractType, LoonContext loonContext, LoonType loonType) {
        Double rszBijdrage = bepaalRszBijdrage(brutoloon, contractType);

        Double belastbaarLoon = brutoloon - rszBijdrage;

        Double bedrijfsvoorheffing = berekenBedrijfsvoorheffing(belastbaarLoon, loonType, loonContext);
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

    private Double bepaalRszBijdrage(Double brutoloon, ContractType contractType) {
        Double basisVoorRsz = null;

        switch (contractType) {
            case BEDIENDE:
                basisVoorRsz = brutoloon;
                break;
            case ARBEIDER:
                basisVoorRsz = 1.08 * brutoloon;
                break;
        }

        return basisVoorRsz * 0.1307;
    }

    private Double berekenBedrijfsvoorheffing(Double belastbaarLoon, LoonType loonType, LoonContext context) {
        int schaal = bepaalBedrijfsvoorheffingSchaal(context);

        List<SchaalWaarde> schaalWaarden = haalSchaalwaardenOp(schaal);

        SchaalWaarde vorigeWaarde = new SchaalWaarde(0,0,0,0);
        for (SchaalWaarde waarde : schaalWaarden) {
            if (waarde.getAmount() > belastbaarLoon) {
                switch (loonType) {
                    case WERKNEMER:
                        return vorigeWaarde.getWerknemer();
                    case BEDRIJFSLEIDER:
                        return vorigeWaarde.getBedrijfsleider();
                    case GEPENSIONEERDE:
                        return vorigeWaarde.getPensioen();
                }
            }
            vorigeWaarde = waarde;
        }
        throw new IllegalArgumentException("Bedragen hoger dan de standaardwaarden in de schaal zijn niet ondersteund.");
    }

    private int bepaalBedrijfsvoorheffingSchaal(LoonContext loonContext) {
        switch (loonContext) {
            case ALLEENSTAANDE:
                return 1;
            case EEN_VERDIENER_GEZIN:
                return 2;
            case TWEE_VERDIENER_GEZIN:
                return 1;
            case NIET_INWONERS:
                return 3;
        }
        throw new IllegalArgumentException("De gegeven LoonContext kon niet worden afgehandeld.");
    }

    private List<SchaalWaarde> haalSchaalwaardenOp(int schaalNr) {
        HttpGet httpGet = new HttpGet("http://tomcools.cloudapp.net:9999/heffing?schaal-nr=" + schaalNr);

        String entityResponse;
        try {
            HttpResponse response = HttpClientBuilder.create().build().execute(httpGet);
            entityResponse = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't retrieve the data from the backend service.", e);
        }

        Type type = new TypeToken<List<SchaalWaarde>>() {
        }.getType();
        return new Gson().fromJson(entityResponse, type);
    }
}
