package org.ing.hackaton.psdating.service;

import org.ing.hackaton.psdating.domain.Recommendation;
import org.ing.hackaton.psdating.util.HttpUtil;
import org.ing.hackaton.psdating.util.StringUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public final class GoogleSearchService {

    private static final String BASE_URL = "https://www.googleapis.com/customsearch/v1";
    private static final String SEARCH_KEY = "AIzaSyBmEvR80QoRWa2lNgo7hs9BPlnXl62mHsE";
    private static final String ENGINE_NAME = "007675409212950568648:sgbh0yztsva";
    private static final String ENGINE_URL = String.format("%s?key=%s&cx=%s&q=", BASE_URL, SEARCH_KEY, ENGINE_NAME);

    private static final Pattern PATTERN_ZIPCITY = Pattern.compile("^(\\d\\d\\d\\d)[\\s]?(..) (.*)$");
    private static final Pattern PATTERN_ZIP = Pattern.compile("^(\\d\\d\\d\\d)[\\s]?(..)$");

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
            final List<JSONObject> options = gatherFieldOptions(item.get("pagemap"));
            for (final JSONObject option : options) {
                if (telephone == null && option.containsKey("telephone")) {
                    telephone = (String) option.get("telephone");
                }
                if (streetaddress == null && option.containsKey("streetaddress")) {
                    streetaddress = (String) option.get("streetaddress");
                }
                if (postalcode == null && option.containsKey("postalcode")) {
                    postalcode = (String) option.get("postalcode");
                    Matcher matcher = PATTERN_ZIPCITY.matcher(postalcode);
                    if (matcher.find()) {
                        postalcode = matcher.group(1) + " " + matcher.group(2);
                        addresslocality = StringUtils.nonUpperCase(matcher.group(3));
                    } else {
                        matcher = PATTERN_ZIP.matcher(postalcode);
                        if (matcher.find()) {
                            postalcode = matcher.group(1) + " " + matcher.group(2);
                        }
                    }
                }
                if (addresslocality == null && option.containsKey("addresslocality")) {
                    addresslocality = StringUtils.nonUpperCase((String) option.get("addresslocality"));
                }
                if (url == null && option.containsKey("url")) {
                    url = (String) option.get("url");
                }
                if (image == null && option.containsKey("image")) {
                    image = (String) option.get("image");
                }
                if (image == null && option.containsKey("src")) {
                    image = (String) option.get("src");
                }
            }
        }
        final String zipcity = addresslocality == null ? postalcode : postalcode == null ? addresslocality : postalcode + " " + addresslocality;
        return Recommendation.builder().phonenumber(telephone).city(addresslocality).zipcode(postalcode).zipCity(zipcity).companyName(companyName).address(streetaddress).logoUrl(image).url(url).build();
    }

    private List<JSONObject> gatherFieldOptions(final Object item) {
        final List<JSONObject> options = new ArrayList<>();
        if (item instanceof JSONObject) {
            putInList(options, item, "hardwarestore");
            putInList(options, item, "postaladdress");
            putInList(options, item, "cse_image");
//        } else if (item instanceof JSONArray) {
//            for (final Object object : (JSONArray) item) {
//                options.addAll(gatherFieldOptions(object));
//            }
        }
        return options;
    }

    private void putInList(final List<JSONObject> options, final Object item, final String key) {
        final JSONArray values = (JSONArray) ((JSONObject) item).get(key);
        if (values != null) {
            for (final Object object : values) {
                options.add((JSONObject) object);
            }
        }
    }
}
