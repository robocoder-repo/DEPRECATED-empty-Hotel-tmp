
package com.hotelreservation.viewmodel;

import com.hotelreservation.model.Staff;
import com.hotelreservation.service.LoginService;

public class LoginViewModel {

    private LoginService loginService;

    public LoginViewModel() {
        loginService = new LoginService();
    }

    public Staff authenticate(String staffId, String password) {
        return loginService.authenticateStaff(staffId, password);
    }
}
