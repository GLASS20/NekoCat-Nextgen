package link.liycxc.verify;

import link.liycxc.utils.Logger;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.digest.DigestUtils;

@UtilityClass
public class Verify {
    public void init() {
        String md5 = DigestUtils.md5Hex(VerifyUtils.getSerialNumber());
        Logger.log("MD5: " + md5);
    }
}
