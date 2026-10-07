package com.automacao.cadastrador_usuarios.util;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalClose {

    private static final By MODAL_ABERTO = By.cssSelector("div.modal.show");
    private static final By FECHAR_MODAL = By.cssSelector("div.modal.show button.close");

    private static final Duration ESPERA_APARECER = Duration.ofSeconds(3);
    private static final Duration ESPERA_SUMIR = Duration.ofSeconds(10);

    public boolean fecharModal(WebDriver driver) {
        WebElement botaoFechar;
        try {
            botaoFechar = new WebDriverWait(driver, ESPERA_APARECER)
                    .until(ExpectedConditions.elementToBeClickable(FECHAR_MODAL));
        } catch (TimeoutException naoApareceu) {
            return false;
        }

        botaoFechar.click();

        new WebDriverWait(driver, ESPERA_SUMIR)
                .until(ExpectedConditions.invisibilityOfElementLocated(MODAL_ABERTO));
        return true;
    }
}