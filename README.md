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

Jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 16 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

Jeg startet med å lagre den opprinnelige utlevert kildekoden.

* Oppgave 1: Løste ved å implementere ...
* Oppgave 2: ...
* Oppgave 5: Serialize() ble løst ved å hente inn Programkode 5.1.6 a) ifra kompendiet og tilpasse denne, opprettet en ny arrayliste og en kø som bruker Java sin Queue.
Starter med å sette første i køen til rot, og starter en whileløkke som holder på til køen er tom. Setter en hjelpenode til kø.poll().
Så legger jeg til et nytt element i listen med p.verdi før jeg setter inn et nytt element i køen og looper tilbake. Til slutt returneres liste.
På deserialize() begynte jeg på likmåte som i serialize() ved å sette en instans av EksamenSBinTre og sette en lenket liste som skal være kø.
Således satte jeg indeks 0 av ArrayListen data som starten av køen.
Så starter jeg en while-løkke som skal stoppe når køen blir tom. Henter verdien til det aktive kø elementet, og legger dette inn i nyttTree.
Så lenge ikke telleren i møter siste verdi i data listen skal kø endres til gjelende. Så økes teller, og treet returneres.
  