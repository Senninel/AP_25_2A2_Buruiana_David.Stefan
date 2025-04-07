package org.example;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record Image(String name, LocalDate date, List<String> tags, String path)  implements Serializable {
    public String formattedDate() {
        return date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
