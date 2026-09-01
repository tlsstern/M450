# Teststrategie


## Übung 1 - Testfälle aus den Rabattregeln

Ausgangslage ist die Beschreibung aus dem Auftrag: unter 15'000 kein Rabatt, bis 20'000 fünf Prozent, unter 25'000 sieben Prozent, darüber 8.5 Prozent.

Bevor ich Testfälle ableiten konnte, musste ich die Beschreibung erst mal auseinandernehmen, weil sie an den Grenzen nicht sauber ist:

- "bis zu 20'000 CHF" liest sich einschliessend, 20'000 bekommt also noch 5 %.
- "liegt der Kaufpreis unter 25'000" liest sich ausschliessend.
- Für genau 25'000 steht dann nirgends etwas. Weder "unter 25'000" noch "darüber" trifft zu.

Ich habe mich entschieden, 25'000 zu den 8.5 % zu zählen, weil das die einzige Lesart ist, bei der keine Lücke bleibt. In einem echten Projekt würde ich hier nachfragen statt raten.

### Abstrakte Testfälle

| ID | Bedingung | Erwarteter Rabatt |
|----|-----------|-------------------|
| A1 | preis < 15'000 | 0 % |
| A2 | 15'000 <= preis <= 20'000 | 5 % |
| A3 | 20'000 < preis < 25'000 | 7 % |
| A4 | preis >= 25'000 | 8.5 % |

Das sind vier Äquivalenzklassen. Innerhalb einer Klasse verhält sich die Software gleich, darum reicht theoretisch ein Wert pro Klasse.

### Konkrete Testfälle

Theoretisch reicht ein Wert pro Klasse. Praktisch liegen die Fehler fast immer an den Grenzen, deshalb teste ich jede Grenze von beiden Seiten. Als kleinsten Schritt nehme ich 5 Rappen, weil CHF-Beträge nicht feiner werden.

| ID | Eingabe (CHF) | Erwarteter Rabatt | Warum dieser Wert |
|----|---------------|-------------------|-------------------|
| K1 | 10'000.00 | 0 % | Normalfall in der untersten Klasse |
| K2 | 14'999.95 | 0 % | knapp unter der ersten Grenze |
| K3 | 15'000.00 | 5 % | erste Grenze, muss noch Rabatt geben |
| K4 | 20'000.00 | 5 % | zweite Grenze, laut Text noch einschliessend |
| K5 | 20'000.05 | 7 % | knapp darüber, muss kippen |
| K6 | 24'999.95 | 7 % | knapp unter der dritten Grenze |
| K7 | 25'000.00 | 8.5 % | die unklare Stelle, siehe oben |
| K8 | 40'000.00 | 8.5 % | Normalfall in der obersten Klasse |

K3, K4 und K7 sind die interessanten. Wenn jemand `<` und `<=` verwechselt, fallen genau diese drei um und die anderen fünf bleiben grün.

## Übung 2 - Black-Box Testfälle Autovermietung

Getestet auf: https://www.sixt.ch  
Datum: 01.09.2026

Für die Black-Box-Tests habe ich mir die Buchungsstrecke von Sixt (Flughafen Zürich) angeschaut. Getestet wird rein über die Weboberfläche ohne Kenntnis des Backends.

