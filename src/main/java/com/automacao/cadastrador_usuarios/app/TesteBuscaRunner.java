package com.automacao.cadastrador_usuarios.app;

import com.automacao.cadastrador_usuarios.util.ModalClose;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TesteBuscaRunner {

    private static final By FORM_EMAIL = By.id("input-1");
    private static final By FORM_PASSWORD = By.id("password");
    private static final By BUTTON_ENTER = By.xpath("//button[span='Entrar']");
    private static final By GENERAL = By.xpath("//a[span='Geral']");
    private static final By ENTERPRISE = By.xpath("//a[normalize-space()='Empresa']");
    private static final By BUTTON_EDIT = By.xpath("//button[@title='Editar']");
    private static final By USERS = By.xpath("//a[normalize-space()='Usuários']");
    private static final By SEARCH_NAME = By.xpath("//input[@placeholder='Buscar por nome']");
    private static final By SEARCH_CPF = By.xpath("//input[@placeholder='Buscar por CPF']");
    private static final By SEARCH = By.xpath("//button[normalize-space()='Pesquisar']");
    private static final By CARREGANDO = By.cssSelector("div.vld-overlay.is-active");

    private static final String LOGIN_SP = env("CF_LOGIN_SP", "");
    private static final String PASSWORD_SP = env("CF_SENHA_SP", "");

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        ModalClose modal = new ModalClose();

        driver.manage().window().maximize();

        try {
            driver.get("https://cury.cfobras.com.br/login");

            driver.findElement(FORM_EMAIL).sendKeys(LOGIN_SP);
            driver.findElement(FORM_PASSWORD).sendKeys(PASSWORD_SP);
            driver.findElement(BUTTON_ENTER).click();

            modal.fecharModal(driver);

            wait.until(ExpectedConditions.elementToBeClickable(GENERAL)).click();
            wait.until(ExpectedConditions.elementToBeClickable(ENTERPRISE)).click();

            esperarCarregamento(wait);
            modal.fecharModal(driver);

            wait.until(ExpectedConditions.elementToBeClickable(BUTTON_EDIT)).click();

            esperarCarregamento(wait);

            wait.until(ExpectedConditions.elementToBeClickable(USERS)).click();

            wait.until(ExpectedConditions.elementToBeClickable(SEARCH_NAME)).sendKeys("Lucas Gabriel");
            wait.until(ExpectedConditions.elementToBeClickable(SEARCH)).click();

            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }

    private static String env(String nome, String padrao) {
        String valor = System.getenv(nome);
        return (valor == null || valor.isBlank()) ? padrao : valor;
    }

    private static void esperarCarregamento(WebDriverWait wait) {
        wait.until(d -> d.findElements(CARREGANDO).stream().noneMatch(WebElement::isDisplayed));
    }

}
