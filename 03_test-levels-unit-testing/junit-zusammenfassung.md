# JUnit 5 Zusammenfassung

Die gängigsten Features von JUnit 5 (Jupiter), jeweils mit kurzem Beispiel.

## @Test

Markiert eine Methode als Testfall. Braucht man für jeden einzelnen Test.

```java
@Test
void addTwoNumbers() {
    assertEquals(5.0, calculator.add(2.0, 3.0), 0.0001);
}
```

## @BeforeEach und @AfterEach

Läuft vor bzw. nach jedem einzelnen Test. Braucht man, damit jeder Test ein frisches Objekt
bekommt und sich die Tests nicht gegenseitig beeinflussen.

```java
@BeforeEach
void setUp() {
    account = new SavingsAccount("S-1000");
}
```

`@AfterEach` wird zum Aufräumen gebraucht, zum Beispiel um `System.out` wieder zurückzusetzen.

## @BeforeAll und @AfterAll

Läuft einmal vor bzw. nach allen Tests der Klasse. Die Methode muss `static` sein. Braucht man
für teure Sachen, die nur einmal aufgebaut werden sollen, zum Beispiel eine Datenbankverbindung.

```java
@BeforeAll
static void initAll() {
    connection = Database.connect();
}
```

## Assertions

Prüfen das Resultat. Schlägt eine Assertion fehl, ist der Test rot.

```java
assertEquals(10000, account.getBalance());
assertTrue(account.deposit(1, 500));
assertNull(bank.createSalaryAccount(5000));
```

Bei `double` immer ein Delta mitgeben, weil Fliesskommazahlen nicht exakt sind:

```java
assertEquals(0.3333, calculator.divide(1.0, 3.0), 0.0001);
```

## assertAll

Führt mehrere Assertions aus und meldet alle Fehler auf einmal, nicht nur den ersten.
Praktisch, wenn man mehrere Felder eines Objekts prüft.

```java
assertAll(
    () -> assertEquals(13576, booking.getDate()),
    () -> assertEquals(12000, booking.getAmount())
);
```

## assertThrows

Prüft, dass eine bestimmte Exception geworfen wird. Braucht man für Fehlerfälle.

```java
assertThrows(ArithmeticException.class, () -> calculator.divide(5.0, 0.0));
```

## @DisplayName

Gibt dem Test einen lesbaren Namen für die Ausgabe in der IDE oder im Report.

```java
@Test
@DisplayName("Division by zero throws an exception")
void divideByZero() { ... }
```

## @Disabled

Schaltet einen Test aus, ohne ihn zu löschen. Sollte nur kurzfristig sein und immer einen Grund haben.

```java
@Test
@Disabled("Fails until the rounding bug is fixed")
void testRounding() { ... }
```

## @ParameterizedTest

Der gleiche Test läuft mit mehreren Eingaben. Braucht man, wenn sich nur die Werte ändern,
zum Beispiel bei Grenzwerten.

Mit `@ValueSource` für einen einzelnen Parameter:

```java
@ParameterizedTest
@ValueSource(ints = {-1, -100, -5000})
void negativeDepositIsRejected(int amount) {
    assertFalse(account.deposit(1, amount));
}
```

Mit `@CsvSource` für mehrere Parameter, zum Beispiel Eingabe und erwartetes Resultat:

```java
@ParameterizedTest
@CsvSource({"2, 3, 5", "-1, 1, 0", "0, 0, 0"})
void add(double a, double b, double expected) {
    assertEquals(expected, calculator.add(a, b), 0.0001);
}
```

Dafür braucht es die Dependency `junit-jupiter-params`, bei `junit-jupiter` ist sie schon dabei.

## @Nested

Gruppiert Tests in einer inneren Klasse. Hilft bei grossen Testklassen, zum Beispiel alle
Tests zu `withdraw` zusammen.

```java
@Nested
class WithdrawTests {
    @Test
    void withdrawTooMuch() { ... }
}
```

## @Tag

Markiert Tests, damit man nur einen Teil laufen lassen kann, zum Beispiel nur die schnellen.

```java
@Test
@Tag("slow")
void bigImport() { ... }
```

## Referenz

JUnit 5 User Guide: https://junit.org/junit5/docs/current/user-guide/

Die offizielle Doku zu JUnit 5 mit allen Features und Beispielen.
