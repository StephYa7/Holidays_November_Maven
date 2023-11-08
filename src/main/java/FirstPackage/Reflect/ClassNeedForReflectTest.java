package FirstPackage;

import lombok.Data;

@Data
public class FirstClass {
    private double x;
    private double y;

    public double haha() {
        return Math.sqrt(x * x + y * y);
    }

}