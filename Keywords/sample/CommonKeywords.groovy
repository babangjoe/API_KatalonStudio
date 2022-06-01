package sample

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

import internal.GlobalVariable

public class CommonKeywords {

	public static JsonSlurper jsonSlurper = new JsonSlurper()

	@Keyword
	def int createNewOrder(String order_id, String order_description, String last_updated, int expectedStatus) {
		def response = WS.sendRequestAndVerify(findTestObject("Object Repository/POST a new order",
				["order_id": order_id, "order_description": order_description, "last_updated_timestamp": last_updated]))

		def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		return jsonResponse.order_status + "_" + jsonResponse.last_updated_timestamp
	}
}
