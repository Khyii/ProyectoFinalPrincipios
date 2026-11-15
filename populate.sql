
USE userLogsE1;

DELIMITER //

CREATE PROCEDURE generate_test_data()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE random_name VARCHAR(150);
    DECLARE random_lastname VARCHAR(150);
    DECLARE random_topic VARCHAR(40);
    DECLARE random_title VARCHAR(150);

    -- =========================================
    -- 1. GENERATE 1000 USERS
    -- =========================================

    WHILE i <= 1000 DO

        SET random_name = ELT(
            FLOOR(1 + RAND() * 15),
            'Carlos', 'Andres', 'Maria', 'Laura', 'Daniel',
            'Sofia', 'Juan', 'Valentina', 'Sebastian', 'Camila',
            'David', 'Isabella', 'Miguel', 'Lucia', 'Alejandro'
        );

        SET random_lastname = ELT(
            FLOOR(1 + RAND() * 15),
            'Rodriguez', 'Gomez', 'Martinez', 'Lopez', 'Garcia',
            'Perez', 'Sanchez', 'Ramirez', 'Torres', 'Diaz',
            'Moreno', 'Castro', 'Rojas', 'Herrera', 'Vargas'
        );

        INSERT INTO `user` (
            username,
            can_create,
            can_delete,
            can_edit,
            direccion,
            email,
            nombre,
            password,
            telefono
        )
        VALUES (
            CONCAT('usr', LPAD(i, 8, '0')),
            IF(RAND() > 0.5, b'1', b'0'),
            IF(RAND() > 0.7, b'1', b'0'),
            IF(RAND() > 0.4, b'1', b'0'),
            CONCAT(
                'Calle ', FLOOR(1 + RAND() * 150),
                ' # ', FLOOR(1 + RAND() * 100),
                '-', FLOOR(1 + RAND() * 99)
            ),
            CONCAT('usuario', i, '@example.com'),
            CONCAT(random_name, ' ', random_lastname),
            CONCAT('Clave', LPAD(i, 8, '0')),
            CONCAT(
                '3',
                LPAD(FLOOR(RAND() * 1000000000), 9, '0')
            )
        );

        SET i = i + 1;

    END WHILE;

    -- =========================================
    -- 2. GENERATE 9000 GUIDES
    -- =========================================

    SET i = 1;

    WHILE i <= 9000 DO

        SET random_topic = ELT(
            FLOOR(1 + RAND() * 15),
            'Programacion',
            'Bases de datos',
            'Redes',
            'Seguridad',
            'Java',
            'Python',
            'Docker',
            'Spring Boot',
            'Desarrollo web',
            'Linux',
            'Git',
            'Inteligencia artificial',
            'Hardware',
            'Software',
            'Cloud computing'
        );

        SET random_title = ELT(
            FLOOR(1 + RAND() * 12),
            'Introduccion a ',
            'Guia completa de ',
            'Aprende a utilizar ',
            'Conceptos fundamentales de ',
            'Consejos para trabajar con ',
            'Como implementar ',
            'Buenas practicas en ',
            'Tutorial practico de ',
            'Configuracion inicial de ',
            'Errores comunes en ',
            'Optimizacion de ',
            'Todo lo que debes saber sobre '
        );

        INSERT INTO guide (
            guide_id,
            guide_author,
            guide_body,
            guide_title,
            guide_topic,
            publish_date
        )
        VALUES (
            CONCAT('G', LPAD(i, 7, '0')),

            CONCAT(
                'usr',
                LPAD(FLOOR(1 + RAND() * 1000), 8, '0')
            ),

            CONCAT(
                'Esta guia aborda el tema de ',
                random_topic,
                '. Su objetivo es explicar los conceptos ',
                'principales y proporcionar recomendaciones ',
                'para su implementacion. ',
                'Es importante comprender los fundamentos, ',
                'realizar pruebas y documentar cada procedimiento. ',
                'Se recomienda practicar los ejemplos y consultar ',
                'la documentacion oficial para profundizar ',
                'en los conocimientos adquiridos. ',
                'Guia numero ', i, '.'
            ),

            CONCAT(random_title, random_topic),

            random_topic,

            DATE_FORMAT(
                DATE_SUB(
                    CURDATE(),
                    INTERVAL FLOOR(RAND() * 1826) DAY
                ),
                '%Y-%m-%d'
            )
        );

        SET i = i + 1;

    END WHILE;

END //

DELIMITER ;

-- Execute data generation
CALL generate_test_data();

-- Remove the procedure after execution
DROP PROCEDURE generate_test_data;