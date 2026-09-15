# Testlevels

## Aufgabe 1 - Testing in der Firma

### Mit welchen Test Levels hatte ich schon zu tun

Bei Google orientieren wir uns primär an der Test-Pyramide und klassifizieren Tests nach ihrer Grösse (**Test Sizes: Small, Medium, Large**), was den klassischen Teststufen entspricht:

- **Small Tests (Unit-Tests):** Schreibe ich als Software Engineer für jede Klasse und Methode. Sie laufen isoliert in einem einzigen Prozess/Thread, ohne externe Ressourcen (kein Netzwerk, keine Festplatte, keine echten Datenbanken – stattdessen Fakes und Mocks). Sie sind extrem schnell (im Millisekundenbereich) und machen rund 70 % aller Tests aus.
- **Medium Tests (Integrationstests):** Prüfen das Zusammenspiel mehrerer lokaler Komponenten auf einer einzelnen Maschine/Instanz, oft in einer hermetischen Testumgebung (z.B. mit In-Memory-Datenbanken oder lokalen Emulatoren). Sie machen ca. 20 % aus.
- **Large / Enormous Tests (System- & End-to-End-Tests):** Testen verteilte Systeme über mehrere Server und reale Netzwerkverbindungen hinweg, um vollständige End-to-End-Benutzerpfade oder Last- und Performance-Verhalten vor dem Rollout zu prüfen (ca. 10 %).

### Wann werden Tests ausgeführt

- **Lokal während der Entwicklung:** Small- und Medium-Tests werden bereits beim Entwickeln lokal mit dem Build-System (Bazel/Blaze) ausgeführt.
- **Presubmit (Continuous Integration via TAP / Test Automation Platform):** Bei jeder Änderung (Changelist / CL) werden automatisch alle betroffenen Tests ausgeführt. Ist auch nur ein Test rot, wird das Einreichen (Submit) blockiert.
- **Postsubmit:** Nach dem Submit laufen kontinuierliche Regressions-Testsuiten über den gesamten Monorepo-Codebase.
- **Continuous Deployment & Canary:** Neue Versionen werden schrittweise ausgerollt (z.B. Canary auf 1 % -> 10 % -> 100 % der Nutzer) und über automatisierte Metriken und Error Budgets überwacht.

### Gibt es dedizierte Testing- oder QA-Teams

- **Keine manuellen QA-Teams:** Bei Google gilt das Prinzip *«You build it, you test it, you run it»*. Entwickler (Software Engineers) sind zu 100 % selbst dafür verantwortlich, ihre Tests (Unit, Integration, E2E) zu schreiben und zu automatisieren.
- Es gibt spezialisierte Rollen wie **SETs (Software Engineers in Test) / Test Engineers (TEs)**. Sie führen jedoch keine manuellen Testabnahmen durch, sondern entwickeln Test-Infrastruktur, Test-Frameworks, automatisierte Test-Pipelines und beraten Teams bei der Testbarkeit komplexer Architekturen.
- Qualitätssicherung vor Releases erfolgt durch automatisierte Tests, internes **Dogfooding** (Mitarbeitende testen neue Features vorab intern) und **Feature Flags**.

### Wie sieht der Testing Life Cycle aus

1. Feature oder Bugfix entwickeln und begleitend Small Tests (Unit-Tests) schreiben
2. Tests lokal ausführen und verifizieren
3. Changelist (CL) erstellen -> Presubmit CI (TAP) führt automatisch alle betroffenen Tests und Linters aus
4. Code Review (Critique) durch Teammitglieder (LGTM und Approval von Code Owners)
5. Nach erfolgreichem Review und grünen Presubmit-Tests: Submit in den Hauptzweig
6. Postsubmit-Tests verifizieren die Integration im gesamten Repository
7. Automatisierter Staged Rollout (Canary / Feature Flags) mit Live-Monitoring und automatischem Rollback bei Fehlern

## Aufgabe 2 - Begriffe einordnen

**Testing approach:** Die grundsätzliche Herangehensweise für ein Projekt. Zum Beispiel
testgetrieben (TDD) oder nachgelagert, manuell oder automatisiert, risikobasiert.
Das wird am Anfang festgelegt, typischerweise im Testkonzept (z.B. Google-Standard: 70% Small, 20% Medium, 10% Large Tests).

**Testing levels:** Die Stufen, auf denen getestet wird: Unit (Small), Component, Integration (Medium),
System und Acceptance (Large). Sie sagen, *was* getestet wird, also wie gross das Testobjekt ist,
und wer typischerweise testet. Bei uns schreiben die Entwickler alle Stufen automatisiert.

**Testing types, techniques and tactics:** Sagen, *wie* innerhalb eines Levels getestet wird.

- Types: funktional oder nicht funktional (Performance, Last, Security, Usability),
  Regressionstest, Smoke-Test
- Techniques: Black-Box (Äquivalenzklassen, Grenzwertanalyse) oder White-Box
  (Anweisungs- und Zweigabdeckung)
- Tactics: was zuerst getestet wird, was automatisiert wird, wo Mocks/Fakes eingesetzt werden

### Abhängigkeiten

```
Testing approach          (Rahmen: automatisiert, Testpyramide 70/20/10, risikobasiert ...)
  └─ Testing levels       (wo: Small/Unit, Medium/Integration, Large/System)
       └─ Types, techniques, tactics   (wie: Black-/White-Box, Mocks/Fakes, funktional ...)
```

- Der Approach gibt vor, welche Levels wie stark abgedeckt werden. Bei einem stark automatisierten Unit-First Approach liegt das
  Gewicht auf schnellen Small Tests.
- Das Level schränkt die Techniken ein. Auf Unit-Level wird meistens White-Box mit Mocks/Fakes getestet,
  auf System- und Acceptance-Level Black-Box gegen echte Schnittstellen.
- Nicht funktionale Types wie Last- oder Performance-Tests machen erst auf System-Level
  Sinn, weil dafür das ganze verteilte System laufen muss.
