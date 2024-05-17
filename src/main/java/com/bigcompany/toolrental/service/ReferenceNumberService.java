package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.ReferenceNumberGenerationException;
import com.bigcompany.toolrental.mapper.ReferenceNumberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReferenceNumberService {

    private static final String PREFIX = "RA";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String REFERENCE_NUMBER_FORMAT = "%s-%s-%04d";

    private final ReferenceNumberMapper mapper;

    @Transactional
    public String generateReferenceNumber() throws ReferenceNumberGenerationException {
        try {
            String today = LocalDate.now().format(DATE_FORMATTER);
            Integer lastNumber = mapper.getLastNumberForDate(today);
            int nextNumber = (lastNumber == null) ? 1 : lastNumber + 1;

            if (lastNumber == null) {
                mapper.insertNewDate(today, nextNumber);
            } else {
                mapper.updateLastNumberForDate(today, nextNumber);
            }

            String referenceNumber = String.format(REFERENCE_NUMBER_FORMAT, PREFIX, today, nextNumber);
            log.debug("Generated reference number: {}", referenceNumber);
            return referenceNumber;
        } catch (Exception e) {
            String msg = String.format("Failed to generate a reference number: %s", e.getMessage());
            log.error(msg, e);
            throw new ReferenceNumberGenerationException(msg, e);
        }
    }
}
