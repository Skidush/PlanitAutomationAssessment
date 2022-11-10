package com.planit.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class CSVDataLoader {
    public static HashMap<String, Double> shopItems = new HashMap<>();
    public static HashMap<String, Double> loadShopItems() throws IOException, URISyntaxException {
        if (shopItems.isEmpty()) {
            ClassLoader classLoader = CSVDataLoader.class.getClassLoader();
            URL resource = classLoader.getResource("ShopItems.csv");

            assert resource != null;

            Reader in = new FileReader(new File(resource.toURI()));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("Name", "Price")
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                String name = record.get("Name");
                String price = record.get("Price");

                shopItems.put(name, new Double(price));
            }
        }

        return shopItems;
    }
}
