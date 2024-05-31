package com.comcast.crm.leadtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ComposeEmailPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewLeadPage;
import com.comcast.crm.objectrepositoryutility.EmailPage;
import com.comcast.crm.objectrepositoryutility.EmailTemplatesPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LeadInformationPage;
import com.comcast.crm.objectrepositoryutility.LeadsPage;
import com.comcast.crm.objectrepositoryutility.MyPreferencesPage;

public class LeadTest extends BaseClass {
	@Test(groups = "regressionTest")
	public void edit_Last_Name_Of_Lead() throws Throwable {

		String lastName = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_012", "Last Name");
		String company = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_012", "Company");
		String updatedLastName = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_012",
				"edit Last Name");

		/* Navigate to Create Lead Page */
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();

		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadImg().click();

		/* Create New Lead */
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.createLead(lastName, company);

		/* Verify the Created Lead */
		LeadInformationPage lip = new LeadInformationPage(driver);
		String hdrMsg = lip.getHeaderMsg().getText();
		boolean getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);
		String actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, lastName);
		String actCompany = lip.getCompanyTxt().getText();
		Assert.assertEquals(actCompany, company);

		/* Edit Last Name of Lead */
		lip.getEditBtn().click();
		cnlp.getLastnameEdt().clear();
		cnlp.getLastnameEdt().sendKeys(updatedLastName);
		cnlp.getSaveBtn().click();

		/* Verify the Updated Last Name of Lead */
		hdrMsg = lip.getHeaderMsg().getText();
		getBoolean = hdrMsg.contains(updatedLastName);
		Assert.assertTrue(getBoolean);
		actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, updatedLastName);
		Assert.assertNotEquals(lastName, updatedLastName);

	}

	@Test(groups = "regressionTest")
	public void edit_Company_Of_Lead() throws Throwable {

		String lastName = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_013", "Last Name");
		String company = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_013", "Company");
		String updatedCompany = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_013",
				"Edit Company");

		/* Navigate to Create Lead Page */
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();

		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadImg().click();

		/* Create New Lead */
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.createLead(lastName, company);

		/* Verify the Created Lead */
		LeadInformationPage lip = new LeadInformationPage(driver);
		String hdrMsg = lip.getHeaderMsg().getText();
		boolean getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);
		String actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, lastName);
		String actCompany = lip.getCompanyTxt().getText();
		Assert.assertEquals(actCompany, company);

		/* Edit company of Lead */
		lip.getEditBtn().click();
		cnlp.getCompanyEdt().clear();
		cnlp.getCompanyEdt().sendKeys(updatedCompany);
		cnlp.getSaveBtn().click();

		/* Verify the Updated Last Name of Lead */
		actCompany = lip.getCompanyTxt().getText();
		Assert.assertEquals(actCompany, updatedCompany);
		Assert.assertNotEquals(company, updatedCompany);
	}

	@Test(groups = "regressionTest")
	public void delete_Lead() throws Throwable {

		String lastName = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_014", "Last Name")
				+ jLib.getRandomNumber();
		String company = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_014", "Company");

		/* Navigate to Create Lead Page */
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();

		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadImg().click();

		/* Create New Lead */
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.createLead(lastName, company);

		/* Verify the Created Lead */
		LeadInformationPage lip = new LeadInformationPage(driver);
		String hdrMsg = lip.getHeaderMsg().getText();
		boolean getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);
		String actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, lastName);
		String actCompany = lip.getCompanyTxt().getText();
		Assert.assertEquals(actCompany, company);

		/* Delete the Lead */
		lip.getDeleteBtn().click();
		wLib.switchtoAlertAndAccept(driver);

		/* Verify the Lead is deleted */
		boolean isDisplayedOrNot = wLib.verifyElementDisplayedOrNot(driver, "//a[text()=" + lastName + "]");
		Assert.assertFalse(isDisplayedOrNot);
	}

	@Test(groups = "regressionTest")
	public void cancel_The_Deleting_Lead() throws Throwable {

		String lastName = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_015", "Last Name")
				+ jLib.getRandomNumber();
		String company = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_015", "Company");

		/* Navigate to Create Lead Page */
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();

		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadImg().click();

		/* Create New Lead */
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.createLead(lastName, company);

		/* Verify the Created Lead */
		LeadInformationPage lip = new LeadInformationPage(driver);
		String hdrMsg = lip.getHeaderMsg().getText();
		boolean getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);
		String actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, lastName);
		String actCompany = lip.getCompanyTxt().getText();
		Assert.assertEquals(actCompany, company);

		/* Delete the Lead */
		lip.getDeleteBtn().click();
		wLib.switchtoAlertAndCancel(driver);

		/* Verify the Lead is not deleted */
		hdrMsg = lip.getHeaderMsg().getText();
		getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);
		actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, lastName);
	}

	@Test(groups = "regressionTest")
	public void duplicate_Lead() throws Throwable {

		String lastName = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_016", "Last Name")
				+ jLib.getRandomNumber();
		String company = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_016", "Company");

		/* Navigate to Create Lead Page */
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();

		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadImg().click();

		/* Create New Lead */
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.createLead(lastName, company);

		/* Verify the Created Lead */
		LeadInformationPage lip = new LeadInformationPage(driver);
		String hdrMsg = lip.getHeaderMsg().getText();
		boolean getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);
		String actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, lastName);
		String actCompany = lip.getCompanyTxt().getText();
		Assert.assertEquals(actCompany, company);

		/* Duplicate the Lead */
		lip.getDuplicateBtn().click();
		hdrMsg = lip.getDuplicatingHdrTxt().getText();
		getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);

		cnlp.getSaveBtn().click();

		/* Verify the duplicated Lead */
		hdrMsg = lip.getHeaderMsg().getText();
		getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);
		actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, lastName);
		actCompany = lip.getCompanyTxt().getText();
		Assert.assertEquals(actCompany, company);

	}

	@Test(groups = "regressionTest")
	public void create_Lead_With_Without_Last_Name() throws Throwable {

		String company = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_017", "Company");
		String expectedAlertMsg = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_017",
				"ExpectedAlertMsg");

		/* Navigate to Create Lead Page */
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();

		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadImg().click();

		/* Create New Lead */
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.getCompanyEdt().sendKeys(company);
		cnlp.getSaveBtn().click();

		String actAlertMsg = wLib.switchtoAlertAndGetAlertMsg(driver);
		wLib.switchtoAlertAndAccept(driver);
		Assert.assertEquals(actAlertMsg, expectedAlertMsg);

	}

	@Test(groups = "regressionTest")
	public void create_Lead_With_Without_Company() throws Throwable {

		String lastName = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_018", "Last Name");
		String expectedAlertMsg = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_018",
				"ExpectedAlertMsg");

		/* Navigate to Create Lead Page */
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();

		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadImg().click();

		/* Create New Lead */
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.getLastnameEdt().sendKeys(lastName);
		cnlp.getSaveBtn().click();

		String actAlertMsg = wLib.switchtoAlertAndGetAlertMsg(driver);
		wLib.switchtoAlertAndAccept(driver);
		Assert.assertEquals(actAlertMsg, expectedAlertMsg);
	}

	@Test(groups = "regressionTest")
	public void save_Mail_For_Created_Lead() throws Throwable {

		String lastName = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_019", "Last Name");
		String company = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_019", "Company");
		String email = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_019", "Email");
		String subject = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_019", "Subject");
		String composeEmailPartialURL = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_019",
				"ComposeEmailPartialURL");
		String emailTemplatePartialURL = eLib.getDataFromExcel("./testdata/testScriptdata.xlsx", "Leads", "LD_019",
				"EmailTemplatePartialURL");

		/* Fetch user email address */
		HomePage hp = new HomePage(driver);
		wLib.mousemoveOnElement(driver, hp.getAdminImg());
		hp.getMyPreferenceLnk().click();

		MyPreferencesPage mpp = new MyPreferencesPage(driver);
		String userEmail = mpp.getEmailLnk().getText();

		/* Navigate to Create Lead Page */
		hp.getLeadsLink().click();

		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadImg().click();

		/* Create New Lead */
		CreatingNewLeadPage cnlp = new CreatingNewLeadPage(driver);
		cnlp.getLastnameEdt().sendKeys(lastName);
		cnlp.getCompanyEdt().sendKeys(company);
		cnlp.getEmailEdt().sendKeys(email);
		cnlp.getSaveBtn().click();

		/* Verify the Created Lead */
		LeadInformationPage lip = new LeadInformationPage(driver);
		String hdrMsg = lip.getHeaderMsg().getText();
		boolean getBoolean = hdrMsg.contains(lastName);
		Assert.assertTrue(getBoolean);
		String actLastName = lip.getLastNameTxt().getText();
		Assert.assertEquals(actLastName, lastName);
		String actCompany = lip.getCompanyTxt().getText();
		Assert.assertEquals(actCompany, company);
		String actEmail = lip.getEmailLnk().getText();
		Assert.assertEquals(actEmail, email);

		/* Send Mail */
		lip.getSendMailLnk().click();
		lip.getSelectBtn().click();
		wLib.switchToTabOnURL(driver, composeEmailPartialURL);

		ComposeEmailPage cep = new ComposeEmailPage(driver);
		String toEmail = cep.getToEdt().getAttribute("value");
		getBoolean = toEmail.contains(email);
		Assert.assertTrue(getBoolean);

		/* Select Email template */
		cep.getSelectEmailTemplateBtn().click();
		wLib.switchToTabOnURL(driver, emailTemplatePartialURL);
		EmailTemplatesPage etp = new EmailTemplatesPage(driver);
		etp.getFollowUpLnk().click();

		/* Enter subject and Save the Mail */
		wLib.switchToTabOnURL(driver, composeEmailPartialURL);
		cep.getSubjectEdt().clear();
		cep.getSubjectEdt().sendKeys(subject);
		cep.getSaveBtn().click();

		/* Navigate to Email Page */
		wLib.switchToTabOnURL(driver, "module=Leads");
		hp.getEmailLink().click();

		EmailPage ep = new EmailPage(driver);
		ep.getSearchEdt().sendKeys(subject);
		ep.getFindBtn().click();
		

		driver.findElement(By.xpath("//b[contains(text(),"+subject+")]")).click();
		/* Verify Email details */
		String actFromEmail = ep.getFromEmailtxt().getText();
		Assert.assertEquals(actFromEmail, userEmail);
		String actToEmail = ep.getToEmailtxt().getText();
		getBoolean = actToEmail.contains(lastName);
		Assert.assertTrue(getBoolean);
		getBoolean = actToEmail.contains(email);
		Assert.assertTrue(getBoolean);
		String actSubject = ep.getSubjecttxt().getText();
		Assert.assertEquals(actSubject, subject);

	}
}
