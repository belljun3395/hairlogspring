CREATE
    USER 'hairlog-local'@'localhost' IDENTIFIED BY 'hairlog-local';
CREATE
    USER 'hairlog-local'@'%' IDENTIFIED BY 'hairlog-local';

GRANT ALL PRIVILEGES ON *.* TO
    'hairlog-local'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO
    'hairlog-local'@'%';

CREATE
    DATABASE hairlog DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;