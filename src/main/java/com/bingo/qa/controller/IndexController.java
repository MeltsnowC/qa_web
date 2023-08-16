package com.bingo.qa.controller;
import com.bingo.qa.dao.QuestionDAO;
import com.bingo.qa.dao.TopUserDAO;
import com.bingo.qa.model.*;
import com.bingo.qa.service.CommentService;
import com.bingo.qa.service.FollowService;
import com.bingo.qa.service.QuestionService;
import com.bingo.qa.service.UserService;
//import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

/**
 */
@Controller
public class IndexController {

    //用户服务：提供用户注册，用户登陆，用户注销，id查找用户，name查找用户
    private final UserService userService;

    //问题服务：查找最近的问题，根据问题id查找问题，添加问题，更新问题评论数，获取问题数。
    private final QuestionService questionService;

    //线程本地变量，保存users
    private final HostHolder hostHolder;

    //关注相关服务：关注、取消关注、获取粉丝列表、获取用户所关注的用户列表（带和不带偏移的），获取某实体的粉丝数，获取某用户关注的用户数，判断是否是粉丝
    private final FollowService followService;

    //评论相关服务：通过实体获取评论列表，添加评论，获取评论数量，根据id获取评论，根据用户id获取评论
    private final CommentService commentService;


    QuestionDAO questionDAO;
    TopUserDAO topUserDAO;

    @Autowired
    public IndexController(UserService userService, QuestionService questionService, HostHolder hostHolder, FollowService followService, CommentService commentService, QuestionDAO questionDAO,TopUserDAO topUserDAO) {
        this.userService = userService;
        this.questionService = questionService;
        this.hostHolder = hostHolder;
        this.followService = followService;
        this.commentService = commentService;
        this.questionDAO = questionDAO;
        this.topUserDAO = topUserDAO;
    }

    @GetMapping(value = {"/", "/index","/search"})
    public String index(Model model) {
        //页面改动 显示数据库中所有问题
        int quencount = questionDAO.getQuestionCount();//获取数据库中的问题数目 li

        List<ViewObject> vos = getQuestions(0, 0, quencount);
        model.addAttribute("vos", vos);


        //这里的bos是看用户是否属于top前20的用户，在top前20的用户界面前有五条推荐求解。如果不在top20里面则不会显示。
        List<Question> questionList = questionService.selectLatestQuestions(0, 0, quencount);
        PriorityQueue<Question> MinProfitQ = new PriorityQueue<>(new RecommendController.MinProfitComparator());//评论少的优先级高。
        for (Question question : questionList) {
            MinProfitQ.add(question);
        }
        List<ViewObject> bos = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();//保存top20的用户
        for (int j = 1; j < 21; j++) {
            set.add(topUserDAO.selectById(j).getUserid());
        }
        User user = hostHolder.getUser();
//        System.out.println(user);
//        System.out.println(set);
        if(user!=null){
            if (set.contains((int)user.getId())) {
                int i  =0;
                //这里用i代表推荐几个问题
                while(i<1){
                    Question question = MinProfitQ.poll();
//                    System.out.println(question);
                    ViewObject bo = new ViewObject();
                    bo.set("question", question);
                    bo.set("user", userService.selectById(question.getUserId()));
                    bo.set("followCount", followService.getFollowerCount(EntityType.ENTITY_QUESTION, question.getId()));
                    bos.add(bo);
                    i++;
                }
            }

        }
        model.addAttribute("bos", bos);

        return "index";
    }


    private List<ViewObject> getQuestions(int userId, int offset, int limit) {
        List<Question> questionList = questionService.selectLatestQuestions(userId, offset, limit);


        List<ViewObject> vos = new ArrayList<>();

        for (Question question : questionList) {
            ViewObject vo = new ViewObject();
//            System.out.println(followService.getFollowerCount(EntityType.ENTITY_QUESTION, question.getId()));
            vo.set("question", question);
            vo.set("user", userService.selectById(question.getUserId()));
            vo.set("followCount", followService.getFollowerCount(EntityType.ENTITY_QUESTION, question.getId()));
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 根据url传入的userid，返回profileuser，返回给前端
     * 简介信息，包括用户评论个数，粉丝个数，关注个数，以及该线程用户判断是否是查询userid的粉丝
     * @param model
     * @param userId
     * @return
     */
    @GetMapping(value = "/user/{userId}")
    public String userIndex(Model model,
                            @PathVariable("userId") int userId) {
        model.addAttribute("vos", getQuestions(userId, 0, 10));

        User user = userService.selectById(userId);
        ViewObject vo = new ViewObject();
        vo.set("user", user);
        vo.set("commentCount", commentService.getUserCommentCount(userId));
        vo.set("followerCount", followService.getFollowerCount(EntityType.ENTITY_USER, userId));
        vo.set("followeeCount", followService.getFolloweeCount(userId, EntityType.ENTITY_USER));
        if (hostHolder.getUser() != null) {
            vo.set("followed", followService.isFollower(hostHolder.getUser().getId(), EntityType.ENTITY_USER, userId));
        } else {
            vo.set("followed", false);
        }
        model.addAttribute("profileUser", vo);
        return "profile";
    }


    public static class MinProfitComparator implements Comparator<Question> {
        @Override
        public int compare(Question o1, Question o2) {
            return o1.getCommentCount() - o2.getCommentCount();
        }
    }


}