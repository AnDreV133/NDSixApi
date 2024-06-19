package com.example.NDSixApi.coders;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class HashGeneratorFiveCharsRandomSuffixAndDoubleSha256 implements HashGenerator {
    @NotNull
    @Override
    public String hash(String input) {
        return DigestUtils.sha256Hex(DigestUtils.sha256Hex(STR."\{input}-#@-#@"));
    }
}

