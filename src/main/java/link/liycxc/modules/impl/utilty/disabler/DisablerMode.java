package link.liycxc.modules.impl.utilty.disabler;

import link.liycxc.api.value.Value;
import link.liycxc.NekoCat;
import link.liycxc.modules.impl.utilty.Disabler;
import net.minecraft.client.Minecraft;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liycxc
 */
public class DisablerMode {
    public String modeName;
    public Disabler disabler = (Disabler) NekoCat.instance.moduleManager.getModule("Disabler");
    public Minecraft mc = Minecraft.getMinecraft();

    public DisablerMode(String modeName) {
        this.modeName = modeName;
    }

    public List<Value<?>> getValues() {
        List<Value<?>> values = new ArrayList<>();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Value.class.isAssignableFrom(field.getType())) {
                try {
                    field.setAccessible(true);
                    Value<?> value = (Value<?>) field.get(this);
                    values.add(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return values;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onInitialize() {
    }
}
