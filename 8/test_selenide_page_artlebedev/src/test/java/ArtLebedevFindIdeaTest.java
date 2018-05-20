import org.junit.Assert;
import org.junit.Test;
import pages.ArtLebedevPage;

public class ArtLebedevFindIdeaTest extends BaseTest {
    @Test
    public void test() {
        ArtLebedevPage page = new ArtLebedevPage();
        ArtLebedevPage.ideaMatrix.ideaResults results = page.Inventar().findIdea("гвоздь").get();
        results.text();
        Assert.assertTrue(results.count() == 2);
    }
}
