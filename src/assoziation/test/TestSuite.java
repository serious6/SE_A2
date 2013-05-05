package assoziation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import assoziation.test.classes.OneToManyBTest;
import assoziation.test.classes.OneToManyETest;
import assoziation.test.classes.OneToManyVoidTest;

@RunWith(Suite.class)
@SuiteClasses({ OneToManyBTest.class, OneToManyETest.class,
		OneToManyVoidTest.class })
public class TestSuite {

}
