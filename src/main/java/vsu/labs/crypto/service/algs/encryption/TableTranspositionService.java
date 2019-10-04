package vsu.labs.crypto.service.algs.encryption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.encryption.transposition.TableTransposition;
import vsu.labs.crypto.exceptions.LogicException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TableTranspositionService {

    public BlocksResponse getAlgorithmBlocks() {
        ControlPanelBlock textBlock = new ControlPanelBlock();
        textBlock.setId("text");
        textBlock.setName("Текст");
        textBlock.setValue(""); // это опционально

        ControlPanelBlock keyBlock = new ControlPanelBlock();
        keyBlock.setId("key");
        keyBlock.setName("Ключ");
        keyBlock.setValue(""); // это опционально

        return BlocksResponse.withEncrypt(new ArrayList<>(Arrays.asList(textBlock, keyBlock)));
    }

    public String startAlgorithm(List<ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        // если проверка пройдена, то мы просто распаковываем блоки и запускаем алгоритм
        // здесь происходит парсинг блоков, который тоже выносится в отдельные функции
        var source = blocks.stream().filter(x -> x.getId().equals("text")).findFirst().get().getValue();
        var key = BigInteger.valueOf(
                Long.parseLong(
                        blocks.stream().filter(x -> x.getId().equals("key")).findFirst().get().getValue()
                )
        );
        return TableTransposition.encrypt(source, key);
    }

    // под каждый id блока можно написать свой обработчик, чтобы не городить городушки
    // а абстрагировать работу в отдельных обработчиках
    private void checkBlocks(List<ControlPanelBlock> blocks) {
        var textBlock = blocks.stream().filter(x -> x.getId().equals("text")).findFirst();

        //TODO блоки с обработкой выдепляются в отдельные хэндлеры

        // обработка блока (тут в основном проверки)
        if (textBlock.isEmpty())
            // такой ситуации по идее быть не должно, так что это непредвиденная ошибка
            throw new IllegalStateException();
        var block = textBlock.get();
        var value = block.getValue();
        if (StringUtils.isEmpty(value)) {
            throw new LogicException("Не заполнен блок " + block.getName());
        }
        // обработка блока
        var keyBlock = blocks.stream().filter(x -> x.getId().equals("key")).findFirst();


        // обработка блока (тут в основном проверки)
        if (keyBlock.isEmpty())
            // такой ситуации по идее быть не должно, так что это непредвиденная ошибка
            throw new IllegalStateException();
        block = keyBlock.get();
        value = block.getValue();
        if (StringUtils.isEmpty(value)) {
            throw new LogicException("Не заполнен блок " + block.getName());
        }
        // обработка блока
    }
}
