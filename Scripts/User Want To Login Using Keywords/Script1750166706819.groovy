import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

def data = TestDataFactory.findTestData('Data Files/User Login')

for (int i = 1; i <= data.getRowNumbers(); i++) {
    def username = data.getValue('username', i)
    def password = data.getValue('password', i)


    CustomKeywords.'com.orangehrm.Login.login'(username, password)

    if (WebUI.waitForElementPresent(findTestObject('Page_OrangeHRM/verify_FailedLogin'), 5, FailureHandling.OPTIONAL)) {
        KeywordUtil.markPassed("[$username] Login Gagal - Pesan error muncul")
    } else if (WebUI.waitForElementPresent(findTestObject('Page_OrangeHRM/verify_SuccessLogin'), 5, FailureHandling.OPTIONAL)) {
        KeywordUtil.markPassed("[$username] Login Berhasil - Homepage muncul")
    } else {
        KeywordUtil.markFailed("[$username] Tidak ada respon login - Tidak sukses atau gagal")
    }

    WebUI.delay(2)
    WebUI.closeBrowser()
}
