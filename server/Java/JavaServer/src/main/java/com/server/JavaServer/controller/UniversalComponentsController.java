package com.server.JavaServer.controller;

import com.server.JavaServer.model.*;
import com.server.JavaServer.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    
    @Autowired private ObjectMapper objectMapper;

    // Get all parts or a part by ID based on partName query
    @GetMapping
    public List<?> getPartsQueried(
        @RequestParam String partName,
        @RequestParam(required = false) Long id,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "50") int items
    ) {
        return getPartsContent(partName, id, page, items);
    }
    
    @GetMapping("/view/{partName}")
    public List<?> getPartsPathed(
        @PathVariable String partName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "50") int items
    ) {
        return getPartsContent(partName, null, page, items);
    }

    // Different version
    @GetMapping("/view/{partName}/{id}")
    public List<?> getPartsPathedWithId(
        @PathVariable String partName,
        @PathVariable Long id,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "50") int items
    ) {
        return getPartsContent(partName, id, page, items);
    }

    // Common logic for handling both methods
    private List<?> getPartsContent(String partName, Long id, int page, int items) {
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
            case "cpu_cooler":
                return id != null ? coolerService.findByIdAsPage(id, pageRequest).getContent() : coolerService.findAll(pageRequest).getContent();
            default:
                throw new IllegalArgumentException("Invalid part name: " + partName);
        }
    }

    // Insert a new part based on partName
    @PostMapping("/insert/{partName}")
    public Object insertPart(@PathVariable String partName, @RequestBody Map<String, Object> partData) {
        switch (partName.toLowerCase()) {
            case "cpu":
                CPU cpu = objectMapper.convertValue(partData, CPU.class);
                return cpuService.insert(cpu);
            case "gpu":
                GPU gpu = objectMapper.convertValue(partData, GPU.class);
                return gpuService.insert(gpu);
            case "motherboard":
                Motherboard motherboard = objectMapper.convertValue(partData, Motherboard.class);
                return motherboardService.insert(motherboard);
            case "memory":
                Memory memory = objectMapper.convertValue(partData, Memory.class);
                return memoryService.insert(memory);
            case "psu":
                PSU psu = objectMapper.convertValue(partData, PSU.class);
                return psuService.insert(psu);
            case "storage":
                Storage storage = objectMapper.convertValue(partData, Storage.class);
                return storageService.insert(storage);
            case "chassis":
                Chassis chassis = objectMapper.convertValue(partData, Chassis.class);
                return chassisService.insert(chassis);
            case "cpu_cooler":
                CpuCooler cpucooler = objectMapper.convertValue(partData, CpuCooler.class);
                return coolerService.insert(cpucooler);
            default:
                throw new IllegalArgumentException("Invalid part name: " + partName);
        }
    }


    // Update an existing part based on partName
    @PatchMapping("/update/{partName}/{id}")
    public Object updatePart(@PathVariable String partName, @PathVariable Long id, @RequestBody Map<String, Object> partData) {
        switch (partName.toLowerCase()) {
            case "cpu":
                CPU cpu = objectMapper.convertValue(partData, CPU.class);
                return cpuService.update(id, cpu);
            case "gpu":
                GPU gpu = objectMapper.convertValue(partData, GPU.class);
                return gpuService.update(id, gpu);
            case "motherboard":
                Motherboard motherboard = objectMapper.convertValue(partData, Motherboard.class);
                return motherboardService.update(id, motherboard);
            case "memory":
                Memory memory = objectMapper.convertValue(partData, Memory.class);
                return memoryService.update(id, memory);
            case "psu":
                PSU psu = objectMapper.convertValue(partData, PSU.class);
                return psuService.update(id, psu);
            case "storage":
                Storage storage = objectMapper.convertValue(partData, Storage.class);
                return storageService.update(id, storage);
            case "chassis":
                Chassis chassis = objectMapper.convertValue(partData, Chassis.class);
                return chassisService.update(id, chassis);
            case "cpu_cooler":
                CpuCooler cpucooler = objectMapper.convertValue(partData, CpuCooler.class);
                return coolerService.update(id, cpucooler);
            default:
                throw new IllegalArgumentException("Invalid part name: " + partName);
        }
    }


    // Delete a part by ID based on partName
    @DeleteMapping("/delete/{partName}/{id}")
    public void deletePart(@PathVariable String partName, @PathVariable Long id) {
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
            case "cpu_cooler":
                coolerService.delete(id);
                break;
            default:
                throw new IllegalArgumentException("Invalid part name: " + partName);
        }
    }
}
