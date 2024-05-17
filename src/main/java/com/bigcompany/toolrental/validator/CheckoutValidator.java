package com.bigcompany.toolrental.validator;

import com.bigcompany.toolrental.exception.ToolRentalException;
import com.bigcompany.toolrental.exception.RentalDayCountException;
import com.bigcompany.toolrental.exception.DiscountPercentRangeException;
import com.bigcompany.toolrental.model.CheckoutRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckoutValidator {

    /**
     * Validates the checkout request.
     * @param request the checkout request
     * @throws ToolRentalException if there are any checkout invalidations
     */
    public void validateCheckoutRequest(CheckoutRequest request) throws ToolRentalException {
        validateDiscountPercent(request.getDiscountPercent());
        validateRentalDays(request.getRentalLengthInDays());
    }

    /**
     * Validates the rental day count.
     * @param rentalDays the number of days for the rental
     * @throws RentalDayCountException if the rental day count is less than 1
     */
    public void validateRentalDays(int rentalDays) throws RentalDayCountException {
        if (rentalDays < 1) {
            throw new RentalDayCountException();
        }
    }

    /**
     * Validates the discount percentage.
     * @param discountPercent the discount percentage to apply
     * @throws DiscountPercentRangeException if the discount percent is not between 0 and 100
     */
    public void validateDiscountPercent(int discountPercent) throws DiscountPercentRangeException {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new DiscountPercentRangeException(discountPercent);
        }
    }
}
