package configuration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.PropertyLoader;

import java.io.File;
import java.net.URL;

public class BaseAppium {

    protected static AndroidDriver<WebElement> driver;

    public void init() throws Exception {

        try {
            // Carga del fichero de propiedades
            PropertyLoader loadproperty = new PropertyLoader();

            // Generación de las capabilites a nivel de driver
            DesiredCapabilities clientCapabilities = new DesiredCapabilities();
            clientCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            clientCapabilities.setCapability(MobileCapabilityType.UDID, loadproperty.loadProperties().getProperty("udid"));
            clientCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
            clientCapabilities.setCapability("automationName","UiAutomator1");
            URL url_appium = new URL("http://" + loadproperty.loadProperties().getProperty("AppiumServerIP") + ":" +
                    loadproperty.loadProperties().getProperty("AppiumServerPort") + "/wd/hub");
            File app = new File(loadproperty.loadProperties().getProperty("apkDir"), loadproperty.loadProperties().getProperty("apkName"));

            clientCapabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            driver = new AndroidDriver(url_appium, clientCapabilities);
        } catch (Exception e) {
            throw new Exception ("Error connecting to Appium service : " + e.getMessage());
        }
    }

    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }


}
