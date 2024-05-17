package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.exception.ToolRentalException;
import com.bigcompany.toolrental.model.CheckoutRequest;
import com.bigcompany.toolrental.model.RentalAgreement;

public interface CheckoutService {

    RentalAgreement checkout(CheckoutRequest request) throws ToolRentalException;
}
