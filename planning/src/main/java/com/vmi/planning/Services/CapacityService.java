package com.vmi.planning.Services;

import com.vmi.planning.Entities.Capacity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityService extends JpaRepository<Capacity, Long> {
}
