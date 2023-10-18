package com.dededec.metadataid.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.dededec.metadataid.model.entity.HTML5Tags;

public class PageAnalysisHelper {

    private static String[] headings = new String[] { "h1", "h2", "h3", "h4", "h5", "h6" };

    public static String getHeadings(Document doc) {
        String result = "";
        for (String heading : headings) {
            int number = doc.select(heading).size();
            if (number > 0) {
                result += heading + ":" + number + ",";
            }
        }

        return Optional.ofNullable(result)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(result);
    }

    public static List<String> getKeywords(Document doc) {
        Element element = doc.head().getElementsByAttributeValue("name", "keywords").first();
        return element != null
                ? Arrays.asList(element.attr("content").split(","))
                : null;
    }

    public static String getDescription(Document doc) {
        Element element = doc.head().getElementsByAttributeValue("name", "description").first();
        return element != null
                ? element.attr("content")
                : null;
    }

    public static String getTitle(Document doc) {
        return doc.title();
    }

    public static Boolean hasHTML5(Document doc) {
        Boolean hasHMTL5 = false;
        for (HTML5Tags tag : HTML5Tags.values()) {
            if (!doc.getElementsByTag(tag.name()).isEmpty()) {
                hasHMTL5 = true;
            }
        }
        return hasHMTL5;
    }

    public static Integer getImagesNumber(Document doc) {
        return doc.select("image").size();
    }

}
