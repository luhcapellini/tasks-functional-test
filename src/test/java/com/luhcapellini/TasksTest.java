package com.luhcapellini;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

    public WebDriver acessarAplicacao() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8002/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
    
    @Test
    public void deveSalvarTarefaSucesso() {
        WebDriver driver = acessarAplicacao();
        try {

        //clicar em addtodo
        driver.findElement(By.id("addTodo")).click();

        //escrever a descrição
        driver.findElement(By.id("task")).sendKeys("Teste via selenium");

        //escrever a data
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");

        //clicar em salvar
        driver.findElement(By.id("saveButton")).click();

        //validar msg sucesso
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Success!", message);
        } finally {
            //fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naodeveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();
        try {

        //clicar em addtodo
        driver.findElement(By.id("addTodo")).click();

       //escrever a descrição
       driver.findElement(By.id("task")).sendKeys("Teste via selenium");

        //clicar em salvar
        driver.findElement(By.id("saveButton")).click();

        //validar msg sucesso
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the due date", message);
        } finally {
            //fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naodeveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();
        try {

        //clicar em addtodo
        driver.findElement(By.id("addTodo")).click();

        //escrever a data
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");

        //clicar em salvar
        driver.findElement(By.id("saveButton")).click();

        //validar msg sucesso
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the task description", message);
        } finally {
            //fechar o browser
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaComDataPassada() {
        WebDriver driver = acessarAplicacao();
        try {

        //clicar em addtodo
        driver.findElement(By.id("addTodo")).click();

        //escrever a descrição
        driver.findElement(By.id("task")).sendKeys("Teste via selenium");

        //escrever a data
        driver.findElement(By.id("dueDate")).sendKeys("10/10/2012");

        //clicar em salvar
        driver.findElement(By.id("saveButton")).click();

        //validar msg sucesso
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Due date must not be in past", message);
        } finally {
            //fechar o browser
            driver.quit();
        }
    }
    @Test
    public void deveRemoverTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();
        try {
            //inserir tarefa
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
            driver.findElement(By.id("saveButton")).click();
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

            // Remover a tarefa
            driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
            message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

        } finally {
            //fechar o browser
            driver.quit();
        }
    }
}

