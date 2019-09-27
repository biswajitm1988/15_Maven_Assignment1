package com.fsd.spring;

import com.fsd.spring.entity.Subject;
import com.fsd.spring.service.SubjectService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private Subject subject;

    @Before
    public void init() {
        subject.setSubtitle("hibernate");
    }

    @Test
    public void subjectSearchTest() throws Exception {
       Assert.assertTrue(!CollectionUtils.isEmpty(subjectService.searchBySubject(subject.getSubtitle())));
    }

    @Test
    public void deleteSubjectTest() throws Exception {
        Assert.assertTrue(subjectService.deleteSubject(subject.getSubtitle())!=0);
    }
}
