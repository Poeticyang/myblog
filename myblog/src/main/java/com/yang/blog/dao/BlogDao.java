package com.yang.blog.dao;


import com.yang.blog.entity.Blog;
import com.yang.blog.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao {

    //新增博客
    int saveBlog(Blog blog);

    //查询出所有博客
    List<BlogQuery> getAllBlogs();

    //根据id删除博客
    int deleteBlog(Long id);

    //根据id查询出要修改的博客
    BlogEdit getBlogById(Long id);

    //修改博客
    int updateBlog(BlogEdit blogShow);

    //搜索博客
    List<BlogQuery> searchBlogs(BlogSearch blogSearch);




    //查询博客列表
    List<BlogPage> getBlogPage();

    //查询最新推荐博客列表
    List<BlogRecommend> getRecommendBlog();

    //搜索博客
    List<BlogPage> searchBlogPage(String query);

    //统计博客总数
    Integer getBlogTotal();

    //统计访问总数
    Integer getBlogViewTotal();

    //统计评论次数
    Integer getBlogCommentTotal();

    //统计留言次数
    Integer getBlogMessageTotal();



    //查询博客详情
    BlogDetail getBlogDetail(Long id);

    //博客访问更新
    int updateBlogViews(Long id);

    //根据博客id查询出评论数量
    int getCommentCountById(Long id);



    //根据typeId查询出博客列表
    List<BlogPage> getBlogsByTypeId(Long typeId);



    //查询出所有博客
    List<BlogQuery> getAllBlogsTime();


}
