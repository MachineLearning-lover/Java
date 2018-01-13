package com.doctor.doctorReview.Repositories;

import com.doctor.doctorReview.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
