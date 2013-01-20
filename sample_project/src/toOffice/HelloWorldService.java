package wsdl.learn;
public class HelloWorldService 
{
	public String sayHello(String name) {
		
		return "Hello : " + name;
	}
	public String getAdd(int x, int y) {
		
		return "Result sum : " + (x + y) ;
	}
}
