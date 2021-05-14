package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private static final int MINIMUM_ACCOUNT_NAME_LENGTH = 5;

    private static final int MINIMUM_ACCOUNT_PASSWORD_LENGTH = 8;

    private PasswordChecker passwordChecker;

    private AccountManager accountManager;

    public void register(Account account) {
        validateAccountNameAndPassword(account);
        setAccountAddressesAndCreationDate(account);
        accountManager.createNewAccount(account);
    }

    private void validateAccountNameAndPassword(Account account) {
        validateAccountNameLength(account.getName());
        validateAccountPasswordLength(account.getPassword());
        checkAccountPassword(account.getPassword());
    }

    private void validateAccountNameLength(String name) {
        if(name.length() <= MINIMUM_ACCOUNT_NAME_LENGTH) {
            throw new WrongAccountNameException();
        }
    }

    private void validateAccountPasswordLength(String password) {
        if(password.length() <= MINIMUM_ACCOUNT_PASSWORD_LENGTH) {
            throw new TooShortPasswordException();
        }
    }

    private void checkAccountPassword(String password) {
        if(passwordChecker.validate(password) != OK) {
            throw new WrongPasswordException();
        }
    }

    private void setAccountAddressesAndCreationDate(Account account){
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
        account.setCreatedDate(new Date());
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

}