| ID | Beschreibung | Erwartetes Resultat | Effektives Resultat | Status | Mögliche Ursache |
|----|--------------|---------------------|---------------------|--------|------------------|
| 1 | Suche mit Abholort "Zürich Flughafen", Zeitraum 15.09. – 18.09. (3 Tage) | Trefferliste mit verfügbaren Fahrzeugen und transparentem Preis | Liste lädt schnell, Fahrzeuge nach Kategorie sortiert, Preise in CHF direkt ersichtlich | ok | - |
| 2 | Ungültiges Datum: Rückgabedatum (14.09.) vor Abholdatum (15.09.) setzen | Fehlermeldung oder Blockierung, keine ungültige Suche möglich | Kalender-Picker korrigiert das Rückgabedatum automatisch auf denselben Tag, Absenden unmöglich | ok | - |
| 3 | Fahrzeug auswählen (z.B. VW Golf) und bis zur Übersicht durchklicken | Zusammenfassung mit Endpreis, Kaution, Inklusivkilometern und Extras | Übersicht stimmt exakt mit der Trefferliste überein, Aufpreise für Zusatzfahrer etc. werden korrekt addiert | ok | - |
| 4 | Mietdauer im bestehenden Suchergebnis von 3 auf 5 Tage verlängern | Preis steigt nachvollziehbar an | Preis erhöht sich von 195 CHF auf 310 CHF (Tagessatz sinkt leicht wegen Mehrtagesstaffelung) | ok | - |
| 5 | Buchungsvorgang vor Zahlungsabschluss abbrechen | Sauberer Abbruch ohne offene Buchung oder gespeicherte Session-Fehler | Zurück-Navigation funktioniert, keine Reservierung angelegt, Seite leitet sauber auf die Suche zurück | ok | - |

### Anmerkungen

* Bei Testfall 2 fängt das UI den Fehler direkt clientseitig im Datepicker ab, bevor überhaupt ein Request ans Backend geht.
* Bei Testfall 4 ist die Preisänderung plausibel: Bei längerer Mietdauer sinkt der durchschnittliche Tagesansatz leicht (Staffelrabatt).
* Testfall 5 habe ich bewusst nur bis kurz vor den finalen Zahlungsschritt durchgespielt.

## Übung 3 - Bank-Software

Ich habe die Maven-Variante genommen, damit ich die JARs für GSON und OKHTTP nicht von Hand einbinden muss. Die Applikation ist eine Konsolen-App mit fünf vorgegebenen Konten, man kann einzahlen, abheben, überweisen, den Kontostand abfragen, Konten anlegen und löschen sowie einen Wechselkurs abfragen.

### Black-Box Testfälle

Das sind Fälle, die ich als Benutzer über das Menü testen kann. Die Erwartungen kommen aus dem, was die Software fachlich tun müsste, nicht daraus, was der Code macht.

| ID | Testfall | Erwartet | Beobachtet | Status |
|----|----------|----------|------------|--------|
| B1 | 500 auf Konto 3 einzahlen | Kontostand 23'500 → 24'000 | wie erwartet | ok |
| B2 | 200 von Konto 1 abheben (Stand 1500) | Kontostand 1300 | wie erwartet | ok |
| B3 | 2000 von Konto 1 abheben (Stand 1500) | Abbruch mit Meldung, Stand unverändert | Meldung "Kontostand zu niedrig", Stand bleibt | ok |
| B4 | Buchstaben statt Betrag eingeben | Meldung, erneute Abfrage | wie erwartet | ok |
| B5 | Kontonummer 99 wählen | Meldung, dass es das Konto nicht gibt | wie erwartet | ok |
| B6 | **-100 einzahlen** | Ablehnung, Einzahlungen müssen positiv sein | Betrag wird abgezogen, Kontostand sinkt | **Fehler** |
| B7 | **-100 abheben** | Ablehnung | Kontostand steigt um 100 | **Fehler** |
| B8 | **100 von Konto 2 (EUR) auf Konto 3 (CHF) überweisen** | Betrag wird in CHF umgerechnet | "Es wurde keine Umrechnung vorgenommen", 100 kommen 1:1 an | **Fehler** |
| B9 | 100 von Konto 1 (USD) auf Konto 3 (CHF) überweisen | Umrechnung mit Kurs | 111 CHF kommen an, Kurs 1.11 | ok |
| B10 | Konto löschen, danach neues Konto anlegen | Konsistente Nummerierung | Nummern zählen weiter hoch, gelöschte Nummern bleiben Lücken | fraglich |

Die auffälligsten Probleme beim Testen:

