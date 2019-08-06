package com.spoontrack.core.io;

import com.spoontrack.core.entity.Pub;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private static final String BASE_URL = "https://www.jdwetherspoon.com/pubs/all-pubs";
    private static final Request request = new Request.Builder().url(BASE_URL).get().build();

    private final List<Pub> pubs = new ArrayList<>(1000);

    public RequestHandler() {
        try {
            Document doc = Jsoup.parse(new OkHttpClient().newCall(request).execute().body().string());

            // Retrieving country wide elements.
            Elements countries = doc.getElementsByClass("advanced-listing-results__panel");
            if(countries.size() > 5) {
                countries.remove(countries.size() - 1); // last element is always malformed.
            }

            Elements regions = doc.getElementsByClass("advanced-listing-results__sub-title");
            int regionNumber = 0;

            // Loop through the country elements.
            for(Element country: countries) {
                String pubCountry = country.getElementsByClass("advanced-listing-results__area-title").text();

                Elements regionPubs = country.getElementsByClass("advanced-listing-results__listing");
                regionPubs.remove(0); // first element seems to always the entire list.

                // Loop through every region in the country
                for(Element region: regionPubs) {
                    String pubRegion = regions.get(regionNumber++).text();
                    Elements pubz = region.getElementsByTag("a");

                    // Loop through each pub in the region.
                    for(Element pub: pubz) {
                        if(pub.attr("href").startsWith("/p")) {
                            String pubName = pub.getElementsByClass("advanced-listing-results__pub-name").get(0).text().replace(" >", "");
                            String pubAddress = pub.getElementsByClass("advanced-listing-results__address").get(0).text();
                            String pubLink = "https://www.jdwetherspoon.com" + pub.attr("href");

                            pubs.add(new Pub(pubName, pubAddress, pubRegion, pubCountry, pubLink));
                        }
                    }
                }

            }

            log.info("Countries: " + countries.size() + ", Regions: " + regions.size() + ", Pubs: " + pubs.size());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Pub> getPubs() {
        return pubs;
    }
}