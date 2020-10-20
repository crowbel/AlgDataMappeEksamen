package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class main {
    public static void main(String[] args) {
        //Testklasse

        //Oppgave 1 - Testutskrift

        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall()); //Utskrift: 10

        /* //Oppgave 0 - test
        EksamenSBinTre <String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());

         */

    }
}
