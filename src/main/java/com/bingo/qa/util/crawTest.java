package com.bingo.qa.util;

import com.bingo.qa.service.CrawlService;
import com.bingo.qa.task.CrawlTask;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class crawTest {
    @Autowired
    private CrawlService crawlService;
    @Test
    public void test(){
        crawlService.crawl("programmer",1);

//
//       CrawlTask crawlTask =  new CrawlTask(crawlService);
//       crawlTask.crawl();



    }
}
