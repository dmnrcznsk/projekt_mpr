package pl.edu.pjatk.projekt_mpr.service;

import org.springframework.stereotype.Service;

@Service
public class StringUtilsService {
    public String toUpperCase(String str) {
        if(!str.isEmpty()) {
            return str.toUpperCase();
        }
        return "";
    }
    public String toLowerCaseButCapitalizeFirstLetter(String str) {
        if(!str.isEmpty()) {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
        return "";
    }
}
