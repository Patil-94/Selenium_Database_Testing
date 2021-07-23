package com.bridgelabz.database.pages;

import com.bridgelabz.database.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends BaseClass  {

    /** Description - Using FindBy for locating elements */

    @FindBy(xpath = "//a[normalize-space()='Sign in']")
    WebElement userSignIn;

    @FindBy(id = "username")
    WebElement userEmailID;

    @FindBy(id = "password")
    WebElement userPassword;

    @FindBy(xpath = "//button[normalize-space()='Sign in']")
    WebElement loginBtn;


    /** create a parameterized constructor.
     * initialization
     */
    public Login(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    /** We have used send keys method for entering credentials
     *  Using click method  to Login in page
     */

    public void loginTo_Application(String username,String password) throws InterruptedException {
        Thread.sleep(500);
        userSignIn.click();
        Thread.sleep(500);
        userEmailID.sendKeys(username);
        Thread.sleep(500);
        userPassword.sendKeys(password);
        Thread.sleep(500);
        loginBtn.click();
        Thread.sleep(2000);

    }
}
