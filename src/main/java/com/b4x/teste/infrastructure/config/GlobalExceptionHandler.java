package com.b4x.teste.infrastructure.config;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

// Básicamente nesse método aqui passa todas as exceptions e elas são tratadas para facilitar a leitura, eu normalmente me conecto a alguma API de algum serviço
// de msg como por exemplo, wpp, discord ou até mesmo email e recebo a exception sempre que ela acontece na prod, essa parte de observabilidade é muito importante
// mas como é um teste eu não coloquei nada para não expor meus tokens.
// Se eu colocasse isso aqui, eu teria que colocar um token de acesso para poder enviar essa mensagem para algum serviço de observabilidade.
@RestControllerAdvice
@Profile("prod")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        StackTraceElement[] stackTrace = e.getStackTrace();

        String pacoteBase = "com.b4x";
        StringBuilder stackTraceFiltered = new StringBuilder();

        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            if (className.startsWith(pacoteBase)) {
                String methodName = element.getMethodName();
                stackTraceFiltered.append("Classe: ").append(className)
                        .append("\nMétodo: ").append(methodName)
                        .append("\nLinha: ").append(element.getLineNumber())
                        .append("\n===========================\n");
            }
        }

        String errorMessage = "Erro no endpoint: " + request.getRequestURI() +
                "\nMétodo: " + request.getMethod() +
                "\nMensagem: " + e.getMessage() +
                "\nCausa: " + e.getCause() +
                "\n===========================" +
                "\nStack Trace Filtrado: " + stackTraceFiltered.toString();

        System.out.println("errorMessage: " + errorMessage);
        return errorMessage;
    }
}
