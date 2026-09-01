# Grundlagen Testing

## Aufgabe 1 - Formen von Tests aus der Praxis

**Unit-Tests** prüfen einzelne Methoden oder Klassen isoliert, ohne Datenbank und ohne Netzwerk. In meinem Betrieb laufen sie automatisiert im Presubmit: bevor eine Änderung überhaupt ins Code Review geht, wird die betroffene Test-Suite ausgeführt. Ist ein Test rot, lässt sich die Änderung nicht einreichen.

**Integrationstests** prüfen das Zusammenspiel mehrerer Komponenten, also zum Beispiel Service und Datenbank zusammen. Sie sind deutlich langsamer als Unit-Tests und laufen darum nicht bei jeder Änderung, sondern regelmässig gegen den aktuellen Stand. Bricht einer, sucht man den Commit, der seit dem letzten grünen Lauf dazugekommen ist.

**Canary-Release** eine neue Version geht zuerst nur an einen kleinen Teil der Nutzer. Parallel werden Fehlerrate und Latenz gegen die alte Version verglichen. Sind die Werte schlechter, wird zurückgerollt, bevor die Version alle erreicht. Das ist kein Test im klassischen Sinn, fängt aber Probleme, die vorher niemand nachstellen konnte.

Der Unterschied liegt vor allem darin, wann getestet wird und wie viel vom System dabei läuft: Unit-Tests vor dem Review auf einzelnen Klassen, Integrationstests danach über mehrere Komponenten, Canary erst in Produktion am echten Verkehr.

## Aufgabe 2 - Fehler und Mangel

**Softwarefehler** Die Software macht gemessen an der Spezifikation etwas falsch. Beispiel aus Aufgabe 3: die Rabattstaffel prüft `extras >= 3` vor `extras >= 5`, dadurch gibt es ab fünf Zusatzausstattungen nur 10 % statt der vorgeschriebenen 15 %. Der berechnete Preis ist schlicht falsch.

**Softwaremangel** Die Software erfüllt die Spezifikation, etwas Erwartetes fehlt trotzdem. Beispiel: ein Bestellformular weist eine ungültige Eingabe korrekt zurück, sagt aber nicht, welches Feld das Problem ist. Formal ist nichts falsch, brauchbar ist es nicht.

**Hoher Schaden** CrowdStrike-Vorfall (Juli 2024). Ein fehlerhaftes Konfigurationsupdate für den Kernel-Treiber wurde ohne stufenweises Rollout (Canary) global verteilt und löste weltweit 8.5 Millionen Bluescreens aus. Flughäfen, Spitäler und Notrufdienste standen still, der Gesamtschaden ging in die Milliarden. Ursache war mangelnde Validierung der Update-Datei vor dem Release.

## Aufgabe 3 Bonus - Fehler im Code

Der Fehler steckt in der Reihenfolge der Bedingungen:

```java
if (extras >= 3)
    addon_discount = 10;
else if (extras >= 5)
    addon_discount = 15;
```

Bei fünf Extras ist `extras >= 3` bereits wahr, der `else if`-Zweig wird deshalb nie erreicht. Ab fünf Zusatzausstattungen gibt es dadurch 10 % statt 15 % Rabatt. Der Zweig mit den 15 % ist toter Code.

Korrektur: die engere Bedingung zuerst prüfen.

```java
if (extras >= 5)
    addon_discount = 15;
else if (extras >= 3)
    addon_discount = 10;
```

Der Testtreiber zeigt den Fehler im Testfall `five extras`: erwartet 21700.0, effektiv 21800.0. Die Original-Methode habe ich absichtlich fehlerhaft gelassen, damit der Testfall rot bleibt.
