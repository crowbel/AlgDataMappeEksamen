package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class main {
    public static void main(String[] args) {
        //Testklasse

        //Oppgave 6 - Testutskrift

        int [] a = {4,7,2,9,4,10,8,7,4,6,1};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int  verdi : a) tre.leggInn(verdi);

        System.out.println(tre.fjernAlle(4)); //3
        tre.fjernAlle(7); tre.fjern(8);

        System.out.println(tre.antall()); //5

        //System.out.println(tre + " " + tre.omvendtString());
        //[1,2,6,9,10] [10,9,6,2,1]
        //OBS: Hvis du ikke har gjort oppgave 4 kan du bruke toString()





        /*

        //Oppgave 2 - Testutskrift
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.antall());           //Utskrift: 10
        System.out.println(tre.antall(5));    //Utskrift: 0
        System.out.println(tre.antall(4));    //Utskrift: 3
        System.out.println(tre.antall(7));    //Utskrift: 2
        System.out.println(tre.antall(10));   //Utskrift: 1

        //Oppgave 1 - Testutskrift
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall()); //Utskrift: 10

         //Oppgave 0 - test
        EksamenSBinTre <String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());

         */

    }
}
