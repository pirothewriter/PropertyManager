package com.tmoncorp.PropertyManager.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext*.xml" })
public class MemberServiceTest {
	@Autowired
	private MemberService memberService;

	@Transactional
	@Test
	public void 정상적으로_여래개의_사원정보를_입력하는지_테스트() throws IOException {
		memberService.multipleMemberInsert("WebContent/csv/test_member.csv");
	}
}
