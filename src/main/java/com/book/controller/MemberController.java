package com.book.controller;

import com.book.data.entity.Member;
import com.book.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by jotaiwan on 29/07/2017.
 */
@Controller
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    MemberService memberService;

    @RequestMapping("/members")
    public Model members(Model model) {
        List<Member> allMembers = memberService.findAll();
        model.addAttribute("members", allMembers);
        model.addAttribute("total", allMembers.size());

        logger.debug("Total members: {}", allMembers.size());
        logger.error("Total members: {}", allMembers.size());
        return model;
    }
}
