package com.dev.app.encrypt_api.encrypt;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.dev.app.encrypt_api.dto.EncryptObject;

@SpringBootTest
public class EncryptServiceTest {

    @Test
    void testencryptOrDecryptList() {
        EncryptService service = new EncryptService();

        ReflectionTestUtils.setField(service, "ALGORITHM", "PBEWITHHMACSHA512ANDAES_256");
        ReflectionTestUtils.setField(service, "DEFAULT_SECRET", "test-default-secret");

        List<EncryptObject> testEncryptInputs = new ArrayList<>();
        testEncryptInputs.add(new EncryptObject("Encrypt this Text", "encrypt-test-secret"));

        List<String> testEncryptResult = service.encryptOrDecryptList("encrypt", testEncryptInputs);

        List<EncryptObject> testDecryptInputs = new ArrayList<>();
        testDecryptInputs.add(new EncryptObject(testEncryptResult.get(0), "encrypt-test-secret"));

        List<String> testDecryptResult = service.encryptOrDecryptList("decrypt", testDecryptInputs);

        assertEquals(testEncryptInputs.get(0).getText(), testDecryptResult.get(0));
    }

}
