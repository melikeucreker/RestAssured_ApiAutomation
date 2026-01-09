import org.json.JSONObject;
import org.junit.Test;


    import org.json.JSONObject;
import org.junit.Test;

public class C2_JsonObject_Create {

        @Test
        public void test01(){

        /*

                {
                     "firstname":"Jim",
                     "additionalneeds":"Breakfast",
                     "bookingdates":{
                             "checkin":"2018-01-01",
                             "checkout":"2019-01-01"
                    },
                      "totalprice":111,
                      "depositpaid":true,
                      "lastname":"Brown"
                 }
         */

            JSONObject requestBody = new JSONObject();

            JSONObject bookingdatesJsonObject = new JSONObject();
            bookingdatesJsonObject.put("checkin","2018-01-01");
            bookingdatesJsonObject.put("checkout","2019-01-01");

            requestBody.put("firstname","Jim");
            requestBody.put("additionalneeds","Breakfast");
            requestBody.put("bookingdates",bookingdatesJsonObject);
            requestBody.put("totalprice",111);
            requestBody.put("depositpaid",true);
            requestBody.put("lastname","Brown");


            System.out.println(requestBody);

        /*
                {
                 "firstname":"Jim",
                 "additionalneeds":"Breakfast",
                 "bookingdates":{
                            "checkin":"2018-01-01",
                            "checkout":"2019-01-01"},
                  "totalprice":111,
                  "depositpaid":true,
                  "lastname":"Brown"}



         */

        }
}
