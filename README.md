Rekrutacja online
=================

OPIS
====

Aplikacja pozwala na łatwe przechowywanie i zarządzanie danymi osób zainteresowanych określonym stanowiskiem. 
Silnik posiada wbudowane algorytmy przetwarzania tekstu na podstawie zdefiniowanych reguł. 
Aplikacja posiada w pełni uniwersalny silnik oraz interfejs, który pozwala na skonfigurowanie go według własnych potrzeb. 
Uzupełniając własne typy bazowe, archetypy jak i słowa kluczowe, sami decydujemy jakich osób potrzebujemy w naszej firmie i czego od nich oczekujemy.

KILKA SŁÓW O
============

Aplikacja jest w pełni uniwersalna co do poszukiwanych kandydatów i interesujących nas wyrażeń. 
Aplikacja do poprawnej pracy potrzebuje wypełnioną bazę wiedzy.
Przykładowa baza wiedzy dla profilu pracowanika JAVA została wypełniona w pliku knowledge.sql.
Z poziomu interfejsu administratora możemy w każdym momencie dodać nowe wyrażenia które nas interesją bądź edytować istniejące.
Przykładowa baza wiedzy została ukierunkowana w dużym stopniu na anglo-języczne cv, jednak nic nie stoi na przeszkodzie aby dodać polskie wyrażenia.
Za poprawne przetworzenie tekstu z pliku PDF niezależnie od kodowania pliku jest odpowiedzialana biblioteka org.apache.pdfbox która mapuje plik pdf na string.

KONFIGURACJA
============

Po ściągnięciu serwera Wildfly 8.0.0.final jedyne co musimy zmienić to w pliku standalone.xml zmieniamy sekcję datasource:
```xml
<datasource jndi-name="java:/Recruitment" pool-name="Recruitment" enabled="true" use-java-context="true">
    <connection-url>jdbc:h2:tcp://localhost/~/test</connection-url>
    <driver>h2</driver>
    <security>
        <user-name>sa</user-name>
    </security>
</datasource>
```
Baza danych jedynie wymaga zainstalowania bądź na środowisku linux odpalenia w tle np: screen java -jar <nasz_plik_h2>.jar
Po zbudowaniu paczki war projektu i wrzuceniu w wildfly'u do katalogu deployments aplikacja powinna odpowiadać na: http://localhost:8080/recruitment