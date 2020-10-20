package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class main {
    public static void main(String[] args) {

        //Testklasse

        EksamenSBinTre <String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());

    }
}
