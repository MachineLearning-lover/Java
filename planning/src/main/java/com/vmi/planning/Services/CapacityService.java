package com.vmi.planning.Services;

import com.vmi.planning.Dtos.CapacityDto;
import com.vmi.planning.Entities.Capacity;

import java.util.List;
import java.util.Optional;

public interface CapacityService  {
    long addCapacity(Capacity capacity);
    void deleteCapacity(Long capacityId);
    void updateCapacity(Long capacityId, CapacityDto capacity);
    boolean isPresent(Long capacityId);
    Optional<Capacity> getCapacity(Long capacityId);
    List<Capacity> getAllCapacities();

}
