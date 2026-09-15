# Lasttest Backend

## Tool

Ich habe JMeter 5.6.3 genommen. Postman wäre auch gegangen, aber JMeter macht am Schluss
einen HTML-Report mit Diagrammen, das ist für die Auswertung praktischer.

## Was JMeter kann

- Viele parallele Benutzer simulieren (Thread Group mit Anzahl Threads, Ramp-up und Loops oder Dauer)
- Nicht nur HTTP, auch JDBC, FTP, JMS und weitere Protokolle
- Assertions, um die Antworten zu prüfen, und Timer für Pausen zwischen den Requests
- Listener wie Summary Report oder Graphen direkt im GUI
- Testdaten aus einer CSV-Datei einlesen (CSV Data Set Config)
- Requests im Browser aufnehmen (HTTP(S) Test Script Recorder)
- Im CLI-Modus ohne GUI laufen und daraus einen HTML-Report erzeugen

Den Testplan baut man im GUI und speichert ihn als `.jmx`. Für den eigentlichen Lasttest soll man
laut Doku den CLI-Modus nehmen, weil das GUI selber zu viel Leistung braucht.

## Konfiguration

Testplan: `lasttest.jmx`

| Einstellung | Wert |
|---|---|
| Endpoint | `GET http://localhost:8081/students` |
| Threads (Benutzer) | 300 |
| Ramp-up | 30 s |
| Loops | unendlich |
| Dauer | 60 s |

Das Backend läuft auf Port 8081 (steht so in `application.properties`). Backend und JMeter liefen
auf dem gleichen Laptop.

Ausgeführt mit:

```powershell
jmeter -n -t lasttest.jmx -l results.jtl -e -o lasttest-report -Jjmeter.reportgenerator.overall_granularity=1000 -Jjmeter.reportgenerator.statistic_window=5000000
```

Die zwei `-J` Werte habe ich ergänzt, damit die Diagramme im Report pro Sekunde statt pro Minute
zeichnen und die Perzentile über mehr Samples gerechnet werden.

## Resultate

Lauf vom 14.09.2026, Report unter `lasttest-report/index.html`.

| Wert | Resultat |
|---|---|
| Requests | 1'533'654 |
| Durchschnitt Antwortzeit | 8.8 ms |
| Median | 9 ms |
| 90% Perzentil | 26 ms |
| Maximum | 649 ms |
| Durchsatz | 25'596 Requests/s |
| Fehler | 930 (0.06%) |

Die Perzentile im Report sind bei so vielen Samples nur angenähert. Direkt aus der `results.jtl`
ausgerechnet kommt Median 6 ms und 90% Perzentil 19 ms heraus.

## Wo die Antwortzeit steigt

Ausgewertet in 5-Sekunden-Abschnitten:

| Zeit | Threads | Requests/s | Durchschnitt |
|---|---|---|---|
| 0-5 s | bis 50 | 20'396 | 1.3 ms |
| 5-10 s | bis 100 | 29'012 | 2.6 ms |
| 10-15 s | bis 150 | 28'584 | 4.4 ms |
| 15-20 s | bis 200 | 29'295 | 6.0 ms |
| 20-25 s | bis 250 | 25'207 | 9.0 ms |
| 25-55 s | 300 | ca. 25'000 | 11-12 ms |
| 55-60 s | 300 | 22'030 | 13.3 ms |

Ab ungefähr 100 Threads steigt der Durchsatz nicht mehr, er bleibt bei 25'000 bis 29'000 Requests
pro Sekunde. Ab da wird nur noch die Antwortzeit länger, weil die Requests warten müssen. Im Report
sieht man das gut im Diagramm "Response Time vs Threads".

Die 930 Fehler kamen alle in den letzten 5 Sekunden, Meldung `java.net.BindException: Address
already in use: connect`. Das ist kein HTTP-Fehler vom Backend, sondern JMeter konnte keine neue
Verbindung mehr aufmachen. Vermutlich gehen unter Windows die freien lokalen Ports aus, weil
Tomcat eine Keep-Alive-Verbindung nach 100 Requests schliesst und JMeter dauernd neue öffnet
(nicht weiter geprüft).

## Fazit

Der Endpoint hält die Last gut aus, auch mit 300 gleichzeitigen Benutzern bleibt die Antwortzeit
im Durchschnitt unter 15 ms. Die Grenze liegt beim Durchsatz, ab etwa 100 Threads kommen nicht mehr
Requests pro Sekunde durch. Weil Backend und JMeter auf dem gleichen Rechner liefen, teilen sie sich die CPU. Die
Zahlen sind darum eher ein Richtwert als ein echter Wert für einen Server. JMeter selber war
schnell eingerichtet, der HTML-Report ist das Beste daran.
