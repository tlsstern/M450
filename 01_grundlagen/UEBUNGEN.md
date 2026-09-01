# Aufgaben

Arbeiten Sie zu zweit an diesen Aufgaben.
Stellen Sie sicher, dass Ihre Lösungen in Ihrem Repository abgelegt sind.
Zeigen Sie Ihre Lösungen anschliessend der Lehrperson.

## Aufgabe 1

Welche Formen von Tests kennen Sie aus der Informatik?
Arbeiten Sie zu zweit und erläutern Sie mind. drei Beispiele, die Sie aus der Praxis kennen.
Wie werden die Tests durchgeführt?

## Aufgabe 2

Nennen Sie ein Beispiel eines SW-Fehlers und eines SW-Mangels.
Nennen Sie ein Beispiel für einen hohen Schaden bei einem SW-Fehler.

## Aufgabe 3

Eine Software gliedert sich in der Regel in eine Reihe von Teilsystemen, die wiederum aus einer Vielzahl elementarer
Komponenten besteht. Wir haben im V-Modell gesehen, dass es verschiedene Teststufen gibt. Wir wollen in diesem
Zusammenhang nun ein Beispiel der untersten Stufe anschauen.

**Beispiel Test in der Klasse Preisberechnung**

In der Auto-Verkauf Software werden Preise mit Rabatten versehen.
Wir haben folgende Elemente und Regeln:
Es besteht ein Grundpreis (*baseprice*), abzüglich Händlerrabatt (*discount*).
Dazu kommen Sondermodellaufschlag (*specialprice*) und der Preis für weitere Zusatzaustattungen (*extraprice*).
Wenn drei oder mehr Zusatzausstattungen (*extras*) ausgewählt werden, dann erfolgt ein Rabatt von 10% auf diesen
Ausstattungen. Wenn es fünf oder mehr Zusatzausstattungen sind, dann ist der Rabatt bei 15%.
Der Händlerrabatt bezieht sich auf den Grundpreis. Der Zubehörrabatt gilt nur für den Preis der Zubehörteile.
Ein Code würde somit so aussehen:

`

        double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
            double addon_discount;
            double result;
            
            if (extras >= 3) 
                addon_discount = 10;
            else if (extras >= 5)
                addon_discount = 15;
            else 
                addon_discount = 0;
            
            if (discount > addon_discount)
                addon_discount = discount;
            
            result = baseprice/100.0 * (100-discount) + specialprice
                    + extraprice/100.0 * (100-addon_discount);
            
            return result;
	    }

`

Setzen Sie diesen Code in Ihrer Entwicklerumgebung um. Erstellen Sie nun einen entsprechenden *Testtreiber*, um diese Preisberechnung zu testen. Ein Testtreiber ist ein Programm, das die jeweiligen Schnittstellenaufrufe (Methoden-Aufruf) ausführt und anschliessend das Resultat zurückerhält. Skizzenhaft würde das so aussehen:

`
    
    boolean test_calculate_price(){

        double price;
        boolean test_ok = true;
    
        < your code>

    }
`
Achtung: Wir schreiben hier keine Unit-Tests. Unit-Tests werden wir später im Modul anwenden.
Stellen Sie Ihre Tests ins Repository und zeigen Sie Ihre Lösung der Lehrperson.

## Aufgabe 3 - Bonus

Das Programmstück ist fehlerhaft ;) Finden Sie den Fehler im Code. Was müsste man korrigieren?
