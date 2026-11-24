package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.Duration;



public class Panier {
    private String date;
    private List<Article> articles;

    // Constructeur par défaut
    public Panier() {
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.articles = new ArrayList<>();
    }

    // Getters et Setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }

    // Ajouter un article
    public void ajouterArticle(Article a) {
        articles.add(a);
    }

    public boolean estVide() {
        return articles.isEmpty();

    }
    // Calculer le prix total
    public double prixTotal() {
        double somme = 0;
        for (Article a : articles) {
            somme += a.getPrix();
        }
        return somme;
    }


    public String dureeCourses() {
        if (articles.size() < 2)
            return "00:00:00";

        try {
            // Format des horaires "HH-MM-SS"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss");

            // Heure du premier et du dernier article
            LocalTime h1 = LocalTime.parse(articles.get(0).getHoraire_scan(), formatter);
            LocalTime h2 = LocalTime.parse(articles.get(articles.size() - 1).getHoraire_scan(), formatter);

            // Calcul de la durée entre les deux heures
            Duration duree = Duration.between(h1, h2);

            // passage minuit, ajouter 24h
            if (duree.isNegative()) {
                duree = duree.plusHours(24);
            }
            // Retourner sous forme HH:MM:SS
            long heures = duree.toHours();
            long minutes = duree.toMinutes() % 60;
            long secondes = duree.getSeconds() % 60;

            return String.format("%02d:%02d:%02d", heures, minutes, secondes);
            //"%02d:%02d:%02d" pour afficher l'heure avec deux chiffres
        } catch (Exception e) {
            return "Erreur durée";
        }
    }

    // Affichage et sauvegarde du ticket
    public void ticket() {
        LocalDateTime now = LocalDateTime.now();
        String Fichier_Projet = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String mon_Fichier = "ticket_" + Fichier_Projet + ".txt";
        String heureImpression = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append(" \n ===== TICKET DE CAISSE ===== \n");
        sb.append("========================================\n");
        sb.append("Date     : " + date + "\n");
        sb.append("Heure    : " + heureImpression + "\n");
        sb.append("----------------------------------------\n");
        sb.append("DESIGNATION - PRIX\n");
        sb.append("----------------------------------------\n");

        for (Article a : articles) {
            sb.append(a.afficher()).append("\n"); // utilisation de la méthode personnalisée
        }

        sb.append("----------------------------------------\n");
        sb.append(String.format("\n Nombre d'articles :" + articles.size(), "\n"));
        sb.append(String.format("\n TOTAL             :" +  prixTotal(),"\n"));
        sb.append(String.format("\n Durée des courses : " + dureeCourses(),"\n"));
        sb.append("\n========================================\n");
        sb.append("  MERCI.A BIENTÔT.\n");
        sb.append("========================================\n");

        System.out.println(sb);

        // Sauvegarde dans un fichier
        try (PrintWriter pw = new PrintWriter(new FileWriter(mon_Fichier))) {
            pw.print(sb.toString());
            System.out.println(">>> Ticket sauvegardé dans : " + mon_Fichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    // Menu interactif
    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU PANIER D'ACHAT ===");
            System.out.println("1. Ajouter un article");
            System.out.println("2. Afficher le ticket");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");

            String choix = sc.nextLine();

            switch (choix) {
                case "1":
                    Article a = new Article();
                    System.out.print("Désignation : ");
                    a.setDesignation(sc.nextLine());
                    System.out.print("Code barre : ");
                    a.setCode_barre(Integer.parseInt(sc.nextLine()));
                    System.out.print("Prix (DH) : ");
                    a.setPrix(Double.parseDouble(sc.nextLine()));
                    String horaire = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
                    a.setHoraire_scan(horaire);

                    ajouterArticle(a);

                    System.out.println("Article ajouté : ");
                    System.out.println("Nombre d'articles : " + articles.size() );
                    System.out.println("Prix total : " + prixTotal() + " DH");
                    break;

                case "2":
                    if (estVide()) System.out.println("Le panier est vide !");
                    else ticket();
                    break;

                case "3":
                    if (estVide()) System.out.println("Le panier est vide !");
                    else ticket();
                    System.out.println("Au revoir !");
                    return;

                default:
                    System.out.println("Option invalide. Réessayez.");
            }
        }
    }

}