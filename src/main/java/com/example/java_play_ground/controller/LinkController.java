package com.example.java_play_ground.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_play_ground.service.app.CommandContext;
import com.example.java_play_ground.service.app.LinkConveyorToWarehouseCommand;
import com.example.java_play_ground.service.app.LinkProducerToConveyorCommand;
import com.example.java_play_ground.service.components.Conveyor;
import com.example.java_play_ground.service.components.Producer;
import com.example.java_play_ground.service.components.Warehouse;

@RestController
@RequestMapping("/api/link")
public class LinkController {

    private final LinkProducerToConveyorCommand linkCommand;
    private final LinkConveyorToWarehouseCommand linkConveyorToWarehouseCommand;
    private final CommandContext context;

    @Autowired
    public LinkController(
    		LinkProducerToConveyorCommand linkCommand,
    		LinkConveyorToWarehouseCommand linkConveyorToWarehouseCommand,
    		CommandContext context) {
        this.linkCommand = linkCommand;
        this.linkConveyorToWarehouseCommand = linkConveyorToWarehouseCommand;
        this.context = context;
    }

    @PostMapping("/producer")
    public ResponseEntity<String> linkProducerToConveyor(
            @RequestParam(name = "from") String from,
            @RequestParam(name = "to") String to) {

        context.setTempLinkFrom(from);
        context.setTempLinkTo(to);

        Producer producer = context.getProducerByName(from);
        Conveyor conveyor = context.getConveyorByName(to);

        if (producer != null && conveyor != null) {
            linkCommand.execute(0f, Logger.getGlobal());
            return ResponseEntity.ok("Linked " + from + " → " + to);
        } else {
            return ResponseEntity.badRequest().body("Link failed: component not found");
        }
    }
    
    @PostMapping("/conveyor")
    public ResponseEntity<String> linkConveyorToWarehouse(
            @RequestParam(name = "from") String from,
            @RequestParam(name = "to") String to) {

        context.setTempLinkFrom(from);
        context.setTempLinkTo(to);

        Conveyor conveyor = context.getConveyorByName(from);
        Warehouse warehouse = context.getWarehouseByName(to);

        if (conveyor != null && warehouse != null) {
            linkConveyorToWarehouseCommand.execute(0f, Logger.getGlobal());
            return ResponseEntity.ok("Linked " + from + " → " + to);
        } else {
            return ResponseEntity.badRequest().body("Link failed: component not found");
        }
    }

}