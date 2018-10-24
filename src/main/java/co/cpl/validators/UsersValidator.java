package co.cpl.validators;

import co.cpl.dto.UsersDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// This is an example of how to declare constants
// please build your own constants based on this
@Component
public class UsersValidator extends BaseValidator implements Validator {

    /*
     * This method validates the login data
     *
     * @return void no value is returning.
     */
    @Override
    public void validate(Object target, Errors errors) {

        UsersDto loadRequest = (UsersDto) target;
        /*
        if (Objects.isNull(loadRequest.getBuyer()) || StringUtils.isEmpty(loadRequest.getBuyer().getId())) {
            errors.rejectValue("buyer", Codes.BUYER_CANNOT_BE_NULL.getErrorCode());
        }

        if (Objects.isNull(loadRequest.getAmount())) {
            errors.rejectValue("amount", Codes.AMOUNT_CANNOT_BE_NULL.getErrorCode());
        }

        if (StringUtils.isEmpty(loadRequest.getCurrency())) {
            errors.rejectValue("currency", Codes.CURRENCY_CANNOT_BE_NULL.getErrorCode());
        }

        if (StringUtils.isEmpty(loadRequest.getMethod())) {
            errors.rejectValue("amount", Codes.METHOD_CANNOT_BE_NULL.getErrorCode());
        }
        */
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UsersDto.class.equals(clazz);
    }
}
