
INSERT INTO answer(question_id, name, is_correct)
VALUES (41, 'алгоритм гаммирования', true),
      (41, 'алгоритмы сложных математических преобразований', true),
      (41, 'алгоритм перестановки', true),
      (42, '3', true),
      (43, 'ОБЕНТ ЕДУБА ДГЕСВ ЬТСУП', true),
      (43, 'ЕШО РОХГА РВЕЕШ ЧУЛ', false),
      (43, 'ОГЕШЧ УЛГАР ВЕЕШЧ УЛ', false),
      (43, 'ОГОХО ЛПГАР ВЕЕШЧ УЛ', false),
      (44, 'Data Encryption Algorithm', true),
      (44, 'Digital Signature Standard', false),
      (44, 'Secure Hash Algorithm', true),
      (44, 'Data Signature Algorithm', false),
      (45, 'Закрытый ключ получателя', false),
      (45, 'Открытый ключ отправителя', false),
      (45, 'Закрытый ключ отправителя', true),
      (45, 'Открытый ключ получателя', false),
      (46, 'Это метод переноса собственноручной подписи на электронный документ', false),
      (46, 'Это метод шифрования конфиденциальной информации', false),
      (46, 'Это метод, обеспечивающий электронную подпись и шифрование', false),
      (46,
       'Это метод, позволяющий получателю сообщения проверить его источник и убедиться в целостности сообщения', true),
      (47, 'Подписание ключа', false),
      (47, 'Передача ключа на хранение третьей стороне (key escrow)', false),
      (47, 'Кластеризация ключа', false),
      (47, 'Обмен ключом', true),
      (48, 'Коллизия', false),
      (48, 'Хэширование', false),
      (48, 'MAC', false),
      (48, 'Кластеризация ключей', true),
      (49, 'HMAC', true),
      (49, '3DES', false),
      (49, 'ISAKMP-OAKLEY', false),
      (49, 'RSA', false),
      (50, 'свойство гаммы', true),
      (50, 'длина ключа', false),
      (50, 'нет правильного ответа', false);

INSERT INTO task(type_id, question_id, test_id)
VALUES (500, 1, 1),
      (500, 2, 1),
      (500, 3, 1),
      (600, 4, 1),
      (500, 5, 1),
      (600, 6, 1),
      (700, 7, 1),
      (600, 8, 1),
      (500, 9, 1),
      (500, 10, 1),

      (500, 11, 2),
      (500, 12, 2),
      (700, 13, 2),
      (500, 14, 2),
      (500, 15, 2),
      (500, 16, 2),
      (600, 17, 2),
      (500, 18, 2),
      (500, 19, 2),
      (500, 20, 2),

      (500, 21, 3),
      (700, 22, 3),
      (500, 23, 3),
      (500, 24, 3),
      (500, 25, 3),
      (500, 26, 3),
      (500, 27, 3),
      (600, 28, 3),
      (500, 29, 3),
      (600, 30, 3),

      (500, 31, 4),
      (600, 32, 4),
      (700, 33, 4),
      (500, 34, 4),
      (500, 35, 4),
      (500, 36, 4),
      (600, 37, 4),
      (500, 38, 4),
      (500, 39, 4),
      (600, 40, 4),

      (600, 41, 5),
      (700, 42, 5),
      (500, 43, 5),
      (600, 44, 5),
      (500, 45, 5),
      (500, 46, 5),
      (500, 47, 5),
      (500, 48, 5),
      (500, 49, 5),
      (500, 50, 5);
