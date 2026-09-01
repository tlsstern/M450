# Übungen

## Übung 1

Wir haben folgende Beschreibung einer Verkaufssoftware:

*Über die Verkaufssoftware kann das Autohaus seinen Verkäufern Rabattregeln vorgeben: Bei einem Kaufpreis von weniger
als 15’000 CHF soll kein Rabatt gewährt werden. Bei einem Preis bis zu 20’000 CHF sind 5% Rabatt angemessen. Liegt der
Kaufpreis unter 25’000 CHF sind 7% Rabatt möglich, darüber sind 8,5 % Rabatt zu gewähren.*

### Aufgabe

Leiten Sie aus dieser Beschreibung Testfälle ab. Wir wollen beide Varianten von Testfällen untersuchen.

* Eine Tabelle mit abstrakten Testfällen. Hier verwenden Sie logische Operatoren wie > , < , etc.

* Eine Tabelle mit konkreten Testfällen. Hier verwenden Sie ganz konkrete Eingabe-Werte, um die Testfälle zu erstellen.

  
---

## Übung 2

Suchen Sie sich eine Webseite zum Thema **Autovermietung**.

Definieren Sie *funktionale Black-Box Tests*, die Sie brauchen, um diese Plattform zu betreiben. <br/>
*Listen Sie die 5 wichtigsten Testfälle auf*

Erstellen Sie eine Tabelle mit diesen Testfälle als Markdown und stellen Sie diese in Ihr Repository. Hier eine Idee als Tabelle:

| ID | Beschreibung               | Erwartetes Resultat               |Effektives Resultat               |Status               |Mögliche Ursache               |
|-----------|-------------------------|-------------------------|-------------------------|-------------------------|-------------------------|
|   1     | Programm startet korrekt | Nach dem Aufruf des Programms auf der Konsole öffnet sich ein Fenster.| Programm stürzt ab mit Fehler X00234 | Fehler |Zugriff auf DB-Server evtl. nicht möglich |

---

## Übung 3

Sie haben folgende Software einer simplen Bank-Software. Laden Sie das Source-Zip herunter und erstellen Sie ein lokales
Projekt in Ihrer IDE. Achtung! Sie müssen auch die JAR-Files für GSON und OKHTTP installieren. Alternativ können Sie das
[Maven Projekt](https://gitlab.com/ch-tbz-it/Stud/m450/m450/-/blob/main/Unterlagen/teststrategie/bank-software-mvn.zip) verwenden,
um es ohne die JAR-Files in Betrieb zu nehmen. Die Software plus JAR-Files finden Sie
hier: https://gitlab.com/ch-tbz-it/Stud/m450/m450/-/tree/main/Unterlagen/teststrategie
Machen Sie sich mit dem Code vertraut.

Wir wollen ganz grob herausfinden, was für Testfälle es in dieser Software gibt.

* Die Applikation läuft bei Ihnen und Sie können diese testen.
* Identifizieren Sie mögliche Black-Box Testfälle, welche Sie als Benutzer testen können.
* Welche Methoden im Code könnten für White-Box Testfälle verwendet werden?
* Was würden Sie am Code generell verbessern, welche Best Practices fallen Ihnen ein?

Listen Sie Ihre Testfälle tabellarisch auf in einem Markdown-Dokument und stellen Sie Ihre Lösung in Ihr Repository.
