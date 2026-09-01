![TBZ Logo](../../x_gitres/tbz_logo.png)
# Grundlagen zu Testing und Testing in Vorgehensmodelle

<!-- TOC -->
- [Grundlagen zu Testing und Testing in Vorgehensmodelle](#grundlagen-zu-testing-und-testing-in-vorgehensmodelle)
  - [Lernziele](#lernziele)
  - [Wieso Testen?](#wieso-testen)
  - [Ursprung in der Produktion](#ursprung-in-der-produktion)
    - [Fehler vs Mangel](#fehler-vs-mangel)
  - [Fehlermaskierung](#fehlermaskierung)
  - [Kann alles getestet werden?](#kann-alles-getestet-werden)
  - [Kriterien für gute Testfälle](#kriterien-für-gute-testfälle)
  - [Testaufwand](#testaufwand)
- [Testing in Vorgehensmodellen](#testing-in-vorgehensmodellen)
  - [Testing in einem Vorgehensmodell](#testing-in-einem-vorgehensmodell)
  - [Das V-Modell als Prototyp für verschiedene Testarten](#das-v-modell-als-prototyp-für-verschiedene-testarten)
  - [SCRUM als Beispiel für das iterative Testen](#scrum-als-beispiel-für-das-iterative-testen)
<!-- TOC -->

## Lernziele

* Sie können Gründe für das Testen einer Applikation nennen
* Sie kennen den Unterschied zwischen einem Fehler und einem Mangel
* Sie können den Begriff Fehlermaskierung erklären
* Sie können erläutern, wieso ein vollständiges Testen nicht möglich ist
* Sie können die Kriterien für gute Testfälle erklären
* Sie kennen SW-Vorgehensmodelle und können diese beschreiben
* Sie können die Arten von Tests den jeweiligen SW-Vorgehensmodellen zuordnen

---

## Wieso Testen?

Software hat in den letzten Jahren eine enorme Verbreitung gefunden. Es gibt kaum Geräte, Maschinen oder Anlagen, in denen die Steuerung nicht über Software bzw. Softwareanteile realisiert wird. <br/>

Software trägt somit ganz entscheidend zum Funktionieren der Geräte und Anlagen bei. <br/>

Wir haben Software aber auch im Betrieb – z.Bsp. im Bestell- oder Rechnungswesen, wo der Ablauf eines Betriebs oder einer Organisation weitgehend von der Zuverlässigkeit der Softwaresysteme abhängig ist. <br/>

In beiden Bereichen (technische und kommerzielle Software) ist die Qualität der Software zum entscheidenden Faktor für den Erfolg von Produkten oder Unternehmen geworden. <br/>

Ein Mittel, um gute Software-Qualität zu erreichen, ist das systematische Prüfen und Testen der entwickelten Software. <br/>

---

## Ursprung in der Produktion

Der Ansatz der Qualitätsüberprüfung kommt aus der Industrieproduktion. Dabei wird geprüft, ob das Produkt die geforderte Aufgabe löst. Erweist sich ein Produkt als fehlerhaft, so müssen entweder Korrekturen in der Herstellung oder in der Konstruktion erfolgen.<br/>

Bei der Software ist es nicht viel anders: auch hier wollen wir sicherstellen, dass die Software das tut, was von ihr verlangt oder gefordert wird.<br/>

Das Testen ist somit sehr wichtig, um die Qualität einer Software zu gewährleisten.<br/>

Beim Testen wollen wir sichergehen, dass die Software keine Fehler aufweist.<br/>

Dazu eine kleine Auflistung von Begriffen zum Thema “Fehler”:  

### Fehler vs Mangel

Wenn wir vom Testen reden, dann kommen wir unweigerlich zu den Begriffen “Fehler” oder “Mangel”. Hier gibt es einen Unterschied:
<br/>

**Fehler**  
Wir reden von einem “Fehler”, wenn eine Anforderung nicht erfüllt wird. Wir haben eine Abweichung zwischen dem IST-Verhalten (also was das System während des Tests macht) und dem SOLL-Verhalten (was in der Spezifikation oder in den Anforderungen festgelegt wurde).

**Mangel**  
Ein Mangel liegt vor, wenn eine gestellte Anforderung oder eine berechtigte Erwartung nicht angemessen erfüllt wird. So kann es sein, dass z.Bsp. eine Berechnung korrekt ausgeführt wird, jedoch wird die Berechnung nicht korrekt dargestellt (z.Bsp. in einem GUI).

---

## Fehlermaskierung

Nicht zu vergessen ist, dass Fehler vielleicht durch andere Teile des Programms kompenisiert werden. Man spricht dann von einer Fehlermaskierung, weil der Fehler nicht sofort erkennbar ist. Der Fehler tritt in diesem Fall erst dann auf, wenn ein anderer Teil des Programms korrigiert worden ist. Fehler können somit in Abhängigkeit zu anderen SW-Teilen entstehen.
<br/>
Auch kann es sein, dass ein Fehler erst viel später auftritt, z.Bsp. wenn falsch gespeicherte Daten erst später verwendet werden. 

---

## Kann alles getestet werden?

Testen kann Fehlerfreiheit nicht nachweisen. D.h. wir können bei einem Programm nicht alle möglichen Situationen mit allen möglichen Eingaben und unter Berücksichtigung aller unterschiedlichen Randbedingungen testen.<br/>

Die Vielzahl von kombinatorischen Möglichkeiten zeigt eine nahezu unbegrenzte Anzahl an Tests, die durchzuführen wären.


## Kriterien für gute Testfälle

Somit wird immer nur ein Teil aller denkbaren Testfälle ausgeführt. Die Kunst des “richtigen” Testens besteht somit  

* Aus Tests, welche eine hohe Wahrscheinlichkeit von Fehler aufzeigen
* Aus Tests, die nicht dasselbe testen (keine Redundanz)
* Aus Tests, die unabhängig voneinander sind
* Aus Tests, die möglichst viel Code abdecken

Wir werden dabei zum letzten Punkt (Codeabdeckung) Werkzeuge kennenlernen, die dem Programmierer dabei helfen, eine möglichst hohe Testabdeckung zu erreichen.

---

## Testaufwand 

Da ein vollständiger Test nicht möglich ist, muss der Testaufwand in einem vernünftigen Verhältnis zum erzielbaren Ergebnis stehen.  

Ganz einfach erklärt: Systeme, die einen hohen Schaden bei Fehler verursachen, müssen ausgiebiger getestet werden, als Systeme, welche einen geringen Schaden verursachen. Dabei muss ein “Schaden” nicht bloss ein finanzieller sein – es kann auch einen lebensgefährlichen Schaden verursachen.

---

# Testing in Vorgehensmodellen


## Testing in einem Vorgehensmodell

Wie Sie aus dem [Modul 426 ("Software-entwicklung mit agilen Methoden")](https://gitlab.com/ch-tbz-it/Stud/M426) wissen, gibt es in der Software-Entwicklung unterschiedliche Vorgehensmodelle. Dabei geht es darum, die Entwicklung einer Applikation nach einer bestimmten Struktur (organisatorisch, planerisch, etc.) umzusetzen. Oder anders gesagt: Jedes IT-Projekt kann nach einem anderen Vorgehen realisiert werden. 
<br/>
Wir wollen an dieser Stelle nur zwei Vorgehensmodelle genauer anschauen, weil diese die Art des Testens massgeblich beeinflusst bzw. verändert haben.


## Das V-Modell als Prototyp für verschiedene Testarten

Das V-Modell ist eine Erweiterung des klassischen "Wasserfallmodells". Hier wurde das Verständnis für Softwaretests nachhaltig verändert.<br/>

Im Wasserfallmodell kommt das Testing ziemlich am Schluss eines Projekts zum Zug.
Im V-Modell hingegen, wird das Testen mit der Entwicklung gleichgesetzt.  
<br/>
Dies wird auch bildlich dargestellt in Form eines V (Bilder zum V-Modell gibt es wie Sand am Meer. Wir haben eines ausgesucht, das möglichst dem allgemeinen V-Modell entspricht. Quelle: https://www.peterjohann-consulting.de/v-modell/):

![V Modell](../../x_gitres/peco-v-modell.png)

Der **linke Ast** steht für die Entwicklungsschritte, in deren Verlauf das gewünschte System schrittweise und zunehmend detaillierter entworfen und programmiert wird. Diese Schritte sind sehr ähnlich wie im Wasserfallmodell, also aufgegliedert in

* **Anforderungsdefinition** mit den Wünschen und Anforderungen des Auftraggebers
* **Funktionaler Systementwurf** wo die Anforderungen auf Funktionen und Dialoge abgebildet werden
* **Technischer Systementwurf** wo die technische Realisierung entworfen wird (System wird in Komponenten unterteilt, Schnittstellen werden definiert)
* **Komponentenspezifikation**, wo jedes Teilsystem im Detail beschrieben wird
* **Programmierung**, wo jeder Baustein (Modul, Klasse, etc.) in einer Programmiersprache programmiert wird

Der **rechte Ast** zeigt die Integrations- und Testarbeiten, in deren Verlauf elementare Programmbausteine sukzessive zu grösseren Teilsystemen zusammengesetzt (= integriert) werden.
Wir haben somit auf jeder Ebene eine bestimmten Test:

* **Komponententest** prüft, ob jeder elementare Softwarebaustein seine Vorgaben erfüllt.
* **Integrationstest** prüft, ob Gruppen von Komponenten korrekt zusammenspielen
* **Systemtest** prüft, ob das System als Ganzes die Anforderungen erfüllt.
* **Abnahmetest** prüft, ob das System vom Kunden als korrekt akzeptiert wird.

Für jede dieser Testarten gibt es unterschiedliche Testmethoden und Testwerkzeuge. Es gibt auch unterschiedliches Personal (z.Bsp. wird sich der Programmierer um den Komponententest kümmern und der Kunde beim Abnahmetest massgeblich beteiligt sein).

Das V-Modell sieht so aus, als ob das Testen erst spät beginnen würde. Dieser Eindruck ist aber falsch. Im V-Modell ist das Testen gleichwertig wie das Entwickeln einer Software. Somit werden diese Teststufen parallel mit der Entwicklung durchgeführt.

## SCRUM als Beispiel für das iterative Testen

SCRUM haben Sie schon im [Modul 426](https://gitlab.com/ch-tbz-it/Stud/M426) kennengelernt. Hier steht der iterative Prozess im Vordergrund, mit der Idee, dass jede folgende Iteration einer Software eine verbesserte Version des Produkts erstellt. Das Produkt wird nicht *an einem Stück* entwickelt, sondern in einer Abfolge von Versionen.
Die Entwicklung wird somit agiler.

![SCRUM](../../x_gitres/scrum_flow.jpg)

Nach jedem *Definition of Done* wird ein Increment erstellt, welches nach einer Review als *shippable* oder ausführbar gilt.
Das Testen ändert sich natürlich in einem solchen iterativen Vorgang.
Für jede Komponente müssen wiederverwendbare Tests vorhanden sein, die bei jedem Inkrement wiederholt werden können. Hier sind **automatisierte Tests** sehr wichtig, denn mit anwachsender Produktfunktionalität müssen immer mehr Testfälle abgearbeitet werden.
Wir werden die Test-Automatisierung noch genauer anschauen in diesem Modul (unter [automation-testing](../automation-testing)).


---
**Quellen:**

* Basiswissen Softwaretest (A. Spillner)
* Essentials of Software Testing (R. Bierig)
* Projekt leicht gemacht (https://projekte-leicht-gemacht.de/blog/projektmanagement/klassisch/v-modell/ ), last accessed August 2023
* https://www.peterjohann-consulting.de/v-modell/ 

