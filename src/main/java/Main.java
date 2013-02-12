import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String endpoint = "https://www.tagtagcity.com/api/services/service.php";
//			String endpoint = "http://localhost:19080/api/services/service.php";

			Service service = new Service();
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://www.belfius.be/",
					"getToken"));

			call.addParameter(new QName("http://www.belfius.be/", "apiKey"),
					org.apache.axis.Constants.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);
			call.addParameter(new QName("http://www.belfius.be/", "userID"),
					org.apache.axis.Constants.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);

			QName qnStringArr = new QName( "http://www.belfius.be/", "ArrayOf_xsd_string");

			call.registerTypeMapping(java.lang.String[].class, qnStringArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

			call.setReturnType(qnStringArr );
			
			String[] ret =  (String[])call.invoke(new Object[] {
					"24912633bd03b88061d7282d28125e8c",
					"c4ca4238a0b923820dcc509a6f75849b" });

			for (String string : ret) {
				System.out.println(string);
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
