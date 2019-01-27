package com.pahlsoft.encryption;

import com.pahlsoft.encryption.EncryptionUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class BasicEncryptionTest {
    @BeforeClass
    public void setup() throws Exception {

    }

    @Test
    public void testEncryptionOfBinary() throws Exception {
        byte[] targetItem = "blah".getBytes("UTF-8");
        byte[] encryptedBytes = EncryptionUtil.encrypt(targetItem);
        byte[] decryptedBytes = EncryptionUtil.decrypt(encryptedBytes);
        Assert.assertEquals("blah", new String(decryptedBytes, "UTF-8"));
    }

}
