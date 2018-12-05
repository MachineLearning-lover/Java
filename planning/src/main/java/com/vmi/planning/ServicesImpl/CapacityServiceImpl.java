package com.vmi.planning.ServicesImpl;

import com.vmi.planning.Dtos.CapacityDto;
import com.vmi.planning.Entities.Capacity;
import com.vmi.planning.Repositories.CapacityRepository;
import com.vmi.planning.Services.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapacityServiceImpl implements CapacityService{

    @Autowired
    private CapacityRepository capacityRepository;

    @Override
    public long addCapacity(Capacity capacity) {
        Capacity capacitySaved = capacityRepository.save(capacity);
        return capacitySaved.getId();
    }

    @Override
    public void deleteCapacity(Long capacityId) {
        Optional<Capacity> capacity = capacityRepository.findById(capacityId);
        capacity.ifPresent(capacity1 -> capacityRepository.delete(capacity1));
    }

    @Override
    public void updateCapacity(Long capacityId, CapacityDto capacity) {
        Optional<Capacity> capacityRepositoryById = capacityRepository.findById(capacityId);
        if (capacityRepositoryById.isPresent()){
            capacityRepositoryById.get().setContentbox(capacity.getContentbox());
            capacityRepositoryById.get().setTimebox(capacity.getTimebox());
            capacityRepositoryById.get().setDaysOff(capacity.getDaysOff());
            capacityRepositoryById.get().setStartDate(capacity.getStartDate());
            capacityRepositoryById.get().setEndDate(capacity.getEndDate());
            capacityRepository.save(capacityRepositoryById.get());
        }
    }

    @Override
    public boolean isPresent(Long capacityId) {
        return capacityRepository.findById(capacityId).isPresent();
    }

    @Override
    public Optional<Capacity> getCapacity(Long capacityId) {
        Optional<Capacity> capacityRepositoryById = capacityRepository.findById(capacityId);
        return capacityRepositoryById;

    }

    @Override
    public List<Capacity> getAllCapacities() {
        return capacityRepository.findAll();
    }
}
