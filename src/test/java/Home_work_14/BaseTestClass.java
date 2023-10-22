package Home_work_14;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class BaseTestClass {
    private static final String URL = "https://demo.seleniumeasy.com/generate-file-to-download-demo.html";
    public WebDriver driver;
    public WebDriverWait wait;
    public String downloadPath = System.getProperty("user.home") + "\\Downloads\\";
    File downloadFile = new File(downloadPath + "easyinfo.txt");
    static File localFile = new File("textExample.txt");

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    public static String readDataFromLocalFile(File file) throws IOException {              // читаємо дані з локального файлу textExample.txt

        FileUtils.readLines(file, Charset.defaultCharset());
        List<String> staticData = FileUtils.readLines(file, Charset.defaultCharset());

        if (staticData.size() > 1) {
            return staticData.toString().replace(",", "\n").replace("[", "").replace("]", "").replace(" ", "");
        } else {
            return staticData.toString().replace("[", "").replace("]", "");
        }
    }

    public static Boolean waitTillFileIsLoaded(File file) throws InterruptedException {     // очікуємо поки файл завантажиться
        int count = 0;
        while (count != 60) {
            if (!file.exists()) {
                Thread.sleep(1000);
                count++;
            } else {
                break;
            }
        }

        count = 0;
        while (count < 60) {
            long lengthBefore = file.length();
            Thread.sleep(1000);
            long lengthAfter = file.length();
            if (lengthBefore == lengthAfter) {
                return true;
            } else {
                count++;
            }
        }
        return false;
    }

    public static Boolean ReadFromFileAndCompare(File file) throws IOException {            // Зчитауємо дані з файлу та порівнюємо з константою

        FileUtils.readLines(file, Charset.defaultCharset());
        List<String> fileData = FileUtils.readLines(file, Charset.defaultCharset());
        String data1 = "";

        if (fileData.size() > 1) {
            data1 = fileData.toString().replace(",", "\n").replace("[", "").replace("]", "").replace(" ", "");
        } else {
            data1 = fileData.toString().replace("[", "").replace("]", "");
        }
        return data1.equals(readDataFromLocalFile(localFile));
    }
}
