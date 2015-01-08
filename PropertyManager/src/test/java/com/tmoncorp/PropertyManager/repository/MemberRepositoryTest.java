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
	public void 제대로_사원정보가_입력되는지_테스트() {
		memberMapper = sqlSession.getMapper(MemberMapper.class);
		MemberModel sampleMember = generateMember();
		memberMapper.insertMemberInfomation(sampleMember);
	}

	@Test
	public void 상위_부서의_리스트를_정상적으로_반환하는지_테스트() {
		memberMapper = sqlSession.getMapper(MemberMapper.class);
		List<String> result = memberMapper.selectUpperDivisions();
		assertNotNull(result);
		System.out.println(result.get(0));
	}

	@Test
	public void 하위_부서의_리스트를_정상적으로_반환하는지_테스트() {
		memberMapper = sqlSession.getMapper(MemberMapper.class);
		assertNotNull(memberMapper.selectUpperDivisions());
	}

	@Test
	public void 전체_사원정보를_들고오는지_테스트() {
		memberMapper = sqlSession.getMapper(MemberMapper.class);
		List<MemberModel> members = memberMapper.selectMembers(0, 20, "", "", "", "");
		assertNotNull(members);
	}

	@Test
	public void 특정_사원정보를_들고오는지_테스트() {
		memberMapper = sqlSession.getMapper(MemberMapper.class);
		assertNotNull(memberMapper.selectAMember("201404016"));
	}
	
	@Test
	public void 조건절의_멤버를_들고오는지_테스트(){
		memberMapper = sqlSession.getMapper(MemberMapper.class);
		assertNotNull(memberMapper.selectMembers(0, 20, "서비스개발랩", "", "piro", ""));
	}

	private MemberModel generateMember() {
		MemberModel memberModel = new MemberModel();

		memberModel.setMemberId("test");
		memberModel.setMemberName("test");
		memberModel.setUpperDivision("test");
		memberModel.setLowerDivision("test");
		memberModel.setAdAccount("test");
		memberModel.setOfficePhoneNumber(1234);
		memberModel.setIsServed('Y');

		return memberModel;
	}

}
