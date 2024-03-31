package ru.aydar.drivers;

import com.codeborne.selenide.WebDriverProvider;
import ru.aydar.config.DriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    static DriverConfig config = ConfigFactory.create(DriverConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mc = new MutableCapabilities();
        mc.merge(capabilities);

        mc.setCapability("browserstack.user", config.userName());
        mc.setCapability("browserstack.key", config.accessKey());
        mc.setCapability("app", config.app());
        mc.setCapability("device", config.device());
        mc.setCapability("os_version", config.osVersion());
        mc.setCapability("project", config.project());
        mc.setCapability("build", config.build());
        mc.setCapability("name", config.testName());

        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), mc);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
