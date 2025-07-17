package org.example.dao;

import org.example.entity.Client;

public class ClientDAO extends GenericDAO<Client> {
    public ClientDAO(Class<Client> type) {
        super(type);
    }
}
