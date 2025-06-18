import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.model.FailureHandling


def data = TestDataFactory.findTestData('Data Files/User Login')

for (int i = 1; i <= data.getRowNumbers(); i++) {
    def username = data.getValue('username', i)

    def password = data.getValue('password', i)

	WebUI.openBrowser('')
	WebUI.navigateToUrl('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login')
	WebUI.maximizeWindow()
	WebUI.setText(findTestObject('Page_OrangeHRM/field_Username'), username)
	WebUI.setText(findTestObject('Page_OrangeHRM/field_Password'), password)
	WebUI.sendKeys(findTestObject('Page_OrangeHRM/field_Password'), Keys.chord(Keys.ENTER))

	if (WebUI.waitForElementPresent(findTestObject('Object Repository/Page_OrangeHRM/verify_FailedLogin'), 5, FailureHandling.OPTIONAL)) {
		KeywordUtil.markPassed("[$username] Login Gagal - Pesan error muncul")
	} else if (WebUI.waitForElementPresent(findTestObject('Object Repository/Page_OrangeHRM/verify_SuccessLogin'), 5, FailureHandling.OPTIONAL)) {
	    KeywordUtil.markPassed("[$username] Login Berhasil - Homepage muncul")
	} else {
	    KeywordUtil.markFailed("[$username] Tidak ada respon login - Tidak sukses atau gagal")
	}

    WebUI.delay(3)

    WebUI.closeBrowser()
}

