# Worttrainer

## Überblick

Der Worttrainer ist eine Java-Anwendung, die für Volksschulkinder entwickelt wurde, um ihre Rechtschreibfähigkeiten zu trainieren. Die Anwendung zeigt den Kindern Bilder und fordert sie auf, das entsprechende Wort einzutippen. Die Eingabe wird dann mit der korrekten Schreibweise verglichen und Rückmeldung gegeben.

## Funktionalitäten

- **Wort-Bild-Paarung**: Jedes Wort ist mit einem Bild über eine URL verknüpft, was das Lernen visuell unterstützt.
- **Rechtschreibprüfung**: Die Eingabe des Benutzers wird mit der korrekten Schreibweise des Wortes verglichen.
- **Statistiken**: Die Anwendung zeichnet auf, wie oft Wörter insgesamt, richtig oder falsch geraten wurden.
- **Persistenz**: Die Sitzung des Worttrainers kann gespeichert und später wieder geladen werden, um den Fortschritt beizubehalten.

## Struktur

- `WoerterPaar.java`: Klasse für die Wort-Bild-Paare.
- `WortTrainer.java`: Hauptklasse des Rechtschreibtrainers, die die Logik der Anwendung steuert.
- `Speichern.java`: Interface für Speicherfunktionen.
- `JSONSave.java`: Implementierung des Speichern-Interfaces mit JSON.
- `words.json`: JSON-Datei, die die Wort-Bild-Paare enthält.

## Grafische Oberfläche

Die Anwendung verwendet `JOptionPane` für eine einfache und interaktive Benutzeroberfläche.

## Entwicklung

- **Git und Gradle**: Das Projekt wird unter Verwendung von Git und Gradle entwickelt, um den Software-Entwicklungsprozess zu verwalten.
- **Entwurf vor Implementierung**: Jedes Feature wird zuerst entworfen und dann implementiert, was in der Commit-Historie ersichtlich ist.
- **Tests und Dokumentation**: Automatisierte Testfälle und Javadoc-Kommentare sind für jede Klasse und Methode vorhanden.
- **README.md**: Beschreibt das Programm und seine Funktionsweise.

## Benutzung

Starten Sie das Programm, um entweder eine neue Sitzung zu beginnen oder eine gespeicherte Sitzung zu laden. Folgen Sie den Anweisungen auf dem Bildschirm, um Wörter zu raten und Ihre Rechtschreibfähigkeiten zu verbessern.
