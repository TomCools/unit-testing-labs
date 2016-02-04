package unittesting.berekeningen.loon;

import unittesting.domein.LoonContext;
import unittesting.domein.LoonType;
import unittesting.domein.SchaalWaarde;

import java.util.List;

public class BedrijfsvoorheffingBerekening {
    private BedrijfsvoorheffingSchaalService schaalService = new BedrijfsvoorheffingSchaalService();

    public Double berekenBedrijfsvoorheffing(Double belastbaarLoon, LoonType loonType, LoonContext context) {
        int schaal = bepaalBedrijfsvoorheffingSchaal(context);

        List<SchaalWaarde> schaalWaarden = schaalService.haalSchaalwaardenOp(schaal);

        SchaalWaarde waarde = new SchaalWaarde(0, 0, 0, 0);
        int i = 0;
        do {
            waarde = schaalWaarden.get(i);
            i++;
        } while (waarde.getAmount() > belastbaarLoon && i <= schaalWaarden.size());

        switch (loonType) {
            case WERKNEMER:
                return waarde.getWerknemer();
            case BEDRIJFSLEIDER:
                return waarde.getBedrijfsleider();
            case GEPENSIONEERDE:
                return waarde.getPensioen();
        }
//Bedragen onder de gegeven schalen worden niet belast
        return 0D;
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
            default:
                throw new IllegalArgumentException("De gegeven LoonContext kon niet worden afgehandeld.");
        }
    }
}
