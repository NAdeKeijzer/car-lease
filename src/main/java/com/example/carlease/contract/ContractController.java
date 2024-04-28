package com.example.carlease.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService ContractService;


    @Autowired
    public ContractController(ContractService ContractService) {
        this.ContractService = ContractService;
    }

    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract Contract) {
        Contract createdContract = ContractService.createContract(Contract);
        return new ResponseEntity<>(createdContract, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        Contract Contract = ContractService.findContractById(id);
        return ResponseEntity.ok(Contract);
    }

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> Contracts = ContractService.getAllContracts();
        return ResponseEntity.ok(Contracts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody Contract Contract) {
        Contract.setId(id);
        Contract updatedContract = ContractService.updateContract(Contract);
        return ResponseEntity.ok(updatedContract);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        ContractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }
}
