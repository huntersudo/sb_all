<a href="http://gitlab.cmss.com/NETWORK/pms-common/commits/develop"><img alt="pipeline status" src="http://gitlab.cmss.com/NETWORK/pms-common/badges/develop/pipeline.svg" /></a>
<a href="http://gitlab.cmss.com/NETWORK/pms-common/commits/develop"><img alt="coverage report" src="http://gitlab.cmss.com/NETWORK/pms-common/badges/develop/coverage.svg" /></a>

# PMS-COMMON: 性能管理系统COMMON模块 (Common Lib For Performance Management System)

## pms-common

### 说明

该工程为PMS的REST接口提供了共通的处理.

### 功能介绍

- 请确保 **spring** 能够扫描到 **com.chinamobile.cmss.common** 这个 package 或者其 **父package**，否则此共通模块将无法生效
- 对 **REST** 接口返回值进行统一封装
- 提供相关常量
- 提供 **MODEL** 转换为 **DTO** 的抽象处理(具体实现, 需要由子工程自己实现)
- 统一异常处理
- **spring** 辅助类,用于获取 **spring context** 中的 **bean**
- 对于每个引用了 **本工程Jar包** 的工程，请将本工程的 **etc/message.properties** 文件拷贝至相应工程的 **classpath** 下(一般放 **src/main/resources/** 目录下即可)。然后，相应工程可以在 **message.properties** 文件中添加自己所需要的 **error code & error message**，且 **error code** 请从 **MSG_000001** 升序排列。
  (PS：如果手动为spring指定了资源文件，请务必包含此 **message.properties** 文件中的内容。)