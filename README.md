# Caisse automatique en Java

Application console qui simule une caisse en libre-service : on scanne des articles, le panier se met à jour, puis un ticket de caisse est affiché et enregistré dans un fichier texte.

Projet réalisé en Bachelor 1 Informatique à Ynov (programmation orientée objet en Java).

## Fonctionnalités

- Menu interactif : ajouter un article, afficher le ticket, quitter
- Chaque article a une désignation, un code-barre, un prix et une heure de scan, enregistrée automatiquement
- Nombre d'articles et total mis à jour après chaque ajout
- Durée des courses calculée entre le premier et le dernier scan (passage de minuit pris en compte)
- Ticket enregistré dans `ticket_AAAAMMJJ_HHMMSS.txt`

## Conception

| Classe    | Rôle |
|-----------|------|
| `Article` | un produit scanné (attributs privés, getters, setters) |
| `Panier`  | la liste des articles, le total, la durée, le ticket et le menu |
| `Main`    | point d'entrée |

Notions utilisées : encapsulation, collections (`List`), `java.time` (`LocalDateTime`, `Duration`), écriture de fichier (`PrintWriter`), lecture clavier (`Scanner`).

## Lancer

Avec Maven (Java 17+) :

```bash
mvn compile exec:java -Dexec.mainClass=org.example.Main
```

Ou avec le JDK seul :

```bash
javac -d out src/main/java/org/example/*.java
java -cp out org.example.Main
```
