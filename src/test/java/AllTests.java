import com.dudoser.service.DistributorFirstTest;
import com.dudoser.service.DistributorSecondTest;
import com.dudoser.service.DistributorTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DistributorSecondTest.class, DistributorFirstTest.class, DistributorTest.class})
public class AllTests {
    @Test
    public void init(){}
}
