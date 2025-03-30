CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS blog (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    manager_id BIGINT,
    CONSTRAINT fk_blog_manager FOREIGN KEY (manager_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS article (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT,
    blog_id BIGINT,
    CONSTRAINT fk_article_author FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT fk_article_blog FOREIGN KEY (blog_id) REFERENCES blog (id)
);

CREATE TABLE IF NOT EXISTS role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user_roles (
     user_id BIGINT,
     role_id BIGINT,
     PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);
