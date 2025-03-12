package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Aircraft implements Comparable<Aircraft>{
    private String model;
    private String callSign;

    @Override
    public int compareTo(Aircraft o) {
        return this.callSign.compareTo(o.callSign);
    }

}
