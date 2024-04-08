package app.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class SimpleCalcPage {

  @FindBy(className = "calculator__key--equal")
  SelenideElement equal;

  @FindBy(className = "calculator__display")
  SelenideElement display;

  @FindBy(className = "calculator__clear")
  SelenideElement clear;

  @FindBy(className = "calculator__backspace")
  SelenideElement backspace;

  @FindBy(className = "calculator__power")
  SelenideElement power;

  private void clickValues(String[] values) {
    for (String value : values) {
      $("[value='" + value + "']").click();
    }
  }

  public SelenideElement makeEquation(String[] values) {
    clickValues(values);
    equal.click();
    return display;
  }

  public SelenideElement makeClear(String[] values) {
    clickValues(values);
    clear.click();
    return display;
  }

  public SelenideElement makeBackspace(String[] values) {
    clickValues(values);
    backspace.click();
    return display;
  }

  public SelenideElement makePower(String[] values) {
    clickValues(values);
    equal.click();
    power.click();
    return display;
  }
}