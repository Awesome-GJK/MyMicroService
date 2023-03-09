# 对象存储组件
## 1 简介
- 支持
  - 阿里云 OSS
  - 华为云 OBS

## 2 快速开始
### step1：引入依赖
```xml
<dependency>
    <groupId>com.gjk</groupId>
    <artifactId>file_storage</artifactId>
</dependency>
```

### step2：配置
```yaml
## 以阿里云 OSS 为例
custom:
  storage:
    type: oss
    config:
      domain: https://domian.aliyuncs.com
      https: true
      endpoint: oss-cn-hangzhou.aliyuncs.com
      access-key: accessKey
      secret-key: sevretKey
      bucket-name: bucketName
```

## 3 附录-自定义配置说明
| 属性                                    | 默认值 | 说明                                                   |
|---------------------------------------|-----|------------------------------------------------------|
| `custom.storage.type`                 | -   | 存储类型，目前支持 oss，obs                                    |
| `custom.storage.config.domain`        | -   | 自定义域名（不为空时，url用该值拼接）                                 |
| `custom.storage.config.https`         | -   | 是否 https（自定义域名为空时使用该值 + bucket-name + endpoint 拼装域名） |
| `custom.storage.config.endpoint`      | -   | 服务端点                                                 |
| `custom.storage.config.access-key`    | -   | 准入 key                                               |
| `custom.storage.config.secret-key`    | -   | 准入密钥                                                 |
| `custom.storage.config.bucket-name`   | -   | 存储桶                                                  |
| `custom.storage.config.properties.**` | -   | 其它配置属性（预留参数，根据具体存储类型对应的平台要求配置）                       |
