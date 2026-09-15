# Aufgaben

Arbeiten Sie zu zweit an diesen Aufgaben.
Stellen Sie sicher, dass Ihre Lösungen in Ihrem Repository abgelegt sind.
Zeigen Sie Ihre Lösungen anschliessend der Lehrperson.

Nehmen Sie das [Frontend sowie das Backend](https://gitlab.com/ch-tbz-it/Stud/m450/m450/-/blob/main/Unterlagen/projects/recipe-planner-fronend-and-backend.zip) in Betrieb. Für die folgenden Aufgaben werden nur changes im Backend benötigt, das Frontend dient lediglich zu Verständnis Zwecken. In einer späteren Aufgabe wird auch das Frontend erweitert.


## Aufgabe 1 - Unit Testing

In der Backend Applikation soll folgendes umgesetzt werden:
1. Testen Sie alle Controller Methoden via MockMvc, RestTemplate, REST-assured oder einer alternativen Methode
2. Testen Sie die Mapper Klasse für die zwei vorhandenen Domänen Klassen und benutzen Sie dazu [SoftAssertions](https://joel-costigliola.github.io/assertj/core/api/org/assertj/core/api/SoftAssertions.html) in ihren Tests. Sind Sie sich bewusst, was der Vorteil von SoftAssertions sind.


## Aufgabe 2 - Reports

In der Backend Applikation soll folgendes umgesetzt werden:
* Es sollen sichtbare Reports für die Unit Tests automatisiert erstellt werden
* Dafür können Sie etwas wie [Surefire](https://maven.apache.org/surefire/maven-surefire-plugin/), [JaCoCo](https://github.com/jacoco/jacoco) oder Alternativen verwenden


## Aufgabe 3 - Pipeline

* Nun soll eine Build pipeline wie beschrieben in der [Theorie](https://gitlab.com/ch-tbz-it/Stud/m450/m450/-/blob/main/Unterlagen/ci-cd-pipeline/README.md) aufgesetzt werden
* Dies kann beliebig via Gitlab / Github oder einer Alternative gemacht werden
* Schlussendlich soll ein commit (bzw. der Push) jeweils die Pipeline und damit die Ausführung der Unit Tests triggern
* Ein Report soll pro Pipeline-Durchlauf generiert werden und einsehbar sein

