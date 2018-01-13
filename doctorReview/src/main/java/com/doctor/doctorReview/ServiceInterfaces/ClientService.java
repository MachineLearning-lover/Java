package com.doctor.doctorReview.ServiceInterfaces;

import com.doctor.doctorReview.Entities.Client;

import java.util.List;

public interface ClientService {
     void saveClient(Client client);
     List<Client> getClients();

}
