package Home_work_14;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Bolshakov_HW_14_page_object extends BaseTestClass {

    @Test(testName = "Створення, завантаження та перевірка файла")
    public void FileCreateAndDownload() throws InterruptedException, IOException {

        PageFileDownload pageFileDownload = new PageFileDownload(driver);

        pageFileDownload.insertText(DATA_FOR_FILE);
        pageFileDownload.clickGenerateButton();
        pageFileDownload.downloadLink.isDisplayed();
        pageFileDownload.checkUploadedFile();

        if (waitTillFileIsLoaded(downloadFile))
            Assert.assertTrue(ReadFromFileAndCompare(downloadFile), "File data not equal to constant!");
        else {
            System.out.println("Something went wrong!");
        }
        downloadFile.deleteOnExit(); // видаляємо завантажений файл
    }
}