# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 24 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

Jeg startet med å lagre den opprinnelige utlevert kildekoden.

* Oppgave 1: Løst ved å hente inn Programkode 5.2.3 a) og tilpasse denne. Programkoden henter inn T verdi, og sjekker at denne ikke er null.
Således settes rotnoden og en whileløkke kjøres frem til p ikke er i treet. q er så forelder til p og comp sjekker verdiene.
En ny node opprettes som har verdien og jeg setter q som forelder, da q var den siste vi passerte.
Således det hvor p burde ligge ift q. Og når den er plassert økes antall og metoden returnerer true.

* Oppgave 2: Jeg starter med å sette en teller skal inneholde antall tilfeller av verdien. Deretter kjører jeg en enkel sjekk for å se at treet ikke er tomt.
Deretter bruker jeg inneholder() metoden til å sjekke om det faktisk eksisterer et tilfelle av verdien vi søker etter. Hvis det er tiilfelle setter jeg en temp node p til roten.  og starter et søk i treet.
Denne kjører jeg frem til p er null, i traverseringen bruker jeg en lik måte som i inneholder() til å sammenligne verdiene.
Hvis cmp er mindre enn, skal p pekkes videre til den mindre noden. Hvis den er større skal den pekkes videre til høyre subtre.
Hvis cmp er lik skal teller økes og fortsette på høyre subtre. Fordi eventuelle like verdier vil ligge etter hverandre i denne typen tre.
Til slutt returneres telleren.

* Oppgave 3: I førstePostorden() tar jeg utgangspunkt i Programkode 5.1.7 h). 
Her kjører jeg en while løkkes så lenge den returnerer true. 
Da prøver vi å traversere til første postOrden ved å da gå til vi finner første node som ikke har noen barn.
Ved å gå ned langs ytre venstre gren til vi finner en null. Og da sjekker at ikke det går noe videre ned til høyre for den. 
For da må vi løpe videre ned den til vi finner en null der. 
Så returnerer vi p til slutt da den har gått forbi begge disse tilfellene, for da er vi på første node uten barn.
På nestePostorden() starter jeg med å sjekke p ikke er rotnoden da den vil være sist i postorden. 
Så sjekker jeg om p er venstre eller høyre barn. Hvis høyre barn er neste p.forelder, hvis venstre barn sjekker vi om høyrebarnet er null eller ikke.

* Oppgave 4: I postorden() starter jeg må å sette en node p til førstePostorden(rot), videre sjekker jeg at p ikker null og kan da utføre en oppgave på p.verdi.
Så settes p til nestePostorden(p). Således looper den gjennom alle verdiene og kan utføre oppgaven på hver av dem.
På postorderRecursive() bruker jeg Programkode 5.1.7 d) hvor jeg bare har tilpasset implementeringen og satt den opp så den går i postorden isteden for inorden.
Her har vi to rekursive kall som hver seg sjekker at venstre eller høyre barn ikke er null og kaller så seg selv med p sitt venstre eller høyre barn med oppgaven.
Så kjøres et utførOppgave kall slik som i postorden() etter kallene.

* Oppgave 5: Serialize() ble løst ved å bruke Programkode 5.1.6 a) og tilpasse denne, satt en ny arrayliste og en kø som bruker Java sin Queue.
Setter første i køen til rot, starter en whileløkke som går til køen = null. Setter en hjelpenode til kø.poll().
Så legger jeg til et nytt element i listen med p.verdi før jeg setter inn et nytt element i køen og looper tilbake. Til slutt returneres liste.
På deserialize() begynte jeg på likmåte som i serialize() ved å sette en instans av EksamenSBinTre og sette en lenket liste som skal være kø.
Således satte jeg indeks 0 av ArrayListen data som starten av køen.
Så starter jeg en while-løkke som skal stoppe når køen blir tom. Henter verdien til det aktive kø elementet, og legger dette inn i nyttTree.
Så lenge ikke telleren i møter siste verdi i data listen skal kø endres til gjelende. Så økes teller, og treet returneres.
 
* Oppgave 6: fjern() henter inn Programkode 5.2.8 d) Klarer dessverre ikke å få pekerne til å bli helt riktige i programkoden vi har fått utdelt.
 Dette fører til en evig loop på siste element som skal fjernes "verdi = 8".
 Men ser at det er barna av noden som fjernes som må få oppdaterte pekere i hvert av de tre tilfellene her. 
 
fjernAlle() starter med å sette en teller for hvor mange som er fjernet.
Så sjekker jeg om treet er tomt, hvis ikke kjøres en while løkke som benytter seg av fjern(T verdi) og øker telleren før den returneres.
nullstill() Setter en p og q som rot og nestepostorden(p), metoden går i postorden. 
Så lenge ikke treet er tomt skal p slettes også settes til nestepostorden(q) Så gjøres motsatt hos q til hele treet er fjernet.