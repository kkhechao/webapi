package com.zqkh.webapi;

import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.utils.JwtTokenContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {


    @Autowired
    private JwtTokenContextUtil jwtTokenContext;

	@Test
	public void contextLoads() {

        JWTUserDto dto = new JWTUserDto();
        dto.setId("c00072cfe91e444e48232eae8349e2fed");
        dto.setMobile("13997470653");
        dto.setUserId("c00072cfe91e444e48232eae8349e2fed");
        String s = jwtTokenContext.generateToken(dto);
        System.out.println(s);

	}

}
