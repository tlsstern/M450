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
`when(...).thenReturn(...)` sage ich dem Mock, was er zurückgibt, mit `verify(...)` prüfe
ich, ob der Service das Repository aufgerufen hat. Es wird kein Spring-Context gestartet.
Beim Controller habe ich gleich den Service gemockt.

Das `AddressRepository` ist nur ein Interface von Spring Data und hat keinen eigenen Code,
darum gibt es dafür keine eigene Testklasse.

**Comparator.** In der Vorgabe hat `compare` immer `-1` zurückgegeben. Damit ist jede
Adresse "kleiner" als die andere, auch mit sich selbst verglichen. Das verletzt den Vertrag
von `Comparator` (`compare(a, b)` und `compare(b, a)` müssen entgegengesetzte Vorzeichen
haben, gleiche Objekte geben `0`). Die Sortierung in `getAll()` war dadurch unbrauchbar.
Jetzt wird nach Nachname verglichen, mit `compareTo` von `String`.
