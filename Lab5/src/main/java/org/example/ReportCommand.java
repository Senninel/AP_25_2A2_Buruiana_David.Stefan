package org.example;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command{
    public void execute(String[] args, ImageRepository repo) throws Exception{
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        File templatesDir = new File("templates");
        System.out.println("Looking for templates in: " + templatesDir.getAbsolutePath());
        cfg.setDirectoryForTemplateLoading(new File("templates"));


        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template =  cfg.getTemplate("report.ftl");

        Map<String, Object> data = new HashMap<>();
        data.put("images", repo.getImages());

        try(Writer writer = new FileWriter("report.html");){
            template.process(data, writer);
            System.out.println("Report generat: report.html");
        }
    }
}
