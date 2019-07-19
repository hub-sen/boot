import com.pengfeng.BeanDefinition;
import com.pengfeng.PropertyValue;
import com.pengfeng.PropertyValues;
import com.pengfeng.factory.AutowireCapableBeanFactory;
import com.pengfeng.factory.BeanFactory;
import org.junit.Test;

/**
 * @author pengfeng
 */
public class BeanFactoryTest {

	@Test
	public void test() throws Exception {
		// 1.初始化beanfactory
		BeanFactory beanFactory = new AutowireCapableBeanFactory();

		// 2.bean定义
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClassName("HelloWorldService");

		// 3.设置属性
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("text", "Hello World!"));
        beanDefinition.setPropertyValues(propertyValues);

		// 4.生成bean
		beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

		// 5.获取bean
		HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
		helloWorldService.helloWorld();

	}
}
