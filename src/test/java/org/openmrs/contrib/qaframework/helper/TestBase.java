package org.openmrs.contrib.qaframework.helper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.vfs2.AllFileSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openmrs.contrib.qaframework.helper.TestData.EncounterInfo;
import org.openmrs.contrib.qaframework.helper.TestData.PatientInfo;
import org.openmrs.contrib.qaframework.helper.TestData.UserInfo;
import org.openmrs.contrib.qaframework.page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

/**
 * Superclass for all UI Tests. Contains lots of handy "utilities" needed to
 * setup and tear down tests as well as handy methods needed during tests, such
 * as:
 * <ul>
 * <li>initialize Selenium WebDriver</li>
 * <li>@see {@link #goToLoginPage()}</li>
 * <li>@see {@link #login()}</li>
 * <li>@see {@link #assertPage(Page)} - @see {@link #pageContent()}</li>
 * </ul>
 */
public class TestBase {

	public static final int MAX_WAIT_IN_SECONDS = 120;

	public static final int MAX_PAGE_LOAD_IN_SECONDS = 120;

	public static final int MAX_SERVER_STARTUP_IN_MILLISECONDS = 10 * 60 * 1000;

	protected By patientHeaderId = By.cssSelector("div.identifiers span");

	private static volatile boolean serverFailure = false;

	protected WebDriver driver;

	protected Page page;

