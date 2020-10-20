package de.clayntech.excelsea.example;

import de.clayntech.excelsea.example.fx.LoginApplication;
import de.clayntech.excelsea.fw.Excelsea;
import de.clayntech.excelsea.fw.ExcelseaContext;
import de.clayntech.excelsea.fw.fx.ExcelseaFXContext;

public class Example {
    public static void main(String[] args) {
        Excelsea.init(new ExcelseaFXContext("Example", LoginApplication.class));
    }
}
