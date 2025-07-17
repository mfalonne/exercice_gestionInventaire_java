package org.example.dao;

import org.example.entity.Vente;

public class VenteDAO extends GenericDAO<Vente> {
    public VenteDAO(Class<Vente> type) {
        super(type);
    }
}
