INSERT INTO test (id, title)
VALUES (1, 'Вариант 1'),
      (2, 'Вариант 2'),
      (3, 'Вариант 3'),
      (4, 'Вариант 4'),
      (5, 'Вариант 5');

INSERT INTO task_type (id, title, type)
VALUES (700, 'Ручной ввод в поле ответа', 'MANUAL_INPUT'),
     (600, 'Ответ с множественным выбором', 'MULTISELECT'),
     (500, 'Выбор единственного ответа', 'SELECT');


INSERT INTO question(id, name)
VALUES (1, 'Что такое шифрование?'),
      (2, 'Что такое кодирование?'),
      (3, 'Для восстановления защитного текста требуется:'),
      (4, 'Выберите примеры алфавитов:'),
      (5, 'Что представляет собой криптографическая система?'),
      (6, 'На какие виды подразделяют криптосистемы?'),
      (7, 'Количество используемых ключей в симметричных криптосистемах для шифрования и дешифрования:'),
      (8, 'Ключи, используемые в системах с открытым ключом:'),
      (9, 'Выберите то, как связаны ключи друг с другом в системе с открытым ключом:'),
      (10, 'Что принято называть электронной подписью?');



