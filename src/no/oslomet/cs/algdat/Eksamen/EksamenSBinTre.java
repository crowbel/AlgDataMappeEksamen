package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        //Oppgave 1

        //Programkode 5.2.3 a)
        Objects.requireNonNull(verdi, "Ulovelig med nullverdier!");

        Node <T> p = rot, q = null;             //p starter i roten
        int cmp = 0;                            //hjelpevariabel

        while(p != null){       //fortsetter til p er ute av treet

        q = p;                                  //q er forelder til p
        cmp = comp.compare(verdi, p.verdi);     //bruker komparatoren
        p = cmp < 0 ? p.venstre : p.høyre;      //flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<T>(verdi, q);                  //oppretter en ny node

        // Lagt inn q som forelder som vil alltid peke til forrige og starter som null.

        if(q == null) rot = p;                  //p blir rotnode
        else if (cmp < 0) q.venstre = p;        //venstre barn til q
        else q.høyre = p;                       //høyre barn til q

        antall++;                               //en verdi mer i treet
        return true;                            //vellykket innlegging
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {

        int teller = 0;                                 //Teller som holder antall tilfeller av verdien
        if(tom()){
            return teller;                              //Hvis treet er tomt, returneres at det er null forekomster av verdien
        }

        if(inneholder(verdi)){                          //Henter inn inneholder() til å sjekke at det er en forekomst ellers kan

            Node <T> p = rot;                           //Setter en node til å starte på rot.

            while(p != null){                           //Så lenge noden ikke når null skal den loope

                int cmp = comp.compare(verdi, p.verdi); //Bruker en lik måte å sammenligne verdiene som i inneholder()
                if(cmp < 0){                            //Hvis cmp er mindre enn, skal p pekkes videre til den mindre noden
                    p = p.venstre;
                }else if(cmp > 0){                      //Hvis den er større skal den pekkes videre til det høyre subtreet.
                    p = p.høyre;
                }
                else{                                   //Hvis cmp er lik skal teller økes og fortsette ned høyre subliste.
                    teller++;                           //Da eventuelle like verdier vil følge som større eller lik hverandre.
                    p = p.høyre;
                }
            }
        }
        return teller;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //Oppgave 3

        //Skal returnere første node i postorden

        //Trenger ikke håndtere eventuelle Nullpointers.
        //Tas for gitt at p != null
        //Det skal håndteres når man kaller metoden.

        //rot = p starter med å sett at roten er p.

        //Kan loope videre å sette p = p.venstre frem til p.venstre == null
        //Da har vi den siste på den ytre venstre grenen.

        //Når den finnes kan jeg prøve å returnere p.

        throw new UnsupportedOperationException("Ikke kodet ennå!");//Fjernes
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        //Oppgave 3

        //Skal returnere neste Noden i post orden.

        //Begge disse under kan tas i en while så lenge p.forelder != null. For da er man på den sistenoden i post. Da skal metoden returnere null.

        //Hvis p er foreldrenoden sin sitt høyre barn og p sin venstre og høyre er null skal p flyttes til foreldrenoden.

        //Hvis på p er foreldrenoden sitt venstre barn må vi sjekke to tilfeller:

        //Hvis p sin forelder.høyre er null. Er p enebarn og dermed er foreldren neste.

        //Hvis p sin foreldre.høyre != null.
        //Da er skal p flyttes til den første i subtreet som begynner på p.foreldre.høyre som er postnode.

        throw new UnsupportedOperationException("Ikke kodet ennå!");//Fjernes
    }

    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
