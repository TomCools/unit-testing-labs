package unittesting.berekeningen.loon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import unittesting.domein.SchaalWaarde;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class BedrijfsvoorheffingSchaalService {
    public List<SchaalWaarde> haalSchaalwaardenOp(int schaalNr) {
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
        List<SchaalWaarde> schaalwaarden = new Gson().fromJson(entityResponse, type);
        schaalwaarden.add(0, new SchaalWaarde(0, 0, 0, 0));

        return schaalwaarden;
    }


}
