package org.example;

import org.example.dao.ArticleDAO;
import org.example.dao.ClientDAO;
import org.example.dao.VenteDAO;
import org.example.entity.*;
import org.example.entity.enums.CategorieMode;
import org.example.entity.enums.StatutVente;


import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Initialisation des DAO
        ClientDAO clientDAO = new ClientDAO(Client.class);
        ArticleDAO articleDAO = new ArticleDAO(Article.class);
        VenteDAO venteDAO = new VenteDAO(Vente.class);

        // 1. Création de client
        Client client = new Client();
        client.setNom("Alice Dupont");
        client.setEmail("alice.dupont@email.com");
        clientDAO.createOrUpdate(client);

        // 2. Création d'articles
        ArticleMode tShirt = new ArticleMode();
        tShirt.setDescription("T-Shirt Blanc");
        tShirt.setPrix(19.99);
        tShirt.setQuantite(10);
        tShirt.setDateRestock(LocalDate.now());
        tShirt.setCategorie(CategorieMode.FEMME);
        tShirt.setTaille("M");

        ArticleElectronique casque = new ArticleElectronique();
        casque.setDescription("Casque Bluetooth");
        casque.setPrix(49.99);
        casque.setQuantite(5);
        casque.setDateRestock(LocalDate.now());
        casque.setDureeBatterie(12);

        ArticleNourriture biscuit = new ArticleNourriture();
        biscuit.setDescription("Biscuit Chocolat");
        biscuit.setPrix(2.99);
        biscuit.setQuantite(100);
        biscuit.setDateRestock(LocalDate.now());
        biscuit.setDatePeremption(LocalDate.now().plusMonths(6));

        articleDAO.createOrUpdate(tShirt);
        articleDAO.createOrUpdate(casque);
        articleDAO.createOrUpdate(biscuit);

        // 3. Création d'une vente
        Vente vente = new Vente();
        vente.setClient(client);
        vente.setDate(LocalDate.now());
        vente.setStatus(StatutVente.EN_COURS);
        vente.setArticles(Arrays.asList(tShirt, casque));

        venteDAO.createOrUpdate(vente);

        System.out.println("Données insérées avec succès !");
        System.out.println("Vente créée avec client : " + client.getNom() + " contenant " + vente.getArticles().size() + " article(s)");
    }
}
