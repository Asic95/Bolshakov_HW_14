package Home_work_14;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Bolshakov_HW_14 extends BaseTestClass {

    @Test(testName = "Створення, завантаження та перевірка файла")
    public void FileCreateAndDownload() throws InterruptedException, IOException {
        driver.findElement(By.cssSelector("#textbox")).sendKeys(DATA_FOR_FILE);
        driver.findElement(By.cssSelector("#create")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download")));
        driver.findElement(By.linkText("Download")).click();

        if (waitTillFileIsLoaded(downloadFile)){
        Assert.assertTrue(ReadFromFileAndCompare(downloadFile), "File data not equal to constant!");
        } else {
            System.out.println("Something went wrong!");
        }

        downloadFile.deleteOnExit(); // видаляємо завантажений файл

    }

}