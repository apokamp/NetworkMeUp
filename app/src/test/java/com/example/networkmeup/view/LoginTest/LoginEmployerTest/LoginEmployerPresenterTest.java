package com.example.networkmeup.view.LoginTest.LoginEmployerTest;

import com.example.networkmeup.dao.EmployerDAO;
import com.example.networkmeup.daoMemory.EmployerDAOMemory;
import com.example.networkmeup.daoMemory.MemoryInitializer;
import com.example.networkmeup.view.Login.LoginEmployer.LoginEmployerPresenter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginEmployerPresenterTest {

    private LoginEmployerPresenter presenter;
    private LoginEmployerViewStub view;
    private MemoryInitializer dataInitializer;

    @Before
    public void setup() {
        dataInitializer = new MemoryInitializer();
        dataInitializer.prepareData();
        view = new LoginEmployerViewStub();
    }

    @Test
    public void testLoginProcess() {
        presenter = new LoginEmployerPresenter(view, new EmployerDAOMemory());

        // Test various scenarios for employer login

        // No input provided, attempt to login
        view.setPasswordField("Test1234!");
        presenter.onLogin();
        Assert.assertEquals("Error!", view.getShowErrorMessageTitle());
        Assert.assertEquals("Email address cannot be empty.", view.getShowErrorMessageMsg());

        // No password provided, attempt to login
        view.setEmailField("example@email.com");
        presenter.onLogin();
        Assert.assertEquals("Error!", view.getShowErrorMessageTitle());
        Assert.assertEquals("Password cannot be empty.", view.getShowErrorMessageMsg());


        // Invalid email provided, attempt to login
        view.setEmailField("invalid.email");
        view.setPasswordField("Test1234!");
        presenter.onLogin();
        Assert.assertEquals("Error!", view.getShowErrorMessageTitle());
        Assert.assertEquals("Invalid email address.", view.getShowErrorMessageMsg());

        // Valid email but incorrect password provided
        view.setEmailField("john.doe@example.com");
        view.setPasswordField("WrongPassword");
        presenter.onLogin();
        Assert.assertEquals("Error!", view.getShowErrorMessageTitle());
        Assert.assertEquals("Invalid credentials!", view.getShowErrorMessageMsg());

        // Valid email and password, successfully login
        view.setEmailField("john.doe@example.com");
        view.setPasswordField("Test1234!");
        presenter.onLogin();
        Assert.assertEquals("Login successful!", view.getSuccessfullyFinishLoginMessage());

        // Empty email and valid password
        view.setEmailField("");
        view.setPasswordField("Test1234!");
        presenter.onLogin();
        Assert.assertEquals("Error!", view.getShowErrorMessageTitle());
        Assert.assertEquals("Email address cannot be empty.", view.getShowErrorMessageMsg());

        // Valid email and empty password
        view.setEmailField("john.doe@example.com");
        view.setPasswordField("");
        presenter.onLogin();
        Assert.assertEquals("Error!", view.getShowErrorMessageTitle());
        Assert.assertEquals("Password cannot be empty.", view.getShowErrorMessageMsg());

        // Empty email and empty password
        view.setEmailField("");
        view.setPasswordField("");
        presenter.onLogin();
        Assert.assertEquals("Error!", view.getShowErrorMessageTitle());
        Assert.assertEquals("Email address cannot be empty.", view.getShowErrorMessageMsg());
    }
}




