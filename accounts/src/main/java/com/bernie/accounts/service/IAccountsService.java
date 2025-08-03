package com.bernie.accounts.service;

import com.bernie.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Creates a new account for the customer.
     * @param customerDto - CustomerDto object containing customer details.
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Account details for the given mobile number.
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating success or failure of the update operation.
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - input Mobile Number
     * @return boolean indicating success or failure of the delete operation.
     */
    boolean deleteAccount(String mobileNumber);
}
