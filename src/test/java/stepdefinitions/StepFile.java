package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepFile {

    private final String fileName = "src\\test\\resources\\purchase_orders.xls";
    private File file;
    private int rowNumber;
    private List<String> headerLines;

    @Before
    public void setUp() {
        file = new File(fileName);
        headerLines = new ArrayList<>();
    }

    @Given("^purchase_orders.xls file exists$")
    public void checkFileExists() {
        Assert.assertTrue(file.exists(), "Purchase orders xls file does not exist.");
    }

    @When("^the user opens purchase_orders.xls$")
    public void openAndReadFile() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if(headerRow != null) {
                for(Cell cell : headerRow) {
                    headerLines.add(cell.getStringCellValue());
                }
            }
            rowNumber = sheet.getLastRowNum(); //including header
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("File could not be opened.");
        }
    }

    @Then("^the xls file has the following headers$")
    public void validateHeader(DataTable dataTable) {
        List<List<String>> expectedHeaders = dataTable.asLists(String.class);
        for(int i = 0; i < expectedHeaders.get(0).size(); i++) {
            Assert.assertEquals(headerLines.get(i), expectedHeaders.get(0).get(i),
                    String.format("Actual header is %s, but expected header is %s in column %s",
                            headerLines.get(i), expectedHeaders.get(0).get(i), i+1));
        }
    }

    @And("the xls file has more than {int} lines")
    public void validateRowNumber(int expectedRowNumber) {
        Assert.assertTrue(rowNumber > expectedRowNumber, String.format("Row number is %s, " +
                "but expected row number is %s", rowNumber, expectedRowNumber));
    }
}
