package com.vmi.planning.Controllers;

import com.vmi.planning.Dtos.CapacityDto;
import com.vmi.planning.Entities.Capacity;
import com.vmi.planning.Services.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/capacity")
public class CapacityController {

    @Autowired
    private CapacityService capacityService;


    @RequestMapping(value="/addCapacity", method = POST)
    public ResponseEntity addCapacity(@RequestBody @Valid CapacityDto capacityDto){
        Capacity capacity = new Capacity(capacityDto);
        return ResponseEntity.ok(capacityService.addCapacity(capacity));
    }

    @RequestMapping(value="/deleteCapacity/{capacityId}", method = DELETE)
    public ResponseEntity deleteCapacity(@PathVariable("capacityId") Long capacityId){
        if (capacityService.isPresent(capacityId))
            capacityService.deleteCapacity(capacityId);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/updateCapacity/{capacityId}", method = PUT)
    public ResponseEntity updateCapacity(@PathVariable("capacityId") Long capacityId,
                                         @RequestBody @Valid CapacityDto capacityDto){
        if (capacityService.isPresent(capacityId))
            capacityService.updateCapacity(capacityId, capacityDto);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/getAllCapacities", method = GET)
    public ResponseEntity getAllCapacities(){
        return ResponseEntity.ok(capacityService.getAllCapacities());
    }

    @RequestMapping(value="/getCapacity}", method = GET)
    public ResponseEntity getCapacity(@RequestParam("capacityId") Long capacityId){
        return ResponseEntity.ok(capacityService.getCapacity(capacityId).get());
    }
}
