//package io.renren.common.utils;
//
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.ClientConfig;
//import com.qcloud.cos.auth.BasicCOSCredentials;
//import com.qcloud.cos.auth.COSCredentials;
//import com.qcloud.cos.exception.CosClientException;
//import com.qcloud.cos.model.ObjectMetadata;
//import com.qcloud.cos.model.PutObjectRequest;
//import com.qcloud.cos.model.PutObjectResult;
//import com.qcloud.cos.region.Region;
////import org.apache.log4j.Logger;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//
//
//public class QcloudUtil {
//    public static String uploadFile(File localFile, String Directory, String bucketName, String filename)
//    {
//        try
//        {   // 1 初始化身份信息(appid, secretId, secretKey)
//            COSCredentials cred = new BasicCOSCredentials("1255508964", "AKID3vXu11vJbdPvBBuNaXDdnhLjxwf8sHxO", "KPEo7AxWrwmNjodBcnhKgmbASVHn23oI");
//            // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
//            ClientConfig clientConfig = new ClientConfig(new Region("cos.ap-chengdu"));
//            // 3 生成 cos 客户端
//            COSClient cosClient = new COSClient(cred, clientConfig);
//            // 设置 bucket 名
////            String bucketName = "xv-shop";
//            // 指定要上传到 COS 上的路径
//            String key = "/"+Directory+"/"+filename;
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
//            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//            putObjectResult.getExpirationTimeRuleId();
//
//            System.out.print("上传结果"+putObjectResult.getETag());
//            String url="http://"+bucketName+"-1255508964.file.myqcloud.com/"+Directory+"/"+filename;
//            System.out.println("结果地址：" + "\n"+url);
//            cosClient.shutdown();
////            String url = "http://market-1255508964.file.myqcloud.com/picture/zdj/NB105.png";
//            return url;
//        }
//        catch (CosClientException e)
//        {
//            System.out.print(e.toString());
//            return  "上传失败";
//        }
//    }
//    public static String uploadFileByInput(String bucketName, String Directory, String filename, InputStream input){
////        PoolingHttpClientConnectionManager p= new PoolingHttpClientConnectionManager();
////        p.setValidateAfterInactivity(10000);
//        String url= null;
//        try {
//            // 1 初始化身份信息(appid, secretId, secretKey)
//            COSCredentials cred = new BasicCOSCredentials("1255508964", "AKID3vXu11vJbdPvBBuNaXDdnhLjxwf8sHxO", "KPEo7AxWrwmNjodBcnhKgmbASVHn23oI");
//            // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
//            ClientConfig clientConfig = new ClientConfig(new Region("cos.ap-chengdu"));
//            // 3 生成 cos 客户端
//            COSClient cosClient = new COSClient(cred, clientConfig);
//            // 指定要上传到 COS 上的路径
//            String path = "/" + Directory + "/" + filename;
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            // 设置输入流长度
//            objectMetadata.setContentLength(input.available());
//            // 设置 Content type, 默认是 application/octet-stream
//            objectMetadata.setContentType("application/x-jpg");
//
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, input, objectMetadata);
//            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//            putObjectResult.getExpirationTimeRuleId();
//
////            System.out.print("上传结果" + putObjectResult.getETag());
//            cosClient.shutdown();
//            url = "http://" + bucketName + "-1255508964.file.myqcloud.com" + path;
////            System.out.println("结果地址：" + url);
//        }catch (CosClientException e)
//        {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        return url;
//    }
//}
