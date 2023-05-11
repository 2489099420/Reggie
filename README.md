# 瑞吉外卖项目（跟随黑马编写）
本项目的安装过程大致如下：
1. 下载项目源码
从项目代码托管平台（如 Github、GitLab 等）上下载项目源代码并解压，或者使用 git 工具 进行克隆（git clone 命令） 。

2. 安装后端依赖
进入后端工程目录，使用 Maven 安装项目依赖

3. 配置数据库
在 mysql 数据库中创建相应的数据库和数据表，修改后端工程中 resources/application.yml 文件中的数据库连接信息。

4. 配置 Redis
在 Redis 缓存数据库中创建相应的缓存数据，修改后端工程中 resources/application.yml 文 件中的 Redis 配置信息。

# Tip
sql文件在resource文件下 需要修改的内容有：
mysql数据库账号密码
redis数据库账号密码
修改utils工具包下的SMSUtils.java文件中阿里云短信和对象存储服务的secretId和secretKey，当然buckerName、region、url也需要修改。使用者可自行申请阿里云短信和对象存储服务后来填充密钥等信息
