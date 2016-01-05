package com.labassistant.utils;

import java.io.*;

/**
 * 此类中封装一些常用的文件操作。
 * 所有方法都是静态方法，不需要生成此类的实例，
 * 为避免生成此类的实例，构造方法被申明为private类型的。
 * @author zql
 * @date 2015/09/21
 */
public final class FileUtil {

	private FileUtil(){}
    
    /**
     * 创建指定的目录。
     * 如果指定的目录的父目录不存在则创建其目录书上所有需要的父目录。
     * 若是文件，则创建该文件的父目录，若是文件夹，则创建目录
     * @param file 要创建的目录
     * @return 完全创建成功时返回true，否则返回false。
     */
    public static boolean makeDirectory(File file) {
        if (file != null) {
        	File parent = null;
        	if(file.isFile()) parent = file.getParentFile();
        	else parent = file;
            return parent.mkdirs();
        }
        return false;
    }
    
    public static boolean makeDirectory(String fileName){
    	return makeDirectory(new File(fileName));
    }
    
    /**
     * 删除指定目录及其中的所有内容。
     *
     * @param dirName 要删除的目录的目录名
     * @return 删除成功时返回true，否则返回false。
     * @since 1.0
     */
    public static boolean deleteDirectory(String dirName) {
        return deleteDirectory(new File(dirName));
    }

    /**
     * 删除指定目录及其中的所有内容。
     *
     * @param dir 要删除的目录
     * @return 删除成功时返回true，否则返回false。
     * @since 1.0
     */
    public static boolean deleteDirectory(File dir) {
        if ((dir == null) || !dir.isDirectory()) {
            throw new IllegalArgumentException("Argument " + dir +
                    " is not a directory. ");
        }

        File[] entries = dir.listFiles();
        int sz = entries.length;

        for (int i = 0; i < sz; i++) {
            if (entries[i].isDirectory()) {
                if (!deleteDirectory(entries[i])) {
                    return false;
                }
            } else {
                if (!entries[i].delete()) {
                    return false;
                }
            }
        }

        if (!dir.delete()) {
            return false;
        }
        return true;
    }
    
    /**
     * 转换成本地文件路径
     * @param path
     * @return
     */
    public static String toFilePath(String path){
    	return path.replace("/", File.separator);
    }
    
    public static String toURLPath(String path){
    	return path.replace("\\", "/");
    }

    public static String read(String path){
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line);
                sb.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取文件失败");
            e.printStackTrace();
        }
        return sb.toString();
    }
}
