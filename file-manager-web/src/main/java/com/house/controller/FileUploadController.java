package com.house.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/fileManager")
public class FileUploadController {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String uploadFile(HttpServletRequest request) throws IOException {

        //判断是否多段数据multipart/form-data
        if (!ServletFileUpload.isMultipartContent(request)) {
            return "Not a File";
        }

        FileItemFactory fileItemFactory = new DiskFileItemFactory();

        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

        String localAddr = request.getLocalAddr();
        int localPort = request.getLocalPort();
        System.out.println(localAddr + "=====" + localPort);

        //在项目根目录创建picture文件夹
        String picPath = request.getServletContext().getRealPath("picture");
        System.out.println(picPath);
        File file = new File(picPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);

            for (FileItem item : fileItems) {

                //获取文件后缀
                String originFileName = item.getName();
                if (originFileName == null || "".equals(originFileName)) {
                    continue;
                }
                String extension = originFileName.substring(originFileName.lastIndexOf("."));


                //判断是否普通表单属性，不是再做上传
                if (!item.isFormField()) {
                    //定义文件名，uuid+时间
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String time = sdf.format(new Date());
                    String uuid = UUID.randomUUID().toString();
                    String fileName = uuid+time + extension;

                    //创建文件输出流
                    FileOutputStream fos = new FileOutputStream(new File(file,fileName));

                    //拷贝文件内容到输出流文件
                    IOUtils.copy(item.getInputStream(),fos);
                    fos.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "good";
    }
}
