# Automation Testing - Aufgaben

Grundlage ist `spring-boot-angular-basic-lw2` aus dem Zip. Backend Spring Boot (Port 8081),
Frontend Angular 16 unter `src/main/js/my-app` (Port 4200).

## Applikation starten

Backend, Terminal 1 (Java 17 nur für dieses Terminal):

```powershell
$env:JAVA_HOME = 'C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
cd spring-boot-angular-basic-lw2
mvn spring-boot:run
```

Frontend, Terminal 2 (Node 18 nur für dieses Terminal, Angular 16 läuft nicht auf Node 24):

```powershell
$env:Path = "$env:LOCALAPPDATA\nvm\v18.20.8;$env:Path"
cd spring-boot-angular-basic-lw2\src\main\js\my-app
npm install
npm start
```

`.\mvnw` geht nicht, im Zip fehlt der Ordner `.mvn`. Darum `mvn` direkt.

## Übung 1: REST-Schnittstelle testen

Testklasse `StudentApiTest` unter `spring-boot-angular-basic-lw2/src/test/java`. Zwei Tests:
`GET /students` gibt die Startdaten zurück, `POST /students` fügt einen Studenten hinzu und er ist
danach in der Liste.

Tool: Spring Boot Test mit `TestRestTemplate`. Das ist schon in `spring-boot-starter-test` drin,
startet die ganze Applikation auf einem freien Port und schickt echte HTTP-Requests, also
brauche ich kein zusätzliches Tool.

Ausführen (Backend muss dafür nicht laufen):

```powershell
cd spring-boot-angular-basic-lw2
mvn test
```

## Übung 2: End-to-End-Test im Browser

Tool: Playwright, als devDependency im Frontend. Test `e2e/add-student.spec.ts`: Seite öffnen, auf
"Add Students" klicken, Name und Email ausfüllen, Submit drücken und prüfen, dass der neue Student
in der Liste steht. Konfiguration in `playwright.config.ts`.

Backend und Frontend müssen laufen. Playwright 1.62 braucht mindestens Node 20, darum läuft der
Test in einem eigenen Terminal mit Node 24:

```powershell
$env:Path = "$env:LOCALAPPDATA\nvm\v24.14.1;$env:Path"
cd spring-boot-angular-basic-lw2\src\main\js\my-app
npx playwright test --headed
```

Ohne `--headed` läuft er ohne sichtbaren Browser.
