package com.C4.lolapalooza.service;

import com.C4.lolapalooza.models.Client;

import java.util.List;

public interface ClientService {

    List<Client> getClients();
    Client findClientByEmail(String Email);
    void saveC(Client client);
    void deleteClient(Client client);

}
