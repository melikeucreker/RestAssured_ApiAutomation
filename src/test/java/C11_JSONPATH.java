import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C11_JSONPATH {
    @Test
    public void test01() {

        /*
        {
  "firstName": "John",
  "lastName": "doe",
  "age": 26,
  "address": {
    "streetAddress": "naist street",
    "city": "Nara",
    "postalCode": "630-0192"
  },
  "phoneNumbers": [
    {
      "type": "iPhone",
      "number": "0123-4567-8888"
    },
    {
      "type": "home",
      "number": "0123-4567-8910"
    }
  ]
}

         */

        JSONObject personalInformation = new JSONObject();
        JSONObject addressInformation = new JSONObject();
        JSONArray phonenumbersArr = new JSONArray();
        JSONObject mobilePhoneInformation = new JSONObject();
        JSONObject homePhoneInformation = new JSONObject();
        addressInformation.put("streetAddress", "naist street");
        addressInformation.put("city", "Nara");
        addressInformation.put("postalCode", "630-0192");
        mobilePhoneInformation.put("type", "iPhone");
        mobilePhoneInformation.put("number", "0123-4567-8888");
        homePhoneInformation.put("type", "home");
        homePhoneInformation.put("number", "0123-4567-8910");
        phonenumbersArr.put(mobilePhoneInformation);
        phonenumbersArr.put(homePhoneInformation);
        personalInformation.put("firstName", "John");
        personalInformation.put("lastName", "doe");
        personalInformation.put("age", "26");
        personalInformation.put("address", addressInformation);
        personalInformation.put("phoneNumbers", phonenumbersArr);
        System.out.println("firstname :" +personalInformation.get("firstName"));
        System.out.println("City : "+ personalInformation.getJSONObject("address").get("city"));

        /*
        instead of them we use address.city
         */


    }


}
