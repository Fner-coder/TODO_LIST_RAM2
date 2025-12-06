package com.frandler.localDateAdapter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public void write(JsonWriter writer, LocalDateTime value) throws IOException {
        writer.value(value.format(FORMATTER));
    }

    @Override
    public LocalDateTime read(JsonReader reader) throws IOException {
        String date = reader.nextString();
        return LocalDateTime.parse(date, FORMATTER);
    }
}

