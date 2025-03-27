-- catlaog database 생성
CREATE DATABASE IF NOT EXISTS `catalog`; -- 백틱(`)을 넣는 이유는 예약어가 있을 수 있기 때문에
USE `catalog`;

 -- book 테이블 생성 
CREATE TABLE IF NOT EXISTS book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    title VARCHAR(255) NOT NULL,
    create_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP NOT NULL,
    version INT NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



-- order database 생성

CREATE DATABASE IF NOT EXISTS `order`;
USE `order`;


-- orders 테이블 생성
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    book_isbn VARCHAR(255) NOT NULL,
    book_name VARCHAR(255),
    book_price DECIMAL(10, 2), -- DECIMAL 타입으로 변경
    quantity INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 현재 시간 자동 설정
    last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 자동 갱신 시간
    version INT NOT NULL
)   DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- auth database 생성
CREATE DATABASE IF NOT EXISTS `auth`;
USE `auth`;

-- user 테이블 생성
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL 
)   DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;