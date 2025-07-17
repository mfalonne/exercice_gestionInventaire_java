package org.example.service;

import org.example.entity.Vente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.example.util.SessionFactorySingleton;

import java.time.LocalDate;
import java.util.List;

public class RapportVenteService {

    private final SessionFactory sessionFactory;

    public RapportVenteService() {
        this.sessionFactory = SessionFactorySingleton.getSessionFactory();
    }

    /**
     * Ventes par produit : retourne [nom article, nombre de ventes]
     */
    public List<Object[]> getVentesParProduit() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("""
                SELECT a.description, COUNT(v.id)
                FROM Vente v JOIN v.articles a
                GROUP BY a.description
            """, Object[].class).list();
        }
    }

    /**
     * Ventes par période : liste des ventes entre deux dates
     */
    public List<Vente> getVentesParPeriode(LocalDate debut, LocalDate fin) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("""
                FROM Vente v
                WHERE v.date BETWEEN :debut AND :fin
            """, Vente.class)
                    .setParameter("debut", debut)
                    .setParameter("fin", fin)
                    .list();
        }
    }

    /**
     * Ventes par client : retourne [nom client, nombre de ventes]
     */
    public List<Object[]> getVentesParClient() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("""
                SELECT v.client.nom, COUNT(v.id)
                FROM Vente v
                GROUP BY v.client.nom
            """, Object[].class).list();
        }
    }
}
