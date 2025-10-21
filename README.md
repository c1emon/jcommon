# jcommon

Spring 基础项目依赖

## Maven中央仓库发布

⚠️自2025年6月30日起，OSSRH已经EOF，转而使用maven central repository。截止2025年10月，暂无官方gradle推送插件实现，使用jreleaser实现推送

参考列表：
1. [发布Jar到Maven中央仓库--Maven版(最新方式)](https://blog.hanqunfeng.com/2024/08/01/mvn-depoly-maven-center-repository-new/)
2. [发布Jar到Maven中央仓库--Gradle版(最新方式)](https://blog.hanqunfeng.com/2024/08/02/gradle-depoly-maven-center-repository-new/)
3. [jReleaser gradle example](https://jreleaser.org/guide/latest/examples/maven/maven-central.html#_gradle)


### 1. 注册账号

在https://central.sonatype.com/使用真实的邮箱注册一个账号

### 2. 创建Namespace

* `Namespace`就是`Group Id`，需填写可验证所有权的域名，没有域名的可以用github page
* 提交后按要求在域名解析上添加一个TXT记录以进行验证

### 3. jReleaser配置

* 使用GPG工具生成密钥，并将公钥匙发送到`keyserver.ubuntu.com`或其他密钥服务器供后续验证
* 编辑`~/.jreleaser/config.toml`如下

```toml
# https://central.sonatype.com User Token
JRELEASER_MAVENCENTRAL_USERNAME = "<your-publisher-portal-username>"
JRELEASER_MAVENCENTRAL_PASSWORD = "<your-publisher-portal-password>"
# https://oss.sonatype.org 的认证信息，这里没有用到，可以不进行配置
# JRELEASER_NEXUS2_USERNAME = "<your-sonatype-account-username>"
# JRELEASER_NEXUS2_PASSWORD = "<your-sonatype-account-password>"
# 创建 pgp 密钥时的密码
JRELEASER_GPG_PASSPHRASE = "<your-pgp-passphrase>"
JRELEASER_GPG_PUBLIC_KEY = "path/to/public.pgp"
JRELEASER_GPG_SECRET_KEY = "path/to/private.pgp"
# github token (没用但是不允许为空)
JRELEASER_GITHUB_TOKEN = "<your-github-token>"
```




### 5. 发布
1. `./gradlew jreleaserConfig`: 验证配置文件是否正确
2. `./gradlew clean publish`: 发布到本地 build/staging-deploy
3. `./gradlew jreleaserFullRelease`: 发布到远程Maven中央仓库

### 5. 其他
* deploy中`verifyPom = false`禁用检查