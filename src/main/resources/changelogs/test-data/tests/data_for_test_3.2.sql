
INSERT INTO question(name)
VALUES('Причина, по которой удостоверяющий центр отзывает сертификат:'),
      ('Эффективная длина ключа в DES:'),
      ('Выберите то, что используют для создания цифровой подписи:'),
      ('Выберите то, что лучше всего описывает цифровую подпись:'),
      ('Определите преимущество RSA над DSA?'),
      ('Выберите то, что лучше описывает отличия между HMAC и CBC-MAC?'),
      ('Не будет являться свойством или характеристикой односторонней функции хэширования:'),
      ('Ключи, используемые в системах с открытым ключом:'),
      ('Суть метода перестановки:'),
      ('Самая простая разновидность подстановки:');



INSERT INTO answer(question_id, name, is_correct)
VALUES(21, 'Если открытый ключ пользователя скомпрометирован', false),
      (21, 'Если пользователь переходит на использование модели PEM, которая использует сеть доверия', false),
      (21, 'Если закрытый ключ пользователя скомпрометирован', true),
      (21, 'Если пользователь переходит работать в другой офис', false),
      (22, '56', true),
      (23, 'Закрытый ключ получателя', false),
      (23, 'Открытый ключ отправителя', false),
      (23, 'Закрытый ключ отправителя', true),
      (23, 'Открытый ключ получателя', false),
      (24, 'Это метод переноса собственноручной подписи на электронный документ', false),
      (24, 'Это метод шифрования конфиденциальной информации', false),
      (24, 'Это метод, позволяющий получателю сообщения проверить его источник и убедиться в целостности сообщения', true),
      (25, 'Он может обеспечить функциональность цифровой подписи и шифрования', true),
      (25, 'Он использует меньше ресурсов и выполняет шифрование быстрее, поскольку использует симметричные ключи',
       false),
      (25, 'Это блочный шифр и он лучше поточного', false),
      (25, 'Он использует одноразовые шифровальные блокноты', false),
      (26,
       'HMAC создает дайджест сообщения и применяется для контроля целостности; CBC-MAC используется для шифрования блоков данных с целью обеспечения конфиденциальности',
       false),
      (26,
       'HMAC использует симметричный ключ и алгоритм хэширования; CBC-MAC использует первый блок в качестве контрольной суммы',
       false),
      (26,
       'HMAC обеспечивает контроль целостности и аутентификацию источника данных; CBC-MAC использует блочный шифр в процессе создания MAC',
       true),
      (26,
       'HMAC зашифровывает сообщение на симметричном ключе, а затем передает результат в алгоритм хэширования; CBC-MAC зашифровывает все сообщение целиком',
       false),
      (27, 'Она преобразует сообщение произвольной длины в значение фиксированной длины', false),
      (27,
       'Имея значение дайджеста сообщения, невозможно получить само сообщение',
       false),
      (27, 'Получение одинакового дайджеста из двух различных сообщений невозможно, либо случается крайне редко',
       false),
      (27, 'Она преобразует сообщение фиксированной длины в значение переменной длины', true),
      (28, 'открытый', true),
      (28, 'закрытый', true),
      (28, 'нет правильного ответа', false),
      (29, 'символы шифруемого текста переставляются по определенным правилам внутри шифруемого блока символов',
       true),
      (29, 'замена алфавита', false),
      (29, 'все правильные', false),
      (30, 'простая замена', true),
      (30, 'перестановка', false),
      (30, 'простая перестановка', true);


INSERT INTO question(name)
VALUES  ('Зашифрованный методом простой перестановки текст "ЛУЧШЕЕ ВРАГ ХОРОШЕГО" будет выглядеть следующим образом'),
        ('К системам блочного шифрования относятся:'),
        ('Эффективная длина ключа в DES:'),
        ('Выберите то, что лучше всего описывает удостоверяющий центр?'),
        ('Цель криптоанализа:'),
        ('Идентификация позволяет:'),
        ('Таблицы Вижинера, применяемые для повышения стойкости шифрования:'),
        ('Чем являются символы оригинального текста, меняющиеся местами по определенному принципу, которые являются секретным ключом?'),
        ('Защищенные виртуальные каналы связи, представляющие собой соединение, проведенное через открытую сеть, по которому передаются криптографически защищенные пакеты сообщений виртуальной сети, называются'),
        ('Выберите то, что относится к показателям криптостойкости:');

INSERT INTO answer(question_id, name, is_correct)
VALUES
      (31, 'ОГЕШО РОХГА РВЕЕШ ЧУЛ', true),
     (31, 'ЕШО РОХГА РВЕЕШ ЧУЛ', false),
     (31, 'ОГЕШЧ УЛГАР ВЕЕШЧ УЛ', false),
     (31, 'ОГОХО ЛПГАР ВЕЕШЧ УЛ', false),
     (32, 'DES', true),
     (32, 'Российский стандарт ГОСТ 28147-89', true),
     (32, 'код Хаффмена', false),
     (32, 'схема шифрования Вижинера', true),
     (33, '56', true),
     (34, 'Организация, которая выпускает закрытые ключи и соответствующие алгоритмы', false),
     (34, 'Организация, которая проверяет процессы шифрования', false),
     (34, 'Организация, которая проверяет ключи шифрования', false),
     (34, 'Организация, которая выпускает сертификаты', true),
     (35, 'Определение стойкости алгоритма', true),
     (35, 'Увеличение количества функций замещения в криптографическом алгоритме', false),
     (35, 'Уменьшение количества функций подстановок в криптографическом алгоритме', false),
     (35, 'Определение использованных перестановок', false),
     (36, 'Субъекту назвать себя (сообщить свое имя)', true),
     (36, 'Разграничить сетевой доступ путем фильтрации', false),
     (36, 'Выявить нетипичное поведение пользователей', false),
     (37, 'Во всех (кроме первой) строках таблицы буквы располагаются в произвольном порядке', true),
     (37, 'В качестве ключа используется случайность последовательных чисел', true),
     (37, 'нет правильного ответа', false),
     (38, 'Алгоритм перестановки', true),
     (38, 'Алгоритм подстановки', false),
     (38, 'Алгоритм гаммирования', false),
     (39, 'Криптозащищенными туннелями', true),
     (39, 'Proxy-тоннелями',
      false),
     (39, 'Межсетевыми экранами', false),
     (39, 'Файрволами', false),
     (40, 'Количество всех возможных ключей', true),
     (40, 'Среднее время, необходимое для криптоанализа', true),
     (40, 'Количество символов в ключе', false);

INSERT INTO question(name)
VALUES('Основными современными методами шифрования являются:'),
      ('Количество последовательностей, из которых состоит расшифровка текста по таблице Вижинера:'),
      ('Зашифрованный методом простой перестановки текст "ПУСТЬ ВСЕГДА БУДЕТ НЕБО" будет выглядеть следующим образом:'),
      ('Алгоритм американского правительства, который предназначен для создания безопасных дайджестов сообщений:'),
      ('Выберите то, что используют для создания цифровой подписи:'),
      ('Выберите то, что лучше всего описывает цифровую подпись:'),
      ('Процесс, выполняемый после создания сеансового ключа DES:'),
      ('Название ситуации, в которой при использовании различных ключей для шифрования одного и того же сообщения в результате получается один и тот же шифротекст:'),
      ('Алгоритм, использующий симметричный ключ и алгоритм хэширования:'),
      ('Показатель стойкости шифрования методом гаммирования:');
