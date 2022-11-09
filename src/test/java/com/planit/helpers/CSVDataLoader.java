package com.planit.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;

public class CSVData {
    public static void loadShopItems() throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
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

            _products.put(name, _getShopItem(name));
            productPrices.put(name, new Double(price));
        }
    }
}
