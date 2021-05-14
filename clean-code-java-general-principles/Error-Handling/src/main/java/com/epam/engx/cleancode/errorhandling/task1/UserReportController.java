package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.*;

public class UserReportController {

    private UserReportBuilder userReportBuilder;

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }

    public String getUserTotalOrderAmountView(String userId, Model model) {
        String totalMessage = getUserTotalMessage(userId);
        if (!totalMessage.equals("technicalError")) {
            model.addAttribute("userTotalMessage", totalMessage);
            totalMessage = "userTotal";
        }
        return totalMessage;
    }

    private String getUserTotalMessage(String userId)  {
        try {
            return "User Total: " + userReportBuilder.getUserTotalOrderAmount(userId) + "$";
        } catch (NoOrdersSubmittedException invalidAmountExp) {
            return "WARNING: User have no submitted orders.";
        } catch (UserNotFoundException userNotFoundExp) {
            return "WARNING: User ID doesn't exist.";
        } catch (InvalidOrderAmountException wrongOrderAmountExp) {
            return "ERROR: Wrong order amount.";
        } catch (InvalidTotalAmountException invalidAmountExp){
            return "technicalError";
        }
    }
}
