import com.dudoser.service.DistributorFirstTest;
import com.dudoser.service.DistributorZeroTest;
import com.dudoser.service.DistributorSecondTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DistributorSecondTest.class, DistributorZeroTest.class, DistributorFirstTest.class})
public class AllTests {
    @Test
    public void init(){}
}