* **B6 und B7 (Negative Beträge):** In `deposit` wird der Betrag einfach direkt addiert (`balance += amount`), ohne Vorzeichenprüfung. Bei `withdraw` wird nur `amount > balance` geprüft – bei `-100` ist das `false`, also läuft die Subtraktion durch und der Saldo steigt.
* **B8 (Währungsumrechnung):** In `convertCurrency` sind nur 3 von 6 Währungspaaren abgedeckt (USD→CHF, USD→EUR, CHF→USD). Für EUR→CHF fällt die Methode in den Default-Zweig, gibt den Betrag 1:1 zurück und bucht ihn trotzdem gut. Der Empfänger erhält also den falschen Betrag.
* **B10 (Nummerierung):** Neue Konten erhalten ihre ID über einen statischen Zähler, gelöschte IDs werden nie neu vergeben. Die Begrüssung in `Main` ("Es gibt 5 Konten...") stimmt nach dem Löschen nicht mehr.

### Methoden für White-Box Testfälle

Für White-Box interessieren mich die Methoden, die überhaupt Verzweigungen haben. Bei einem reinen Getter gibt es nichts abzudecken.

| Methode | Warum |
|---------|-------|
| `Account.withdraw(double)` | zwei Zweige über `amount > balance`. Genau ein Vergleich, an dem der Fehler aus B7 hängt |
| `Counter.convertCurrency(...)` | drei `if` plus ein Durchfall-Pfad am Ende. Der Durchfall ist der eigentlich spannende Zweig, und der ist der einzige ohne Testfall |
| `Bank.getAccount(int)` | Schleife mit Treffer und Nicht-Treffer, gibt im zweiten Fall `null` zurück |
| `Counter.transferAmount(...)` | mehrere Pfade: zu wenig Geld, gleiche Währung, unterschiedliche Währung |

`Account.deposit` hat keinen Zweig, da gibt es nichts zu unterscheiden. Trotzdem gehört ein Test hin, sobald die fehlende Prüfung auf negative Beträge eingebaut ist.

### Was mir am Code aufgefallen ist / Verbesserungsvorschläge

1. **Architektur und Testbarkeit (`Counter.java`):**
   `Counter` ist mit knapp 400 Zeilen viel zu vollgepackt. Menü-I/O mit `Scanner`, Konsolenausgaben und eigentliche Geschäftslogik (Überweisungen, Umrechnung) sind komplett vermischt. Das macht Unit-Testing extrem mühsam, weil man Methoden kaum isoliert aufrufen kann. Die Fachlogik gehört in eigene Klassen/Services getrennt von der UI.

2. **Fehlende Validierung:**
   Die Prüfung auf positive Beträge fehlt komplett. Das gehört direkt in die Methoden von `Account` (`deposit`, `withdraw`) und nicht nur ins Menü, damit die Daten immer valide bleiben.

3. **Geldbeträge nicht als `double`:**
   Für Währungen sollte in Java `BigDecimal` oder ganzzahlig in Rappen/Cents gerechnet werden. Mit `double` schleichen sich bei wiederholten Rechnungen Rundungsfehler ein.

4. **Währungskurse doppelt und inkonsistent:**
   Es gibt `ExchangeRateOkhttp` (wo der API-Key fest im Code steht), aber `convertCurrency` rechnet trotzdem mit fest verdrahteten Konstanten. Der Web-Service wird nur im Menü angezeigt, aber gar nicht für die eigentliche Umrechnung verwendet.

5. **Pauschales `catch (Exception e)`:**
   An mehreren Stellen wird jede Exception abgefangen und als "Ungültige Eingabe" ausgegeben. Echte Programmfehler (wie z.B. ein NullPointer) werden dadurch versteckt.

*(Kleine Randnotiz: Die Exception-Klasse heisst `AccountExeption` ohne "c", und in der Menü-Fehlermeldung steht `"u"`, im Menü selbst `"w"`.)*
