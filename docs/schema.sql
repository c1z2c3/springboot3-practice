-- W1 建表脚本：课程管理 CRUD
CREATE DATABASE IF NOT EXISTS crud_db DEFAULT CHARACTER SET utf8mb4;
USE crud_db;

CREATE TABLE IF NOT EXISTS t_category (
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(64)  NOT NULL,
    sort       INT          NOT NULL DEFAULT 0,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted    TINYINT      NOT NULL DEFAULT 0,
    KEY idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_course (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT        NOT NULL,
    title       VARCHAR(128)  NOT NULL,
    cover_url   VARCHAR(512)  NULL,
    price       DECIMAL(10,2) NOT NULL DEFAULT 0,
    status      TINYINT       NOT NULL DEFAULT 0 COMMENT '0草稿 1上架 2下架',
    created_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted     TINYINT       NOT NULL DEFAULT 0,
    KEY idx_category (category_id),
    KEY idx_status_created (status, created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_lesson (
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    course_id    BIGINT       NOT NULL,
    title        VARCHAR(128) NOT NULL,
    sort         INT          NOT NULL DEFAULT 0,
    duration_sec INT          NOT NULL DEFAULT 0,
    created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted      TINYINT      NOT NULL DEFAULT 0,
    KEY idx_course (course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO t_category (name, sort) VALUES
('后端开发', 1),
('前端开发', 2),
('人工智能', 3);

INSERT INTO t_course (category_id, title, price, status) VALUES
(1, 'Spring Boot 3 入门', 99.00, 1),
(1, 'MyBatis-Plus 实战', 129.00, 1),
(3, 'AI Agent 开发基础', 199.00, 0);

INSERT INTO t_lesson (course_id, title, sort, duration_sec) VALUES
(1, '环境准备与项目创建', 1, 480),
(1, '第一个 REST 接口', 2, 620),
(1, '参数绑定与校验', 3, 750);