	public TestBase() {
		try {
			startWebDriver();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Before
	public void startWebDriver() throws Exception {
		if (serverFailure) {
			fail("Test killed due to server failure");
		}
		launchBrowser();
	}

	public void launchBrowser() throws Exception {
		final TestProperties properties = TestProperties.instance();
		System.out.println("Running locally...");
		final TestProperties.WebDriverType webDriverType = properties
				.getWebDriver();
		switch (webDriverType) {
			case chrome :
				driver = setupChromeDriver();
				break;
			case firefox :
				driver = setupFirefoxDriver();
				break;
			default :
				// shrug, choose chrome as default
				driver = setupChromeDriver();
				break;

		}

		driver.manage().timeouts()
				.implicitlyWait(MAX_WAIT_IN_SECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts()
				.pageLoadTimeout(MAX_PAGE_LOAD_IN_SECONDS, TimeUnit.SECONDS);

	}

	@After
	public void stopWebDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	protected WebDriver getWebDriver() {
		return driver;
	}

	WebDriver setupFirefoxDriver() {
		if (StringUtils.isBlank(System.getProperty("webdriver.gecko.driver"))) {
			System.setProperty(
					"webdriver.gecko.driver",
					Thread.currentThread()
							.getContextClassLoader()
							.getResource(
									TestProperties.instance()
											.getFirefoxDriverLocation())
							.getPath());
		}
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		if ("true".equals(TestProperties.instance().getHeadless())) {
			firefoxOptions.addArguments("--headless");
		}
		driver = new FirefoxDriver(firefoxOptions);
		return driver;
	}

	WebDriver setupChromeDriver() {
		URL chromedriverExecutable = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();

		String chromedriverExecutableFilename = null;
		if (SystemUtils.IS_OS_MAC_OSX) {
			chromedriverExecutableFilename = "chromedriver";
			chromedriverExecutable = classLoader
					.getResource("chromedriver/mac/chromedriver");
		} else if (SystemUtils.IS_OS_LINUX) {
			chromedriverExecutableFilename = "chromedriver";
			chromedriverExecutable = classLoader
					.getResource("chromedriver/linux/chromedriver");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			chromedriverExecutableFilename = "chromedriver.exe";
			chromedriverExecutable = classLoader
					.getResource("chromedriver/windows/chromedriver.exe");
		}
		String errmsg = "cannot find chromedriver executable";
		String chromedriverExecutablePath = null;
		if (chromedriverExecutable == null) {
			System.err.println(errmsg);
			Assert.fail(errmsg);
		} else {
			chromedriverExecutablePath = chromedriverExecutable.getPath();
			// This checks to see if the chromedriver file is inside a
			// jar, and if so
			// uses VFS to extract it to a temp directory.
			if (chromedriverExecutablePath.contains(".jar!")) {
				FileObject chromedriver_vfs;
				try {
					chromedriver_vfs = VFS.getManager().resolveFile(
							chromedriverExecutable.toExternalForm());
					File chromedriver_fs = new File(
							FileUtils.getTempDirectory(),
							chromedriverExecutableFilename);
					FileObject chromedriverUnzipped = VFS.getManager()
							.toFileObject(chromedriver_fs);
					chromedriverUnzipped.delete();
					chromedriverUnzipped.copyFrom(chromedriver_vfs,
							new AllFileSelector());
					chromedriverExecutablePath = chromedriver_fs.getPath();
					if (!SystemUtils.IS_OS_WINDOWS) {
						chromedriver_fs.setExecutable(true);
					}
				} catch (FileSystemException e) {
					System.err.println(errmsg + ": " + e);
					e.printStackTrace();
					Assert.fail(errmsg + ": " + e);
				}
			}
		}

		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				chromedriverExecutablePath);
		String chromedriverFilesDir = "target/chromedriverlogs";
		try {
			FileUtils.forceMkdir(new File(chromedriverFilesDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
				chromedriverFilesDir + "/chromedriver-"
						+ getClass().getSimpleName() + ".log");
		ChromeOptions chromeOptions = new ChromeOptions();
		if ("true".equals(TestProperties.instance().getHeadless())) {
			chromeOptions.addArguments("--headless");
		}
		driver = new ChromeDriver(chromeOptions);
		return driver;
	}

	/**
	 * Assert we're on the expected page.
	 * 
	 * @param expected
	 *            page
	 */
	public void assertPage(Page expected) {
		assertTrue(driver.getCurrentUrl().contains(expected.getPageUrl()));
	}

	protected void quit() {
		if (getWebDriver() != null) {
			getWebDriver().quit();
		}
	}

	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}

	/**
	 * Returns the entire text of the "content" part of the current page
	 * 
	 * @return the entire text of the "content" part of the current page
	 */
	public String pageContent() {
		return driver.findElement(By.id("content")).getText();
	}

	public LoginPage goToLoginPage() {
		LoginPage loginPage = getLoginPage();

		Response response = ClientBuilder.newClient()
				.target(TestProperties.instance().getWebAppUrl())
				.path(loginPage.getPageUrl()).request().get();

		int status = response.getStatus();

		if (status >= 400 && status <= 599) {
			throw new ServerErrorException(response.getStatusInfo()
					.getReasonPhrase(), status);
		}

		loginPage.go();
		loginPage.waitForPage();

		// refresh, just to be sure all css files and images are loaded properly
		driver.navigate().refresh();
		loginPage.waitForPage();

		return loginPage;
	}

	public EncounterInfo createTestEncounter(String encounterType,
			PatientInfo patient) {
		EncounterInfo ei = new EncounterInfo();
		ei.datetime = "2012-01-04"; // arbitrary
		ei.type = TestData.getEncounterTypeUuid(encounterType);
		ei.patient = patient;
		TestData.createEncounter(ei); // sets the uuid
		return ei;
	}

	protected LoginPage getLoginPage() {
		return new LoginPage(driver);
	}

	public void login(UserInfo user) {
		LoginPage page = getLoginPage();
		assertPage(page);
		page.login(user.username, user.password);
	}

	protected void waitForPatientDeletion(String uuid) throws Exception {
		Long startTime = System.currentTimeMillis();
		while (checkIfPatientExists(uuid)) {
			Thread.sleep(200);
			if (System.currentTimeMillis() - startTime > 30000) {
				throw new TimeoutException(
						"Patient not deleted in expected time");
			}
		}
	}

	public String createPatient(String personUuid,
			String patientIdentifierType, String source) {
		String patientIdentifier = generatePatientIdentifier(source);
		RestClient.post("patient", new TestPatient());
		return patientIdentifier;
	}

	private String generatePatientIdentifier(String source) {
		return RestClient.generatePatientIdentifier(source);
	}

	public PatientInfo createTestPatient(String patientIdentifierTypeName,
			String source) {
		PatientInfo pi = TestData.generateRandomPatient();
		String uuid = TestData.createPerson(pi);
		pi.identifier = createPatient(uuid, patientIdentifierTypeName, source);
		return pi;
	}

	public PatientInfo createTestPatient() {
		return createTestPatient(TestData.OPENMRS_PATIENT_IDENTIFIER_TYPE, "1");
	}

	/**
	 * @return uuid of created test location tag
	 */
	public String createTestLocationTag() {
		JsonNode responseBody = RestClient.post("/locationtag",
				new TestData.TestLocationTag("TAG" + TestData.randomSuffix(),
						"TEST TAG"));
		return responseBody != null ? responseBody.get("uuid").asText() : null;
	}

	/**
	 * Delete the given patient from the various tables that contain portions of
	 * a patient's info.
	 * 
	 * @param patientInfo
	 *            containing hhe uuid of the patient to delete.
	 */
	public void deletePatient(PatientInfo patientInfo) throws NotFoundException {
		if (patientInfo != null) {
			RestClient.delete("patient/" + patientInfo.uuid);
		}
	}

	/**
	 * Check if patient with given id exists
	 * 
	 * @param id
	 * @return true if patient exists, false otherwise
	 */
	public static boolean checkIfPatientExists(String id) {
		try {
			JsonNode json = RestClient.get("patient/" + id);
			JsonNode results = json.get("results");
			if (results != null && results.size() > 0) {
				return true;
			}
			return false;
		} catch (NotFoundException e) {
			return false;
		}
	}

}
