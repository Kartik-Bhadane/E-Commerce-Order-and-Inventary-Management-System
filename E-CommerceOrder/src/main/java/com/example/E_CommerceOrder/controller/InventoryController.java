package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.InventoryRequestdto;
import com.example.E_CommerceOrder.entity.Inventory;
import com.example.E_CommerceOrder.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

 
    @PostMapping
    public Inventory addStock(@RequestBody InventoryRequestdto dto) {
        return inventoryService.addStock(dto);
    }


    @PutMapping("/{id}")
    public Inventory updateStock(
            @PathVariable int id,
            @RequestBody InventoryRequestdto dto) {
        return inventoryService.updateStock(id, dto);
    }

   
    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable int id) {
        return inventoryService.getInventoryById(id);
    }

  
    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

 
    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable int id) {
        inventoryService.deleteInventory(id);
        return "Inventory deleted successfully";
    }
}