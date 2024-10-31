package com.server.JavaServer.controller;

import com.server.JavaServer.model.*;
import com.server.JavaServer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/part")
public class UniversalComponentsController {

    @Autowired private CPUService cpuService;
    @Autowired private GPUService gpuService;
    @Autowired private MotherboardService motherboardService;
    @Autowired private MemoryService memoryService;
    @Autowired private PSUService psuService;
    @Autowired private StorageService storageService;
    @Autowired private ChassisService chassisService;
    @Autowired private CpuCoolerService coolerService;

    // Get all parts or a part by ID based on partName query
    @GetMapping
    public List<?> getAllPartsOrById(
        @RequestParam String partName,
        @RequestParam(required = false) Long id,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "50") int items
    ) {
        PageRequest pageRequest = PageRequest.of(page, items);
        
        switch (partName.toLowerCase()) {
            case "cpu":
                return id != null ? cpuService.findByIdAsPage(id, pageRequest).getContent() : cpuService.findAll(pageRequest).getContent();
            case "gpu":
                return id != null ? gpuService.findByIdAsPage(id, pageRequest).getContent() : gpuService.findAll(pageRequest).getContent();
            case "motherboard":
                return id != null ? motherboardService.findByIdAsPage(id, pageRequest).getContent() : motherboardService.findAll(pageRequest).getContent();
            case "memory":
                return id != null ? memoryService.findByIdAsPage(id, pageRequest).getContent() : memoryService.findAll(pageRequest).getContent();
            case "psu":
                return id != null ? psuService.findByIdAsPage(id, pageRequest).getContent() : psuService.findAll(pageRequest).getContent();
            case "storage":
                return id != null ? storageService.findByIdAsPage(id, pageRequest).getContent() : storageService.findAll(pageRequest).getContent();
            case "chassis":
                return id != null ? chassisService.findByIdAsPage(id, pageRequest).getContent() : chassisService.findAll(pageRequest).getContent();
            case "cooler":
                return id != null ? coolerService.findByIdAsPage(id, pageRequest).getContent() : coolerService.findAll(pageRequest).getContent();
            default:
                throw new IllegalArgumentException("Invalid part name: " + partName);
        }
    }

    // Create or update a part based on partName
    @PostMapping
    public Object createOrUpdatePart(@RequestParam String partName, @RequestBody Object part) {
        switch (partName.toLowerCase()) {
            case "cpu":
                return cpuService.saveOrUpdate((CPU) part);
            case "gpu":
                return gpuService.saveOrUpdate((GPU) part);
            case "motherboard":
                return motherboardService.saveOrUpdate((Motherboard) part);
            case "memory":
                return memoryService.saveOrUpdate((Memory) part);
            case "psu":
                return psuService.saveOrUpdate((PSU) part);
            case "storage":
                return storageService.saveOrUpdate((Storage) part);
            case "chassis":
                return chassisService.saveOrUpdate((Chassis) part);
            case "cooler":
                return coolerService.saveOrUpdate((CpuCooler) part);
            default:
                throw new IllegalArgumentException("Invalid part name: " + partName);
        }
    }

    // Delete a part by ID based on partName
    @DeleteMapping
    public void deletePart(@RequestParam String partName, @RequestParam Long id) {
        switch (partName.toLowerCase()) {
            case "cpu":
                cpuService.delete(id);
                break;
            case "gpu":
                gpuService.delete(id);
                break;
            case "motherboard":
                motherboardService.delete(id);
                break;
            case "memory":
                memoryService.delete(id);
                break;
            case "psu":
                psuService.delete(id);
                break;
            case "storage":
                storageService.delete(id);
                break;
            case "chassis":
                chassisService.delete(id);
                break;
            case "cooler":
                coolerService.delete(id);
                break;
            default:
                throw new IllegalArgumentException("Invalid part name: " + partName);
        }
    }
}
