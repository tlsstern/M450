# Testlevels

## Aufgabe 1 - Testing in der Firma
 
### Mit welchen Test Levels hatte ich schon zu tun
 
- **Unit-Tests:** Schreibe ich selber beim Entwickeln von Features und Bugfixes.
  Sie prüfen einzelne Klassen und Methoden isoliert ohne externe Abhängigkeiten.
- **Integrationstests:** Prüfen das Zusammenspiel mehrerer Komponenten, zum Beispiel
  Services mit einer Testdatenbank oder APIs. Werden bei Änderungen an Schnittstellen
  angepasst.
- **System- und Abnahmetests:** Werden auf einer Test- oder Staging-Umgebung ausgeführt,
  teilweise manuell durch Tester oder den Kunden/Product Owner vor dem Release.
 
### Wann werden Tests ausgeführt
 
- Unit-Tests werden lokal während der Entwicklung ausgeführt und laufen automatisch
  in der CI/CD Pipeline (z.B. bei jedem Pull Request / Merge Request).
- Schlägt ein Test in der Pipeline fehl, kann der Merge Request nicht gemergt werden.
- Integrationstests laufen automatisch nach dem Merge auf dem Hauptbranch oder
  beim Deployment auf die Staging-Umgebung.
- Vor einem Release finden End-to-End- und Abnahmetests auf der Staging-Umgebung statt.
 
### Gibt es dedizierte Testing- oder QA-Teams
 
In unserem Team schreiben die Entwickler die Unit- und Integrationstests selbst.
Für übergreifende Abnahme- und Systemtests gibt es teilweise Unterstützung durch
Product Owner oder dedizierte QA-Mitarbeiter, die manuelle Tests und Abnahmen
durchführen.
 
### Wie sieht der Testing Life Cycle aus
 
1. Feature oder Bugfix auf einem Feature-Branch entwickeln und Unit-Tests schreiben
2. Tests lokal ausführen und prüfen
3. Pull Request / Merge Request erstellen -> CI Pipeline führt Unit- und Linting-Tests aus
4. Code Review durch Teammitglieder, danach Merge in den Hauptbranch
5. Automatische Integrationstests und Deployment auf die Staging-Umgebung
6. Manuelle Abnahme / Smoke-Tests auf der Test-Umgebung
7. Release in die Produktion

## Aufgabe 2 - Begriffe einordnen

**Testing approach:** Die grundsätzliche Herangehensweise für ein Projekt. Zum Beispiel
testgetrieben (TDD) oder nachgelagert, manuell oder automatisiert, risikobasiert.
Das wird am Anfang festgelegt, typischerweise im Testkonzept.

**Testing levels:** Die Stufen, auf denen getestet wird: Unit, Component, Integration,
System und Acceptance. Sie sagen, *was* getestet wird, also wie gross das Testobjekt ist,
und wer typischerweise testet. Unit-Tests macht der Entwickler, Acceptance-Tests der Kunde.

**Testing types, techniques and tactics:** Sagen, *wie* innerhalb eines Levels getestet wird.

- Types: funktional oder nicht funktional (Performance, Last, Security, Usability),
  Regressionstest, Smoke-Test
- Techniques: Black-Box (Äquivalenzklassen, Grenzwertanalyse) oder White-Box
  (Anweisungs- und Zweigabdeckung)
- Tactics: was zuerst getestet wird, was automatisiert wird, wo Mocks eingesetzt werden

### Abhängigkeiten

```
Testing approach          (Rahmen: TDD, automatisiert, risikobasiert ...)
  └─ Testing levels       (wo: Unit, Integration, System, Acceptance)
       └─ Types, techniques, tactics   (wie: Black-/White-Box, funktional ...)
```

- Der Approach gibt vor, welche Levels wie stark abgedeckt werden. Bei TDD liegt das
  Gewicht zum Beispiel stark auf Unit-Tests.
- Das Level schränkt die Techniken ein. Auf Unit-Level wird meistens White-Box getestet,
  auf System- und Acceptance-Level Black-Box, weil der Tester den Code nicht kennt.
- Nicht funktionale Types wie Last- oder Performance-Tests machen erst auf System-Level
  Sinn, weil dafür das ganze System laufen muss.
