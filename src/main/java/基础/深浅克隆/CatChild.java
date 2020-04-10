package 基础.深浅克隆;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class CatChild implements Cloneable {
    private String name;
    private String name2;
    private String name22;
    private String name223;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
