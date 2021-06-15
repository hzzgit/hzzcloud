package com.hzz.hzzcloud.Hutool和guava工具包使用;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.google.common.collect.Lists;
import com.hzz.hzzcloud.Hutool和guava工具包使用.vo.TreeOfficeVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 15:19
 */
public class TreeUtilTest {

    public static void main(String[] args) {

        ArrayList<TreeOfficeVO> treeOfficeVOS = Lists.newArrayList(TreeOfficeVO.builder().officeId(1L).parentId(0L).name("一级商户").build()
                , TreeOfficeVO.builder().officeId(2L).parentId(1L).name("二级商户").build(),
                TreeOfficeVO.builder().officeId(21L).parentId(1L).name("二级商户2").build(),
                TreeOfficeVO.builder().officeId(31L).parentId(21L).name("三级商户2").build(),
                TreeOfficeVO.builder().officeId(3L).parentId(2L).name("三级商户").build());

        final List<Tree<Long>> trees = TreeUtil.build(treeOfficeVOS, 0L,
                (node, tree) -> {
                    tree.setId(node.getOfficeId());
                    tree.setParentId(node.getParentId());
                    tree.setName(node.getName());
                });

        System.out.println(trees);

    }



}
