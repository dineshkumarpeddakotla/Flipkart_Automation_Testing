/*
 *Purpose : Class is implemented to provide multiple data for test cases from excel sheet
 *                @DataProvider is provides the data to test cases
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */

package com.flipkartapplication.utility;

import com.flipkartapplication.constants.IConstants;

public class DataProvider {

    /**
     * getDataFromProvider is used get the from excel sheet
     * @return data from excel sheet
     */
    @org.testng.annotations.DataProvider(name = "LoginDetails")
    public static Object[][] getDataFromProvider() {
//       ExcelUtil excelUtil = new ExcelUtil(IConstants.EXCEL_FILE_PATH, "Sheet1");

//        return excelUtil.readData();
        JsonUtil jsonUtil = new JsonUtil();
        return jsonUtil.readJson(IConstants.JSON_FILE_PATH);
    }
}
