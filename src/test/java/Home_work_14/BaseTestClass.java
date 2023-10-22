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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class BaseTestClass {
    private static final String URL = "https://demo.seleniumeasy.com/generate-file-to-download-demo.html";
    public WebDriver driver;
    public WebDriverWait wait;
    public String downloadPath = System.getProperty("user.home") + "\\Downloads\\";
    File downloadFile = new File(downloadPath + "easyinfo.txt");
    static final String DATA_FOR_FILE = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";


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

        List<String> staticData = new ArrayList<>();
        staticData.add(0, DATA_FOR_FILE);

        return fileData.equals(staticData);
    }
}
