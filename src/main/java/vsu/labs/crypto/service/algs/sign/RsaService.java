package vsu.labs.crypto.service.algs.sign;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.digSignature.RSA.RSA;
import vsu.labs.crypto.service.algs.common.DefaultBlocksChecker;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class RsaService {

    public BlocksResponse getBlocks() {
        Map<String, ControlPanelBlock> blocks = BlockBuilder.buildMap()
                .withBlock("text", "Текст")
                .withBlock("sign", "Подпись")
                .withBlock("openE", "(Открытый ключ)e")
                .withBlock("openN", "(Открытый ключ)n")
                .withBlock("secretD", "(Закрытый ключ)d")
                .build();

        return BlocksResponse.withCheckSign(blocks);
    }

    public String generateKeys() {
        var keys = RSA.genKeys(81);
        var open = keys.getFirst();
        var secret = keys.getSecond();
        return "Открытый ключ e = "+ open.getE()+ "\n" +
                "открытый ключ n = "+ open.getN() + "\n " +
                "закрытый ключ d = " + secret.getD();

    }

    public String checkSign(Map<String, ControlPanelBlock> blocks)  {
        List<String> listName = new ArrayList<>(Arrays.asList("text", "sign", "openE", "openN"));
        DefaultBlocksChecker.checkBlocks(blocks, listName);
        BigInteger openE = BigInteger.valueOf(Integer.valueOf(blocks.get("openE").getValue()));
        BigInteger openN = BigInteger.valueOf(Integer.valueOf(blocks.get("openN").getValue()));
        boolean check = RSA.checkSign(
                new RSA.OpenKey(openE,openN),
                new RSA.Message
                        (blocks.get("text").getValue(),
                        blocks.get("sign").getValue()
                        )
        );
        if (check){
            return "Проверка по открытому ключу пройдена успешно";
        }
        return "Проверка по открытому ключу провалена";
    }
}
