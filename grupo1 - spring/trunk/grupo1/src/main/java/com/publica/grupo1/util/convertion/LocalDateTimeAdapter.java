package com.publica.grupo1.util.convertion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String dateString) throws Exception {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String marshal(LocalDateTime dbDate) throws Exception {
    	LocalDateTime localDate = LocalDateTime.of(dbDate.toLocalDate(), dbDate.toLocalTime());
    	return DateTimeFormatter.ISO_DATE.format(localDate);
    }
}
