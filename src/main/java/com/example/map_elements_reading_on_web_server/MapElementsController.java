package com.example.map_elements_reading_on_web_server;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MapElementsController {

    HashMap<Integer, String> hm = new HashMap<>();

    {
        for(int i = 0; i < 5; i++) {
            hm.put(i, "Text " + i);
        }
    }

    @GetMapping("/all")
    public String showAllElements() {

        String allElementsToString = "";

        for (Integer k : hm.keySet()) {
            allElementsToString +=  hm.get(k) + " ";
        }

        return allElementsToString;
    }

    @GetMapping("/element")
    public String showElement(HttpServletRequest httpServletRequest) {

        if (httpServletRequest.getParameter("id") == null) {
            return "Bad request !";
        }

        Integer elementId = Integer.valueOf(httpServletRequest.getParameter("id"));

        if(elementId < 0 || elementId > hm.size()) {
            return "Element not found !";
        }

        return hm.get(elementId);

    }

}