package com.tmoncorp.PropertyManager.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tmoncorp.PropertyManager.model.MemberModel;

/**
 * 
 * @author piro
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext*.xml" })
public class MemberRepositoryTest {
	private MemberMapper memberMapper;
	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	@Test
	public void 제대로_사원정보가_입력되는지_테스트(){
		memberMapper = sqlSession.getMapper(MemberMapper.class);
		MemberModel sampleMember = generateMember();
		memberMapper.insertMemberInfomation(sampleMember);
	}
	
	@Test
	public void 전체_사원정보를_들고오는지_테스트(){
		memberMapper = sqlSession.getMapper(MemberMapper.class);
		List<MemberModel> allMembers = memberMapper.selectAllMembers();
		assertNotNull(allMembers);
		System.out.println(allMembers.get(0).getMemberId());
	}
	
	private MemberModel generateMember(){
		MemberModel memberModel = new MemberModel();
		
		memberModel.setMemberId("test");
		memberModel.setMemberName("test");
		memberModel.setUpperDivision("test");
		memberModel.setLowerDivision("test");
		memberModel.setAdAccount("test");
		memberModel.setOfficePhoneNumber(1234);
		
		return memberModel;
	}

}
