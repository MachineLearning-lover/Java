package com.vmi.planning.Repositories;

import com.vmi.planning.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
