\# boot3-01-crud

Spring Boot 3 + MyBatis-Plus 的课程管理 CRUD 练习项目，W1 阶段产出。

\## 技术栈

\- Java 21

\- Spring Boot 3.5.0

\- MyBatis-Plus 3.5.17

\- MySQL 8.x

\- Maven 3.8.8

\## 环境要求

\- JDK 21（`java -version` 确认）

\- Maven 3.8+

\- MySQL 8.x，本地 3306 端口

\## 启动步骤

1. 建库建表

   \```bash

   mysql -u root -p < docs/schema.sql

   \```

   或在 Navicat 里打开 `docs/schema.sql` 全选执行。**只对全新库执行一次**，重复执行会插入重复种子数据。

2. 改数据库密码

   `boot3-01-crud/src/main/resources/application.yml`：

   \```yaml

   spring:

​     datasource:

​       username: root

​       password: 你的密码

   \```

3. 启动

   \```bash

   cd boot3-01-crud

   mvn spring-boot:run

   \```

   或 IDEA 里运行 `Boot301CrudApplication`。

4. 验证

   \```bash

   curl "http://localhost:8081/api/courses?page=1&size=5"

   \```

   返回 `code: 0` 即启动成功。

\## 表结构

\### t_category 分类表

| 字段 | 类型 | 说明 |

|---|---|---|

| id | BIGINT | 主键，自增 |

| name | VARCHAR(64) | 分类名 |

| sort | INT | 排序值，升序 |

| created_at | DATETIME | 创建时间 |

| updated_at | DATETIME | 更新时间 |

| deleted | TINYINT | 逻辑删除，0 正常、1 已删 |

\### t_course 课程表

| 字段 | 类型 | 说明 |

|---|---|---|

| id | BIGINT | 主键，自增 |

| category_id | BIGINT | 所属分类 id |

| title | VARCHAR(128) | 课程标题 |

| cover_url | VARCHAR(512) | 封面图，可空 |

| price | DECIMAL(10,2) | 价格 |

| status | TINYINT | 0 草稿、1 上架、2 下架 |

| created_at | DATETIME | 创建时间 |

| updated_at | DATETIME | 更新时间 |

| deleted | TINYINT | 逻辑删除 |

\### t_lesson 章节表

| 字段 | 类型 | 说明 |

|---|---|---|

| id | BIGINT | 主键，自增 |

| course_id | BIGINT | 所属课程 id |

| title | VARCHAR(128) | 章节标题 |

| sort | INT | 章节顺序 |

| duration_sec | INT | 时长，秒 |

| created_at | DATETIME | 创建时间 |

| updated_at | DATETIME | 更新时间 |

| deleted | TINYINT | 逻辑删除 |

\## 接口清单

| 方法 | 路径 | 入参 | 返回 | 说明 |

|---|---|---|---|---|

| POST | /api/courses | body：categoryId、title、price、status | 新课程 id | 新增课程 |

| GET | /api/courses/{id} | path：id | Course 实体 | 课程详情 |

| PUT | /api/courses/{id} | body 同新增 | null | 更新课程 |

| DELETE | /api/courses/{id} | path：id | null | 逻辑删除 |

| GET | /api/courses | page、size、status、keyword、sortBy、dir | PageResult\<Course> | 分页查询 |

| GET | /api/courses/{id}/detail | path：id | CourseDetailVO | 详情 + 分类名 + 章节列表 |

| GET | /api/courses/{id}/detail-join | path：id | CourseDetailVO | 同 detail，一条 JOIN 实现（对照用） |

| GET | /api/categories | 无 | List\<Category> | 分类列表 |

**分页参数说明**

\- `page`：页码，默认 1，小于 1 按 1 处理

\- `size`：每页条数，默认 10，最大 50

\- `status`：可选，按状态过滤

\- `keyword`：可选，按标题模糊匹配

\- `sortBy`：`price`、`id`，默认 `created_at`

\- `dir`：`asc` / `desc`，默认 `desc`

\## 错误码

| code | 含义 | 出现场景 |

|---|---|---|

| 40001 | 参数校验失败 | `@Valid` 不通过、路径参数类型错误 |

| 40101 | 未登录 | 预留，当前无鉴权 |

| 40301 | 无权限 | 预留 |

| 40401 | 资源不存在 | id 查不到、已逻辑删除 |

| 40901 | 重复提交 | 预留 |

| 50001 | 业务处理失败 | `BusinessException` |

| 50000 | 系统繁忙 | 未捕获异常，看服务端日志 |

\## 目录结构

\```text

spring-boot3/

├── README.md

├── docs/

│   ├── schema.sql

│   └── postman/

├── pom.xml

└── boot3-01-crud/

​    └── src/main/java/com/czc/crud/

​        ├── common/      Result、ErrorCode、异常处理

​        ├── config/      MyBatis-Plus 配置、自动填充

​        ├── controller/  CourseController、CategoryController、HelloController

​        ├── dto/         CourseCreateDTO

​        ├── entity/      Course、Category、Lesson

​        ├── mapper/      CourseMapper、CategoryMapper、LessonMapper

​        ├── service/     CourseService、CategoryService

​        └── vo/          CourseDetailVO、LessonVO

\```

\## 已知限制

\- 无鉴权，所有接口公开

\- 分类和章节只有查询，没有增删改

\- 分页只支持单表，多表 JOIN 分页未做