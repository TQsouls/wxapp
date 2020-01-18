package com.wxapp.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.util.ResourceUtils;

public class PicTransUploadUtils {
    /**
	 * @param imgData base64图片数据
	 * @param fileExtName  保存文件的扩展名png
	 * @return 成功上传服务器后的文件uri ： /group1/M00/00/00/rBDrG134dq2AFqgNAAeUvbJE5Ok464.png
	 * @throws Exception 参数含null或空字符串
	 */
	public static String transAndUpload(String imgData) {
		// String retAddr = null;
        String fileFullPath = null;
        OutputStream out = null;
        // 文件扩展名
        String fileExtName = null;
		try {
			if(imgData != null && !"".equals(imgData)){
				if(imgData.contains(",")){
                    String[] split = imgData.split(",");
                    imgData = split[1];
                    fileExtName = split[0].split(";")[0].split(":")[1].split("/")[1];
				}
				// File file = new File("");
				// String canonicalPath = file.getCanonicalPath();
				long timeMills = System.currentTimeMillis();
//				fileFullPath = canonicalPath+file.separator+timeMills+"."+fileExtName;
                // String dirPath = PicTransUploadUtils.class.getResource("").getPath();
                String dirPath = ResourceUtils.getURL("classpath:").getPath() + "update/images/";
				fileFullPath = dirPath + timeMills + "." + fileExtName;
                // boolean picGenRes = Base64Utils.GenerateImage(imgData, fileFullPath);  //10000.png
                byte[] img = Base64Util.decode(imgData);
                
                out = new FileOutputStream(fileFullPath);
                // 调整异常数据
                for (int i = 0; i < img.length; ++i) {
                    if (img[i] < 0) {
                        img[i] += 256;
                    }
                }
                out.write(img);
                
// 				if(picGenRes){
// //					String clientConf = canonicalPath+file.separator+"client.conf";
// 					String clientConf = dirPath + "client.conf";
// 					retAddr = FdfsUtils.fileUpload(clientConf, fileFullPath);
// 				}else {
// 					throw new Exception("图片转码失败，请修正后重试");
// 				}
			} else {
				throw new Exception("图片转换上传方法传入参数为null或空字符串");
			}
		} catch (Exception e) {
            e.printStackTrace();
            return "";
		} finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
            
        }
        return fileFullPath;
    }

    public static void main(String[] args) {
        String a = ",abc,def,gih";
        System.out.println(a.substring(1));
        // String[] b = a.split(",");
        // for (String string : b) {
        //     System.out.println(string);
        // }
    }
    
}