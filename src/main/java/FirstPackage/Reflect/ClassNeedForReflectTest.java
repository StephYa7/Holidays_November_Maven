package FirstPackage.Reflect;

import lombok.Data;

@Data
public class ClassNeedForReflectTest {
    private double x;
    private double y;

    public double haha() {
        return Math.sqrt(x * x + y * y);
    }

}