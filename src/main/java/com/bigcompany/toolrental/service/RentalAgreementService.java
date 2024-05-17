package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.CreateRentalAgreementException;
import com.bigcompany.toolrental.exception.RentalAgreementNotFoundException;
import com.bigcompany.toolrental.mapper.RentalAgreementMapper;
import com.bigcompany.toolrental.model.RentalAgreement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalAgreementService {

    private final RentalAgreementMapper mapper;
    private final ReferenceNumberService referenceNumberService;

    @Transactional
    public RentalAgreement createRentalAgreement(RentalAgreement agreement) throws CreateRentalAgreementException {
        try {
            String ref = referenceNumberService.generateReferenceNumber();
            agreement.setReferenceNumber(ref);
            mapper.insert(agreement);
            return agreement;
        } catch (Exception e) {
            throw new CreateRentalAgreementException("Failed to create rental agreement", e);
        }
    }

    @Transactional(readOnly = true)
    public List<RentalAgreement> findAll() {
        return mapper.findAll();
    }

    @Transactional(readOnly = true)
    public RentalAgreement findById(Long id) {
        return mapper.findById(id).orElseThrow(() ->
                new RentalAgreementNotFoundException(String.format("Rental agreement with id %s not found", id)));
    }

    @Transactional(readOnly = true)
    public RentalAgreement findByReferenceNumber(String ref) {
        return mapper.findByReferenceNumber(ref).orElseThrow(() ->
                new RentalAgreementNotFoundException(String.format("Rental agreement with reference number %s not found", ref)));
    }
}
