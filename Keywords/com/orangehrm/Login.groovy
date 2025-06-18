package com.orangehrm

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil

public class Login {

    @Keyword
    def login(String username, String password) {
        KeywordUtil.logInfo("[LOGIN] Membuka browser")
        WebUI.openBrowser('')

        KeywordUtil.logInfo("[LOGIN] Navigasi ke halaman login")
        WebUI.navigateToUrl('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login')

        KeywordUtil.logInfo("[LOGIN] Memaksimalkan window")
        WebUI.maximizeWindow()

        KeywordUtil.logInfo("[LOGIN] Mengisi username: " + username)
        WebUI.setText(findTestObject('Page_OrangeHRM/field_Username'), username)

        KeywordUtil.logInfo("[LOGIN] Mengisi password")
        WebUI.setText(findTestObject('Page_OrangeHRM/field_Password'), password)

        KeywordUtil.logInfo("[LOGIN] Menekan tombol Enter")
        WebUI.sendKeys(findTestObject('Page_OrangeHRM/field_Password'), Keys.chord(Keys.ENTER))
    }

    @Keyword
    def logout() {
        KeywordUtil.logInfo("[LOGOUT] Klik dropdown user")
        WebUI.click(findTestObject('Page_OrangeHRM/dropdown_User'))

        KeywordUtil.logInfo("[LOGOUT] Klik tombol logout")
        WebUI.click(findTestObject('Page_OrangeHRM/link_Logout'))

        KeywordUtil.logInfo("[LOGOUT] Menutup browser")
        WebUI.closeBrowser()
    }
}
