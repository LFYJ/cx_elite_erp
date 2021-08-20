package com.erp.util;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.core.io.ClassPathResource;


import javax.servlet.http.HttpServletResponse;

import java.util.Map;

public class WordUtils {

    public static void get( HttpServletResponse response,
                            Map<String, Object> dataMap,
                            String fileName) {

        try {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/msword");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=xx分析.doc");

        //创建配置实例对象
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        //设置编码
        configuration.setDefaultEncoding("UTF-8");
        //加载需要装填的模板
        //configuration.setClassForTemplateLoading(this.getClass(), "/");
        ClassPathResource classPathResource = new ClassPathResource("/templates/");
        configuration.setDirectoryForTemplateLoading(classPathResource.getFile());

        //设置对象包装器
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));

        //设置异常处理器
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        //获取ftl模板对象
        Template template = configuration.getTemplate("template.ftl");
        //输出文档
        StringBuilder stringBuilder = new StringBuilder(fileName);

        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(stringBuilder.toString().getBytes("GBK"), "ISO-8859-1"));
            response.setCharacterEncoding("utf-8");//处理乱码问题
            //生成Word文档
            template.process(dataMap, response.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.flushBuffer();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
}





















