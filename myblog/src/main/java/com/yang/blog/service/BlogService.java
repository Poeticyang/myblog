package com.yang.blog.service;

import com.yang.blog.entity.Blog;
import com.yang.blog.vo.*;

import java.util.List;

public interface BlogService {

    //新增博客
    int saveBlog(Blog blog);

    //查询出所有博客
    List<BlogQuery> getAllBlogs();

    //根据id删除博客
    int deleteBlog(Long id);

    //根据id查询出要修改的博客
    BlogEdit getBlogById(Long id);

    //修改博客
    int updateBlog(BlogEdit blogEdit);

    //搜索博客
    List<BlogQuery> searchBlogs(BlogSearch blogSearch);







    //查询所有博客列表
    List<BlogPage> getBlogPage();

    //查询最新推荐博客列表
    List<BlogRecommend> getRecommendBlog();

    //搜索博客
    List<BlogPage> searchBlogPage(String query);

    //查询博客总数
    Integer getBlogTotal();

    //统计访问总数
    Integer getBlogViewTotal();

    //统计评论总数
    Integer getBlogCommentTotal();

    //统计留言总数
    Integer getBlogMessageTotal();



    //查询博客详情
    BlogDetail getBlogDetail(Long id);





    //根据typeId查询出该分类下的所有博客列表
    List<BlogPage> getBlogsByTypeId(Long typeId);




    //查询出所有博客用于时间轴页面展示
    List<BlogQuery> getAllBlogsTime();

}
