# jcommon

Spring 基础项目依赖

## Maven中央仓库发布

### 1. 注册OSSRH/JIRA账号

https://central.sonatype.com/

### 2. 按照说明新建一个ISSUE

* `Group Id`需填写可验证所有权的域名，没有域名的可以应github page
* 提交后会让你在域名解析上添加一个TXT记录

### 3. gradle配置

* 使用GPG工具生成密钥，并将公钥匙发送到`keyserver.ubuntu.com`供后续验证
* 编辑本第`~/.gradle/gradle.properties`如下

```properties
signing.keyId=F1234567
signing.password=passwd
signing.secretKeyRingFile=~/sub.gpg

ossrhUsername=clemon
ossrhPassword=passwd123
```
