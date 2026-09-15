# Banken-Simulation

Projekt: `02_bank-vorgabe`, Package `ch.schule`. Maven-Projekt, läuft mit `.\mvnw clean test`.

## Wie die Software funktioniert

- Reine Konsolenanwendung, keine Oberfläche, keine Datenbank. Alles liegt im Speicher.
- Beträge sind `long` in **Millirappen**, also 100'000 = 1 CHF. So gibt es keine
  Rundungsfehler wie mit `double`.
- Datum ist ein `int` in **Banktagen seit 1.1.1970**. Ein Jahr hat 360 Tage, ein Monat 30.
- Konten werden über die Bank angelegt, die Bank vergibt die Kontonummer.
- Jede Ein- oder Auszahlung erzeugt eine `Booking`. Der Saldo wird direkt im Konto nachgeführt.
- Eine Buchung mit einem älteren Datum als die letzte Buchung wird abgelehnt (`canTransact`).
- Negative Beträge werden bei Ein- und Auszahlung abgelehnt.
- Ergebnis einer Operation ist immer `true` oder `false`, es gibt keine Exceptions.

## Klassen

- **Bank:** Hält alle Konten in einer `TreeMap<String, Account>`, Schlüssel ist die
  Kontonummer. Legt Konten an (`createSavingsAccount`, `createPromoYouthSavingsAccount`,
  `createSalaryAccount`), leitet `deposit`, `withdraw` und `print` an das richtige Konto
  weiter. Nummern starten bei 1000 und bekommen ein Präfix: `S-` Sparkonto,
  `Y-` Jugendsparkonto, `P-` Lohnkonto.
- **Account:** Abstrakte Oberklasse. Hat `id`, `balance` und die Liste der Buchungen.
  Enthält die Grundlogik für `deposit`, `withdraw`, `canTransact` und die Kontoauszüge
  `print()` und `print(year, month)`.
- **SavingsAccount:** Überschreibt `withdraw`. Man kann nicht mehr abheben, als drauf ist.
- **PromoYouthSavingsAccount:** Erbt von `SavingsAccount`. Überschreibt `deposit` und gibt
  1 % Bonus auf jede Einzahlung.
- **SalaryAccount:** Hat eine Kreditlimite (negative Zahl). Überschreibt `withdraw`, der Saldo
  darf bis zur Limite ins Minus.
- **Booking:** Eine Buchung mit Datum und Betrag. Auszahlungen haben einen negativen Betrag.
  `print(balance)` gibt eine Zeile des Kontoauszugs aus.
- **BankUtils:** Nur statische Hilfsmethoden, formatiert Datum (`dd.MM.yyyy`) und Betrag für
  die Ausgabe.
- **AccountBalanceComparator / AccountInverseBalanceComparator:** Sortieren Konten nach
  Saldo absteigend bzw. aufsteigend. Werden von `printTop5` und `printBottom5` gebraucht.
- **Main:** Startpunkt, legt nur eine Bank und zwei Konten an.

## Zusammenhänge

- Bank 1 zu n Account, über die Kontonummer als Schlüssel (im Diagramm `key`).
- Account 1 zu n Booking, Komposition. Die Buchungen gehören fest zum Konto.
- Vererbung: `SavingsAccount` und `SalaryAccount` erben von `Account`,
  `PromoYouthSavingsAccount` erbt von `SavingsAccount`.
- `Booking` ruft `BankUtils` für die Formatierung auf.

## Vergleich mit dem Klassendiagramm

Die Struktur stimmt, der Code ist aber weiter als das Diagramm:

- Im Diagramm hat `Bank` nur ein `createAccount()`. Im Code gibt es drei create-Methoden,
  eine pro Kontotyp, dazu `printTop5` und `printBottom5`.
- Die beiden Comparator-Klassen und `Main` fehlen im Diagramm.
- `SalaryAccount` hat im Diagramm ein `print()`, im Code nicht. Dafür fehlt im Diagramm das
  Attribut `creditLimit`. Die Abhängigkeit von `SalaryAccount` zu `BankUtils` gibt es im
  Code auch nicht.
- In `Bank` und `Account` hat es noch ungenutzte Felder `account` bzw. `booking` mit Getter und Setter.
 
## Auffälligkeiten beim Testen
 
- `Bank.getBalance()` zieht die Salden der Konten ab (`balance -= ...`). Mit 3000 auf einem
  Konto liefert die Bank -3000 (Kundenguthaben als Verbindlichkeit der Bank).
- `Main` ruft `createSalaryAccount(12000)` mit positiver Limite auf. Da nur negative Limiten
  erlaubt sind, gibt die Methode `null` zurück.
- Der Javadoc von `printBottom5` sagt "höchster Saldo", die Methode druckt aber die tiefsten Kontostände aus.
 
## Tests und Coverage (Aufgabe 4)
 
- Testklassen liegen unter `src/test/java/ch/schule/bank/junit5`, jeweils eine Testklasse pro Modellklasse.
- Die Konsolenausgaben (`print`) werden über `System.setOut` in einen `ByteArrayOutputStream` umgeleitet und geprüft.
- JaCoCo Plugin ist im `pom.xml` konfiguriert (`target/site/jacoco/index.html`).
- Resultat: Alle Tests erfolgreich, Coverage entspricht der Vorgabe.
