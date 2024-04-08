import app.pages.SimpleCalcPage;
import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.open;

public class ExampleTest {

  private SimpleCalcPage page;

  //Операция "="
  @DataProvider(name = "equationParams")
  public Object[][] equationParams() {
    return new Object[][]{
        {"5", "+", "3", "8"},
        {"5", "-", "3", "2"},
        {"5", "*", "3", "15"},
        {"5", "/", "2", "2.5"},
        {"0", "/", "0", "NaN"},
        {"1", "/", "0", "Infinity"},
    };
  }

  //Операция "x^2"
  @DataProvider(name = "powerParams")
  public Object[][] powerParams() {
    return new Object[][]{
        {"9", "+", "9", "324"},
        {"5", "*", "3", "225"},
        {"5", "/", "2", "6.25"},
        {"0", "/", "0", "NaN"},
        {"1", "/", "0", "Infinity"},
    };
  }

  //Операция "<-"
  @DataProvider(name = "backspaceParams")
  public Object[][] backspaceParams() {
    return new Object[][]{
        {"0", "0"},
        {"1", "0"}
    };
  }

  //Операция "C"
  @DataProvider(name = "clearParams")
  public Object[][] clearParams() {
    return new Object[][]{
        {"0", "0"},
        {"9", "9", "9", "9"},
        {"1", "1", "1"},
        {"0", "/", "0"},
        {"1", "/", "0"},
    };
  }

  @BeforeMethod
  void beforeAll() {
    page = open("https://ecalc.ru/simple/", SimpleCalcPage.class);
  }

  //Операция "="
  @Test(dataProvider = "equationParams")
  public void testEquation(String[] equationParams) {
    String result = equationParams[equationParams.length - 1];
    // Ввод чисел и проверка действия
    page.makeEquation(Arrays.copyOf(equationParams, equationParams.length - 1))
        .shouldHave(Condition.value(result));
  }

  //Операция "C"
  @Test(dataProvider = "clearParams")
  public void testClear(String[] clearParams) {
    // Ввод чисел и проверка действия
    page.makeClear(clearParams).shouldHave(Condition.value("0"));
  }

  //Операция "<-"
  @Test(dataProvider = "backspaceParams")
  public void testBackspace(String[] backspaceParams) {
    String result = backspaceParams[backspaceParams.length - 1];
    // Ввод чисел и проверка действия
    page.makeBackspace(Arrays.copyOf(backspaceParams, backspaceParams.length - 1))
        .shouldHave(Condition.value(result));
  }

  //Операция "x^2"
  @Test(dataProvider = "powerParams")
  public void testPower(String[] powerParams) {
    String result = powerParams[powerParams.length - 1];
    // Ввод чисел и проверка действия
    page.makePower(Arrays.copyOf(powerParams, powerParams.length - 1))
        .shouldHave(Condition.value(result));
  }
}
