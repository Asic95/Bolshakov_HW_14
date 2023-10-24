package Home_work_14;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFileDownload {

    @FindBy(css = "#textbox")
    private WebElement textBox;

    @FindBy(css = "#create")
    private WebElement generateFileButton;

    @FindBy(linkText = "Download")
    public WebElement downloadLink;

    public PageFileDownload(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void insertText(String fullPath) {
        textBox.sendKeys(fullPath);
    }

    public void clickGenerateButton() {
        generateFileButton.click();
    }

    public void checkUploadedFile() {
        downloadLink.click();
    }

}
