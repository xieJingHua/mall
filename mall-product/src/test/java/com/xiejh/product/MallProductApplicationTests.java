package com.xiejh.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.xiejh.product.entity.BrandEntity;
import com.xiejh.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    BrandService brandService;
    @Autowired
    OSS ossClient;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("");
        brandEntity.setName("小米");
        brandService.save(brandEntity);
        System.out.println("保存成功");
    }


    @Test
    public void testUpload() {
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-guangzhou.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "LTAI4GJ2kUNi1oG791inWXsL";
//        String accessKeySecret = "XsXnXzwtuhn1vobgwN6vSMb4CmtCUM";

        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest("mall-xiejh", "beautiful2.jpg", new File("C:\\Users\\xjh\\Desktop\\scene.jpg"));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
