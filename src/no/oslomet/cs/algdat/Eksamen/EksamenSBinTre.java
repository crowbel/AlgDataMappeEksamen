package no.oslomet.cs.algdat.Eksamen;


import java.lang.reflect.Array;
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
            //System.out.println(p);
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
        //Oppgave 6

        //Programkode 5.2.8 d)

        if(verdi == null){                          //Treet har ingen nullverdier
            return false;
        }

        Node <T> p = rot, q = null;                 //q skal være forelder til p

        while(p != null){                           //Leter etter verdi

            int cmp = comp.compare(verdi, p.verdi); //Sammenligner
            if(cmp < 0) {
                q = p; p = p.venstre;               //Går til venstre

            }else if(cmp > 0){
                q = p; p = p.høyre;                 //Går til høyre

            }else break;                            //Den søkte verdi ligger i p
        }

        if(p == null){
            return false;                           //Finner ikke verdi
        }

        if(p.venstre == null || p.høyre == null) {    //Tilfelle 1) og 2)

        Node <T> b  = p.venstre != null ? p.venstre : p.høyre;  //b for barn
            if(p == rot) rot = b;
            else if(p == q.venstre) q.venstre = b;
            else q.høyre = b;

        }

        else{      //Tilfelle 3) at noden har to barn.

            Node <T> s = p, r = p.høyre; //finner neste i inorden
            while(r.venstre != null){
                s = r;      //s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;      //Kopierer verdien i r til p

            if(s != p){
                s.venstre = r.høyre;

            }

            else{
                s.høyre = r.høyre;
            }
        }

        //Må gjøre endringer så foreldrepekerene er korrekte etter sletting.

        //Blir feil sånn den står ved at tallet som står igjen hvis første 8 fjernes.
        //Ender opp med at 8 blir skrevet ut evig, da denne ikke har en riktig peker videre.

        antall--; //Det er nå en node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi) {
        //Oppgave 6

        //Sette en int teller = 0;
        int teller = 0;

        if (tom()){                 //Sjekke om treet er tomt.
            return teller;
        }else {

            while (fjern(verdi)) {  //Kalle på fjern(T) metoden med T verdi og loope den til den returnerer false.
                teller++;           //For hver loop den gjør kan man øke teller med en.
            }
        }
        return teller;      //Så returnere telleren til slutt.
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
        //Oppgave 6

        //Nullstill metode som skal traversere treet.

        Node <T> p = førstePostorden(rot);

        Node <T> q = nestePostorden(p);

        //Valgfri orden - Vurderer postorden da jeg da alltid vil slette noder som ikke har noen barn.

        //Da vil også den siste jeg fjerner være rotnoden som jeg mener er ryddig.

        while(!tom()){

            if(q == nestePostorden(p) || q.forelder == null){     //Skal p slettes.
                fjern(p.verdi);
                antall--;
                p = nestePostorden(q);

            }else if(p == nestePostorden(q) || p.forelder == null){       //Skal q slettes.
                fjern(q.verdi);
                antall--;
                q = nestePostorden(p);
            }
            // Minske antall for hver fjerning.
        }
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //Oppgave 3

        //Tatt utgangspunkt i Programkode 5.1.7 h)

        while(true){
            if(p.venstre != null){
                p = p.venstre;

            }else if(p.høyre != null){
                p = p.høyre;

            }else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        //Oppgave 3

        if (p.forelder == null){
            return null;

        }else if(p == p.forelder.høyre){
            p = p.forelder;

        }else if(p == p.forelder.venstre){
            if(p.forelder.høyre == null){
                p = p.forelder;

            }else{
                p = førstePostorden(p.forelder.høyre);
            }
        }
        return p;
    }

    public void postorden(Oppgave<? super T> oppgave) {

        Node <T> p = førstePostorden(rot);

        while(p != null){
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        //Her brukers Programkode 5.1.7 d) fra Kompendiet.
        //Endret til å ta høyde for å kjøre postOrden istedet for inOrden

        if(p.venstre != null){
            postordenRecursive(p.venstre, oppgave);
        }

        if (p.høyre != null){
            postordenRecursive(p.høyre, oppgave);
        }
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {

        //Programkode 5.1.6 a) - Traversering - Nivåorden

        ArrayList <T> liste = new ArrayList<>();

        Queue <Node <T>> kø = new LinkedList<>();

        kø.add(rot);

        while(!kø.isEmpty()){

            Node <T> p = kø.poll();
            //System.out.println(p.verdi); //Debuggutskrift for å vise p.verdi.
            liste.add(p.verdi);

            if (p.venstre != null){
                kø.add(p.venstre);
            }
            if (p.høyre != null){
                kø.add(p.høyre);
            }
        }
        //System.out.println("Listen sine verdier er: " + liste);
        return liste;
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        //Skal Deserialisere
        //Lage et nytt tre ut ifra et array.
        System.out.println("Data arrayet som skal legges inn: " + data);

        EksamenSBinTre <K> nyttTree = new EksamenSBinTre<>(c);  //Oppretter ny instans av EksamenSBinTre

        Queue <K> kø = new LinkedList<>();  //Setter en kø som tar inn en verdi av <K> data.


        kø.add(data.get(0)); //Starter køen ved å sette første i ArrayListen inn i køen.


        int i = 1;
        while(!kø.isEmpty()){

            K p = kø.poll();

            System.out.println(p);
            nyttTree.leggInn(p); //Legger inn første noden nivåorden.

            if (i <= data.size() - 1){

                kø.add(data.get(i));
            }

            System.out.println("Antall noder i nyttTree: " + nyttTree.antall);
            i++;

        }
        //Deserialize skal da ta dette arrayet, og legge inn alle verdiene (igjen i nivå orden)

        //Dermed gjenskape treet

        //Kan se til Programkode 5.1.6 g)

        System.out.println("Antall noder i nyttTree: " + nyttTree.antall);

        return nyttTree; //Returnerer den nye instansen.
    }


} // ObligSBinTre
