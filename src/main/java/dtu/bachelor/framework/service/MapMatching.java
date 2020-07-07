package dtu.bachelor.framework.service;

import dtu.bachelor.framework.model.MapReference;
import dtu.bachelor.framework.model.Measurement;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapMatching {

    public static String getUrl = "http://127.0.0.1:5000/match/v1/driving/";
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    public static List<MapReference> referenceList = new ArrayList<>();

    public static void match(List<Measurement> measurementList){
        String addToUrl;
        int k = 0;
        for (Measurement measurement : measurementList) {
            k += 1;
            addToUrl = measurement.getLatitude() + ",";
            addToUrl = addToUrl + measurement.getLongitude();
            getUrl = getUrl + addToUrl + ";";
            if (k % 100 == 0) {
                getUrl = getUrl.substring(0, getUrl.length() - 1);

                referenceList.addAll(Objects.requireNonNull(sendRequest(getUrl)));
                getUrl = "http://127.0.0.1:5000/match/v1/driving/";
                k = 0;
            }
        }
        getUrl = getUrl.substring(0,getUrl.length()-1);

        referenceList.addAll(Objects.requireNonNull(sendRequest(getUrl)));
        getUrl = "http://127.0.0.1:5000/match/v1/driving/";

        for (int i = 0 ; i < measurementList.size() ; i++){
            assert referenceList != null;
            MapReference referenced = referenceList.get(i);
            measurementList.get(i).setMapReference(referenced);
        }
    }

    private static List<MapReference> sendRequest(String getUrl) {
        List<MapReference> referenceList = new ArrayList<>();

        HttpGet request = new HttpGet(getUrl);

        try (CloseableHttpResponse response = httpClient.execute(request)){
            // Get HttpResponse Status
            HttpEntity entity = response.getEntity();
            // return it as a String
            String result = EntityUtils.toString(entity);

            JSONParser parse = new JSONParser();
            JSONObject jsonObject = (JSONObject)parse.parse(result);
            JSONArray jsonArray = (JSONArray) jsonObject.get("tracepoints");

            for (Object o : jsonArray) {
                JSONObject jsonObject1 = (JSONObject) o;
                JSONArray jsonArray1 = (JSONArray) jsonObject1.get("location");
                MapReference reference = new MapReference();
                reference.setMapmatched_longitude((Double) jsonArray1.get(1));
                reference.setMapmatched_latitude((Double) jsonArray1.get(0));
                referenceList.add(reference);
            }
            return referenceList;
        } catch (Exception e){
            System.out.println(e);
            getUrl = "http://127.0.0.1:5000/match/v1/driving/";
        }
        return null;
    }
}