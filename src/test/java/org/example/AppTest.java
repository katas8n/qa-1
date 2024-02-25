package org.example;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import  java.io.File;
import java.io.IOException;

import org.openqa.selenium.io.FileHandler;

import static java.lang.Thread.sleep;

public class AppTest
{
    WebDriver driver;

    @Before
    public void installDriver ()
    {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        driver.get("https://booking.com");

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/form/div[1]/div[1]/div/div/div[1]/div/div/input")).sendKeys("Middle of nowhere!");

        TakesScreenshot screenshot = (TakesScreenshot) driver;

        File src = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(src, new File("screenshots/TextScreenshot" + Math.random() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.xpath("//*[@id='indexsearch']/div[2]/div/form/div[1]/div[4]/button")).submit();
        try {
            sleep(150000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void closeConnection (){
        driver.close();
    }
}
