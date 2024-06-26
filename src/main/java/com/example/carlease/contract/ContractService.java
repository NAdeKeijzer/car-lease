package com.example.carlease.contract;

import com.example.carlease.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContractService {
    private final ContractRepository contractRepository;
    //TODO: get Car info from CarService, Customer info from Customer Service and a LeaseRate from the LeaseRateCalculator
    //TODO:create unit tests
    @Autowired
    public ContractService(ContractRepository contractRepository, CustomerService customerService) {
        this.contractRepository = contractRepository;
    }

    public Contract createContract(Contract contract) {

        return contractRepository.save(contract);
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract findContractById(Long id) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        return optionalContract.orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

}
