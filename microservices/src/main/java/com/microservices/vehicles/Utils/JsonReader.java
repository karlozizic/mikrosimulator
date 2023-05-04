package com.microservices.vehicles.Utils;


import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReader {

    public static JSONObject getJson(String url) throws IOException {
        InputStream input = new URL(url).openStream();
        String json = IOUtils.toString(input, Charset.forName("UTF-8"));
        return new JSONObject(json);
    }

}