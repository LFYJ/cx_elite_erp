package com.erp.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片处理工具类
 */
public class FileUtil {


    /**
     *上传到服务器
     */

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     *上传到本地项目
     */

    public static void uploadbenFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }







    /**
     * 删除图片
     */
    public static  void  deleteFile(String imgs){
        //分割字符串
        String[] sourceStrArray = imgs.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            File  file = new File(sourceStrArray[i]);
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }

        }

    }




}
