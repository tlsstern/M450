# Schnittstellen

Projekt: `addressbook-backend-v1-1` (Java 21). Die Applikation startet mit
`.\mvnw spring-boot:run`, die Tests laufen mit `.\mvnw clean test`.

## Aufgabe 1 - Tests für alle Klassen

Pro Klasse eine Testklasse unter `src/test/java/ch/tbz/m450/`:

| Testklasse | Was getestet wird |
|---|---|
| `repository/AddressTest` | Erstellen und Ändern von Adressen |
| `service/AddressServiceTest` | Speichern, Liste sortiert, Suche nach ID (gefunden und nicht gefunden) |
| `controller/AddressControllerTest` | Statuscodes 201, 200 und 404 |
| `util/AddressComparatorTest` | Vergleich und Sortierung |

In jeder Testklasse baut `@BeforeEach` die Testdaten vor jedem Test neu auf, damit die
Tests nicht voneinander abhängen.

Im `AddressServiceTest` ist die H2-Datenbank weggemockt. Das `AddressRepository` ist ein
`@Mock` von Mockito und wird mit `@InjectMocks` in den Service gegeben. Mit
`when(...).thenReturn(...)` liefert der Mock die Testdaten, und mit `verify(...)` prüfe
ich, ob der Service das Repository aufgerufen hat. Es wird bewusst kein Spring-Context gestartet.
Beim Controller habe ich gleich den Service gemockt.

Für das `AddressRepository` gibt es keine eigene Testklasse, weil es ein reines Spring-Data-Interface
ohne eigene Methoden ist.

**Comparator.** In der Vorgabe hat `compare` immer `-1` zurückgegeben. Dadurch war jede
Adresse "kleiner" als die andere, sogar im Vergleich mit sich selbst. Die Sortierung
in `getAll()` hat so natürlich nicht funktioniert. Ich habe das korrigiert, sodass nach
Nachname verglichen wird (`compareTo` von `String`).

Getestet wird praxisnah über Listen (`list.sort(comparator)`), bei denen nach der Sortierung
die Position der jeweiligen Adressen (`list.get(0)`, `list.get(1)`, ...) geprüft wird,
statt mathematischer Vorzeichenprüfungen (`< 0` / `> 0`).

## Aufgabe 2 - Comparator erweitern

Der Comparator vergleicht zuerst den Nachnamen. Nur wenn der gleich ist, wird der Vorname
verglichen, und wenn auch der gleich ist, die Telefonnummer.

Getestet in `AddressComparatorTest` anhand von Listen und den resultierenden Positionen:
- `testSortByLastname`: Sortierung nach unterschiedlichen Nachnamen.
- `testSameLastnameComparesFirstname`: gleicher Nachname, Vorname entscheidet über die Reihenfolge.
- `testSameNameComparesPhonenumber`: gleicher Vor- und Nachname, Telefonnummer entscheidet.
- `testSortListMultipleAddresses`: umfassender Test mit einer unsortierten Liste mehrerer Adressen, die alle Kriterien kombiniert.
