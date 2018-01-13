package com.doctor.doctorReview.ServiceImpl;

import com.doctor.doctorReview.Entities.Client;
import com.doctor.doctorReview.Repositories.ClientRepository;
import com.doctor.doctorReview.ServiceInterfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void saveClient(Client client){
        this.clientRepository.save(client);
    }

    public List<Client> getClients(){
        return this.clientRepository.findAll();
    }
}
