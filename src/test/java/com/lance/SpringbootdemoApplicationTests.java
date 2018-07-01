package com.lance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void testPass(){
		String pass1="fate";
		String pass2 = "password";
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		String hashPass = encode.encode(pass1);
		String hashPass2 = encode.encode(pass2);
		System.out.println(hashPass);
		System.out.println(hashPass2);
	}

}
