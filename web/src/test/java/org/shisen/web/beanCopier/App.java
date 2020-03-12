package org.shisen.web.beanCopier;

import com.boot.common.beans.TypeConverter;
import com.boot.common.beans.WrapperBeanConverter;
import com.boot.common.beans.WrapperBeanConverterBuilder;
import com.boot.common.beans.WrapperBeanCopier;
import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/10 11:10
 * </pre>
 */
public class App {

    /**
     * 属性名相同, 类型相同, 转换成功
     */
    @Test
    public void normalCopyTest() {
        User user = new User();
        user.setAge(10);
        user.setName("施森");
        UserDto userDto = WrapperBeanCopier.convert(user, UserDto.class);
        System.out.println("user = " + user);
        System.out.println("userDto = " + userDto);
    }


    /**
     * 属性名相同, 类型不同, 转换失败
     */
    @Test
    public void normalCopyWithDiffTypeTest() {
        User user = new User();
        user.setAge(10);
        user.setName("施森");
        UserWithDiffType userDto = WrapperBeanCopier.convert(user, UserWithDiffType.class);
        System.out.println("user = " + user);
        System.out.println("userDto = " + userDto);
    }

    @Test
    public void converterTest() {
        Account po = new Account();
        po.setId(1);
        po.setCreateTime(new Date());
        po.setBalance(BigDecimal.valueOf(4000L));

        WrapperBeanConverter converter = WrapperBeanConverterBuilder.create()
                .registerConverter((TypeConverter<Date, String>) Date::toString)
                .registerConverter((TypeConverter<BigDecimal, String>) BigDecimal::toString)
                .build();

        AccountDto dto = converter.convert(po, AccountDto.class);
        System.out.println("po = " + po);
        System.out.println("dto = " + dto);
    }

    @Test
    public void converterStringTest() {
        AccountDto dto = new AccountDto();
        dto.setId(1);
        dto.setCreateTime("2020-03-11");
        dto.setUpdateTime("2020-03-11 03:03:03");
        dto.setBalance("4000");

        WrapperBeanConverter converter = WrapperBeanConverterBuilder.create()
                .registerConverter((TypeConverter<String, Date>)(String x) -> {
                    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parse = null;
                    try {
                        parse = dateTimeFormat.parse(x);
                    } catch (ParseException e) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            parse = dateFormat.parse(x);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                    return parse;
                })
                .registerConverter((TypeConverter<String, BigDecimal>) x-> BigDecimal.valueOf(Double.parseDouble(x)))
                .build();

        Account po = converter.convert(dto, Account.class);
        System.out.println("dto = " + dto);
        System.out.println("po = " + po);
    }


    @Test
    public void sameFieldListTest() {
        User user1 = new User();
        user1.setName("user1");
        user1.setAge(1);
        User user2 = new User();
        user2.setName("user2");
        user2.setAge(2);
        User user3 = new User();
        user3.setName("user3");
        user3.setAge(3);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        List<UserDto> userDtos = WrapperBeanCopier.convert(users, UserDto.class);

        System.out.println("convert = " + userDtos);

    }


    @Test
    public void copyWithConverter() {
        Account account = new Account();
        account.setId(1);
        account.setCreateTime(new Date());
        account.setBalance(BigDecimal.valueOf(12.21));
        BeanCopier beanCopier = BeanCopier.create(Account.class, AccountDto.class, true);

        AccountDto accountDto = new AccountDto();

        AccountConverter accountConverter = new AccountConverter();

        beanCopier.copy(account,accountDto,accountConverter);

        System.out.println("accountDto = " + accountDto);


    }

    @Test
    public void testBigDecimal() {
        BigDecimal bigDecimal = BigDecimal.valueOf(3.46);
        String s = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_EVEN).stripTrailingZeros().toPlainString();
        System.out.println("s = " + s);
    }

}
