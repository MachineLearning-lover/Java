package com.vmi.planning.Repositories;

import com.vmi.planning.Entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint,Long> {
}
