package com.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.util.StringUtil;
import com.springboot.domain.Result;
import org.omg.PortableServer.IdUniquenessPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author 申浩东
 */
@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Value("${server.port}")
    private String port;
    private  static final String ip="http://localhost";
    /**
     * 文件上传(浏览器请求文件,下载到本地服务器)
     * @param file
     * @return
     */
    @CrossOrigin
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        String filename = file.getOriginalFilename();
        String uuid = IdUtil.fastSimpleUUID();
        String property = System.getProperty("user.dir")+"/springboot/src/main/resources/file/";
        try {
            /* 判断是否是空文件*/
            if(!file.isEmpty()){
                file.transferTo(new File(property + uuid+"_"+filename));
            }

        }catch(IOException e){
            throw new RuntimeException();
        }
        return Result.success(ip+":"+port+"/files/download/"+uuid);
    }

    /**
     * 文件下载(本地文件以字节流的形式发送给浏览器)
     * @param response
     * @param flag
     * @return
     */
    @GetMapping("/download/{flag}")
    public void download(HttpServletResponse response, @PathVariable("flag") String flag){
        OutputStream os=null;
        /*定义文件上传的根路径*/
        String srcpath = System.getProperty("user.dir") + "/springboot/src/main/resources/file/";
        /*获取该目录的所有文件*/
        List<String> strings = FileUtil.listFileNames(srcpath);
        /**
         * strings.stream().filter   遍历并且升序排序
         * 遍历所有文件 name.contains(flag) ,如果有文件名包含传参内容 则为true
         * findAny 取第一个
         * */
        String str = strings.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        /*如果该文件不为空,或者长度不为空*/
        try {
            if (StringUtil.isNotEmpty(str)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(str, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(srcpath + str);
                os= response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
