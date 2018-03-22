package org.ing.hackaton.psdating.service;

import org.ing.hackaton.psdating.domain.Recommendation;
import org.ing.hackaton.psdating.util.HttpUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Repository
public final class GoogleSearchService {

    private static final String BASE_URL = "https://www.googleapis.com/customsearch/v1";
    private static final String SEARCH_KEY = "AIzaSyBmEvR80QoRWa2lNgo7hs9BPlnXl62mHsE";
    private static final String ENGINE_NAME = "007675409212950568648:sgbh0yztsva";
    private static final String ENGINE_URL = String.format("%s?key=%s&cx=%s&q=", BASE_URL, SEARCH_KEY, ENGINE_NAME);

    public final Recommendation collectInfoOnCompany(final String companyName) throws IOException, ParseException {
        final JSONObject response = HttpUtil.executeRequestForJsonObject(HttpMethod.GET, ENGINE_URL + companyName, Collections.emptyMap(), Collections.emptyMap());
        final JSONArray items = (JSONArray) response.get("items");

        String telephone = null;
        String streetaddress = null;
        String postalcode = null;
        String addresslocality = null;
        String url = null;
        String image = null;

        final Iterator i = items.iterator();

        while (i.hasNext() && (telephone == null || streetaddress == null || postalcode == null || addresslocality == null || url == null || image == null)) {
            final JSONObject item = (JSONObject) i.next();
            final List<JSONObject> options = gatherFieldOptions((JSONObject) item.get("pagemap"));
            for (final JSONObject option : options) {
                if (telephone == null && option.containsKey("telephone")) {
                    telephone = (String) option.get("telephone");
                }
                if (streetaddress == null && option.containsKey("streetaddress")) {
                    streetaddress = (String) option.get("streetaddress");
                }
                if (postalcode == null && option.containsKey("postalcode")) {
                    postalcode = (String) option.get("postalcode");
                }
                if (addresslocality == null && option.containsKey("addresslocality")) {
                    addresslocality = (String) option.get("addresslocality");
                }
                if (url == null && option.containsKey("url")) {
                    url = (String) option.get("url");
                }
                if (image == null && option.containsKey("image")) {
                    image = (String) option.get("image");
                }
            }
        }
        final String zipcity = addresslocality == null ? postalcode == null ? null : postalcode : postalcode == null ? addresslocality : postalcode + " " + addresslocality;
        return Recommendation.builder().phonenumber(telephone).zipCity(zipcity).companyName(companyName).address(streetaddress).logoUrl(image).url(url).build();
    }

    private List<JSONObject> gatherFieldOptions(final JSONObject item) {
        final List<JSONObject> options = new ArrayList<>();
        if (item == null) {
            return options;
        }

        final JSONArray hardwarestores = (JSONArray) item.get("hardwarestore");
        if (hardwarestores != null) {
            for (final Object object : hardwarestores) {
                options.add((JSONObject) object);
            }
        }

        final JSONArray postaladdresses = (JSONArray) item.get("postaladdress");
        if (postaladdresses != null) {
            for (final Object object : postaladdresses) {
                options.add((JSONObject) object);
            }
        }

        return options;
    }
}
