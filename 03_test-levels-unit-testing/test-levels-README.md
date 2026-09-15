![TBZ Logo](../../x_gitres/tbz_logo.png)


<!-- TOC -->
* [Testlevels](#testlevels)
  * [Glossar:](#glossar)
  * [Lernziel:](#lernziel)
  * [Einführung](#einführung)
      * [Begrifflichkeits Erklärung: White Box vs. Black Box testing](#begrifflichkeits-erklärung-white-box-vs-black-box-testing)
    * [Unit Testing](#unit-testing)
    * [Component Testing](#component-testing)
    * [Integration Testing](#integration-testing)
    * [System Testing](#system-testing)
    * [Acceptance Testing](#acceptance-testing)
    * [Recap](#recap)
  * [Source](#source)
* [Checkpoint](#checkpoint)
<!-- TOC -->

# Testlevels

---
<img src="./x_gitres/testarten.png"  width="50%" alt="Testarten">

## Glossar:

| Abkürzung | Erklärung               |
|-----------|-------------------------|
| Bug       | Software Fehler / Error |
| UAT       | User Acceptance Testing |
| QA        | Quality Assurance       |
| SLA       | Service Level Agreement |

## Lernziel:

* Ich kenne die verschiedenen Stufen von Testing
* Ich kenne die Reihenfolge dieser Stufen im Entwicklungsprozess
* Ich kenne Begrifflichkeiten wie White-Box und Black-Box
* Ich weiss welche Stufen von welchen Rollen abgedeckt sind
* Ich kenne den Unterschied zwischen funktionalen und nichtfunktionalen Anforderungen

--- 

## Einführung

Software Testing kann auf verschiedenen Stufen (levels) im Software Entwicklungsprozess stattfinden. Das Ausführen von
Tests hilft der frühen Aufdeckung von bugs und Mängel in der Implementation. Ein grosser Vorteil des Testings ist auch,
dass es zur besseren Qualität der Software beitragt. Die verschiedenen Stufen (in Englisch Test Levels)
werden von verschiedenen Testarten abgedeckt, welche folgende sein können:

* Unit Testing / Component Testing
* Integration Testing
* System Testing
* Acceptance Testing

Hier schauen wir uns an welches Level was abdecken kann. Besonders Unit / Component und Integration Testing
werden wir uns im Detail noch speziell anschauen. Im klassischen Fall wird zwar Integration Tests von einem Testing
Team ausführt, heutzutage gibt es aber auch Möglichkeiten wie Integration Tests in einem Backend (von Entwickler)
ausgeführt werden.
**Unit und Component (und eben manchmal heutzutage auch Integration Tests) sind üblicherweise alle Teil eines Builds.**
Generell wird jede Stufe von Testing immer zeitintensiver (Unit Tests sollten am schnellsten ausführbar sein) und kommen immer
später im Entwicklungsprozess zum Tragen.

<img src="./x_gitres/time-complexity.png"  width="50%" alt="Testarten">

#### Begrifflichkeiten Erklärung: White Box vs. Black Box testing

Der Begriff **White-Box-Test** bezeichnet eine Methode des Software-Tests, bei der die Tests mit Kenntnissen über die
innere
Funktionsweise des zu testenden Systems entwickelt werden. Im Gegensatz zum Black-Box-Test ist für diesen Test also ein
Blick in den Quellcode gestattet. D.h., es wird am Code geprüft.

Beim **Black-Box-Test** werden Tests anhand der Spezifikation/Anforderung entwickelt. Dies bedeutet, dass Tests ohne
Kenntnisse über die innere Funktionsweise/Implementierung des zu testenden Systems entwickelt werden. Das zu testende
Programm wird also als Black Box behandelt. Nur nach aussen sichtbares Verhalten fliesst in den Test ein.

Sprich jeder dieser Levels, die wir unten genauer anschauen, kann entweder aus einer White-Box oder aus einer Black-Box
Sicht getestet werden. Gewisse Levels werden zum Teil auch aus beiden Sichten getestet.

---

### Unit Testing

* Gehören zum ersten Level von Testing
* Wird von den Entwicklern geschrieben und ausgeführt (später automatisiert beim build oder beim deployment, sofern kein build nötig ist wie bei Skript-Sprachen)
* White-Box-Test
* Komponenten werden einzeln in Isolation getestet (in Java Klassen oder Methoden)
* Fehler werden früh entdeckt
* Gibt den Entwickler die Sicherheit für Code Refactorings
* Hauptsächlich funktionale Anforderungen werden hier getestet


* **Limitation:** Nur weil Komponenten in Isolation korrekt funktionieren, heisst es nicht, dass Komponenten zwischen
  einander Korrekt funktionieren

---

### Component Testing

* Wird manchmal zum Unit Testing Level dazu gezählt
* Von Entwickler geschrieben und ausgeführt
* White-Box-Test
* Hier wird das Zusammenspiel zwischen mehreren Komponenten getestet
* Schnittstellen jedoch wie z.B. zu einer Datenbank werden hier gemockt
    * Andere Beispiele von Schnittstellen:
        * Externe APIs
        * Message queues

---

### Integration Testing

* Wird von einem Tester (oder ganzes QA Team) ausgeführt
* Black-Box- und White-Box-Test
* Hier werden Integration wie z.B. zu einer Datenbank (oder APIs / Message queues) nicht mehr gemockt, sondern aktiv
  benutzt
* Beispiel: Der Tester ruft eine API auf, welche dann auf einer Datenbank einen Zugriff macht (der Tester kennt das
  Innere der API jedoch nicht)

**Notiz:** Wie vorher schon erwähnt, gibt es auch Integration Tests, welche von Entwicklern geschrieben und ausgeführt
werden. Hier
wäre es dann ein White-Box-Test.

---

### System Testing

* Wird von den gleichen Teams, welche die Integrations Tests gemacht haben, getestet
* Black-Box-Test
* Hier wird die Software als ganzes getestet, um sicherzustellen, dass alle Requirements erfüllt sind
* Eine Umgebung die möglichst nahe an der **Live** Umgebung ist, wird hier benutzt
* Funktionale und Nichtfunktionale Anforderungen werden hier getestet
* Nichtfunktionale Tests wären hier zum Beispiel:
    * Performance Testing um [Bottlenecks](https://en.wikipedia.org/wiki/Bottleneck_(software)) bezüglich Performanz zu
      finden. Hier kann nochmals zwischen folgenden Testarten unterschieden werden:
        * Load Testing -> Um zu verstehen wie sich die Software z.B. bei einer gesetzten Anzahl an Requests verhält
        * Stress Testing -> Um ein Limit der Software zu wissen
    * Usability Testing um Fehler oder Verbesserungen zu finden, in dem man den Benutzer beobachtet wie er die Software
      bedient
    * Security Testing um z.B. zu Testen, ob die Authentifizierung sicher ist

---

### Acceptance Testing

* Wird in der Regel vom Business / Kunden getestet
* Black-Box-Test
* Hier wird vom Kunden sichergestellt, ob das System die Akzeptanz Kriterien erfüllt

**Notiz:** Acceptance Testing kann auch in verschiedene Typen unterteilt werden, auf die wir aber hier nicht
mehr näher eingehen.

### Recap

Wichtig ist zu wissen, wo der Entwickler und wo der Tester in der Regel ansetzt. Alle Testing Levels bauen aufeinander
auf. Es fängt an, bei den kleinsten (unit) und schnellsten Tests und abstrahiert bei jedem Schritt immer ein bisschen
mehr.
<br/>

<img src="./x_gitres/who-does-what-level.png"  width="50%" alt="Testarten">

---

## Source

* https://en.wikipedia.org/wiki/Software_testing#Testing_levels
* https://artoftesting.com/levels-of-software-testing
* https://www.copado.com/devops-hub/blog/end-to-end-testing-vs-regression-testing-whats-the-difference-crt
* https://www.browserstack.com/guide/what-is-system-testing
* https://www.browserstack.com/guide/acceptance-testing
* https://www.sitesbay.com/software-testing/st-levels-of-software-testing
* https://de.wikipedia.org/wiki/White-Box-Test
* https://de.wikipedia.org/wiki/Black-Box-Test
* https://reqtest.com/en/knowledgebase/different-levels-of-testing/
* https://www.professionalqa.com/levels-of-testing
* https://en.wikipedia.org/wiki/Acceptance_testing

# Checkpoint

* Ich kenne die jeweiligen Testarten (Unit, Component, Integration, System, Acceptance) und ihre Aufgaben
* Ich weiss, dass man Abhängigkeiten in Tests mit Mocks abstrahiert
* Ich verstehe den Unterschied zwischen Whitebox und Blackbox testing
* Ich verstehe den Unterschied zwischen funktionalen und nichtfunktionalen Anforderungen
* Ich weiss, für welche Tests ich als Entwickler zuständig bin
* Ich verstehe den Ablauf und die Reihenfolge dieser Tests