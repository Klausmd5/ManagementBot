package config;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.Strings;

public class UserTest {

	@BeforeClass
	public static void setUp() {
		Database.init();
		User user = User.loadUser(123456789L);
		user.setLanguage(Strings.Lang.DE);
		user.addPermission("testPermission");
	}

	@AfterClass
	public static void tearDown() {
		User user = User.loadUser(123456789L);
		User.deleteUser(user);
		Database.cleanUp();
	}

	@Test
	public void loadUser() {
		User user = User.loadUser(123456789L);
		Assert.assertNotNull(user);
	}

	@Test
	public void deleteUser() {
		User user = User.loadUser(123456789L);
		User.deleteUser(user);
		user = User.loadUser(123456789L);
		Assert.assertEquals(Strings.Lang.EN, user.getLanguage());
		Assert.assertEquals(0, user.getPermissions().size());
		user.setLanguage(Strings.Lang.DE);
		user.addPermission("testPermission");
	}

	@Test
	public void getDiscordId() {
		User user = User.loadUser(123456789L);
		Assert.assertEquals(123456789L, user.getDiscordID());
	}

	@Test
	public void getPermissions() {
		User user = User.loadUser(123456789L);
		Assert.assertEquals(1, user.getPermissions().size());
		Assert.assertEquals("testPermission", user.getPermissions().get(0));
	}

	@Test
	public void addPermission() {
		User user = User.loadUser(123456789L);
		user.addPermission("anotherTestPermission");
		Assert.assertEquals(2, user.getPermissions().size());
		Assert.assertTrue(user.getPermissions().contains("testPermission"));
		Assert.assertTrue(user.getPermissions().contains("anotherTestPermission"));
		user.removePermission("anotherTestPermission");
	}

	@Test
	public void getLanguage() {
		User user = User.loadUser(123456789L);
		Assert.assertEquals(Strings.Lang.DE, user.getLanguage());
	}

	@Test
	public void setLanguage() {
		User user = User.loadUser(123456789L);
		user.setLanguage(Strings.Lang.EN);
		Assert.assertEquals(Strings.Lang.EN, user.getLanguage());
		user.setLanguage(Strings.Lang.DE);
		Assert.assertEquals(Strings.Lang.DE, user.getLanguage());
	}
}