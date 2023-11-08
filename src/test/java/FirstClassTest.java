import FirstPackage.Reflect.ClassNeedForReflectTest;
import org.junit.Assert;
import org.junit.Test;

public class FirstClassTest {

    @Test
    public void newVectorShouldHaveZeroLength(){
        ClassNeedForReflectTest test = new ClassNeedForReflectTest(); // action

        //assertion
        Assert.assertEquals(0, test.haha(),1e-9);
    }
}