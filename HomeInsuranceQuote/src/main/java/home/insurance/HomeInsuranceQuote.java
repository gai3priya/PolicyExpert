package home.insurance;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;

public class HomeInsuranceQuote extends BaseClass {

	@BeforeTest
	private void setTestDetails() {
		testcaseName = "Home Insurance Quote";
		testDescription = "To enter the input values in home Insurance quote page";
		author = "Gayathri Priya";
	}

	@Test(dataProvider = "fetchData")
	public void enterInputForHomeInsuranceQuote(String title, String firstName, String lastName, String birthDate,
			String birthMonth, String birthYear, String maritalStatus, String occupation, String secondOccupation,
			String mobileNumber, String email, String CCjudgement, String address, String propertyType, String property,
			String builtYear, String bedRoom, String bathRoom, String propertyOwner, String propertyOccupied,
			String adults, String children, String periodOccupied) throws IOException, InterruptedException {

		selectValue("title.xpath", title).setText("firstName.xpath", firstName).setText("lastName.xpath", lastName)
				.selectValue("day.xpath", birthDate).selectValue("month.xpath", birthMonth)
				.selectValue("year.xpath", birthYear).selectValue("maritalStatus.xpath", maritalStatus)
				.setText("occupation.xpath", occupation).selectListItem("listItem.xpath")
				.clickButton("anotherOccupation.xpath").setText("secondOccupation.xpath", secondOccupation)
				.selectListItem("listItem.xpath").setText("mobileNumber.xpath", mobileNumber)
				.setText("email.xpath", email).clickButton("questionAboutProperty.xpath").clickButton("bankrupt.xpath")
				.clickButton("countyCourtJudgement").selectValue("countCCJ.xpath", CCjudgement)
				.clickButton("declinedInsurence.xpath").clickButton("cancelledInsurance.xpath")
				.clickButton("criminalConviction.xpath").setText("address.xpath", address)
				.selectListItem("listItem.xpath").selectListItem("listItem.xpath")
				.clickButton("correspondenceAddress.xpath").selectValue("propertyType.xpath", propertyType)
				.selectValue("property.xpath", property).setText("builtYear.xpath", builtYear)
				.selectValue("bedroom.xpath", bedRoom).selectValue("bathroom.xpath", bathRoom)
				.clickButton("mainResidence.xpath").selectValue("propertyOwner.xpath", propertyOwner)
				.selectValue("propertyOccupied.xpath", propertyOccupied).selectValue("adultsOccupied.xpath", adults)
				.selectValue("childrenOccupied.xpath", children).clickButton("smoker.xpath")
				.selectValue("occupied.xpath", periodOccupied).clickButton("trees.xpath").clickButton("flatRoof.xpath")
				.clickButton("brickWalls.xpath").clickButton("tileRoof.xpath").clickButton("wellMaintained.xpath")
				.clickButton("propertyQuestion.xpath").clickButton("windowsSecured.xpath")
				.clickButton("selfContained.xpath").clickButton("locktype.xpath").clickButton("frenchDoor.xpath")
				.clickButton("externalDoor.xpath").clickButton("claims.xpath").setText("claimAmount.xpath", "2000")
				.selectValue("claimType.xpath", "contents").selectValue("claimReason.xpath", "accidental_damage")
				.selectValue("claimMonth.xpath", "1").selectValue("claimYear.xpath", "2019")
				.clickButton("claimSettled.xpath").clickButton("saveClaim.xpath");
	}

}
