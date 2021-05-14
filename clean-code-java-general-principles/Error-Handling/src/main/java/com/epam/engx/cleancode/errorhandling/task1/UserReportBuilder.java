package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.*;

import java.util.List;

public class UserReportBuilder {

    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId) throws InvalidTotalAmountException {

        if (userDao == null)
            throw new UserDaoNotFoundException();

        User user = validateUserById(userId);

        List<Order> orders = user.getAllOrders();

        return getSumOfOrdersTotal(orders);
    }

    private User validateUserById(String userId) throws UserNotFoundException {
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    private Double getSumOfOrdersTotal(List<Order> orders) throws NoOrdersSubmittedException, InvalidOrderAmountException {
        validateOrdersList(orders);
        double sum = 0.0;
        for (Order order : orders) {
            if (order.isSubmitted()) {
                sum = calculateOrderTotal(sum, order.total());
            }
        }
        return sum;
    }

    private double calculateOrderTotal(double sum, double total) throws InvalidOrderAmountException {
        validateOrdersTotalAmount(total);
        return sum += total;
    }

    private void validateOrdersList(List<Order> orders) throws NoOrdersSubmittedException {
        if (orders.isEmpty()) {
            throw new NoOrdersSubmittedException();
        }
    }

    private void validateOrdersTotalAmount(Double total) throws InvalidOrderAmountException {
        if (total < 0) {
            throw new InvalidOrderAmountException();
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
