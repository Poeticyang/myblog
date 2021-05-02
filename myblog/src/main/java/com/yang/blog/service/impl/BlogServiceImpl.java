package com.yang.blog.service.impl;

import com.yang.blog.dao.BlogDao;
import com.yang.blog.entity.Blog;
import com.yang.blog.exception.NotFoundException;
import com.yang.blog.service.BlogService;
import com.yang.blog.utils.MarkdownUtils;
import com.yang.blog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    /**
     * 新增博客
     */
    @Override
    public int saveBlog(Blog blog) {

        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);//浏览次数
        blog.setCommentCount(0);//评论次数
        return blogDao.saveBlog(blog);
    }

    /**
     * 查询出所有博客
     */
    @Override
    public List<BlogQuery> getAllBlogs() {
        return blogDao.getAllBlogs();
    }

    /**
     * 根据id删除博客
     */
    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }

    /**
     * 根据id查询出要修改的博客
     */
    @Override
    public BlogEdit getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    /**
     * 修改博客
     */
    @Override
    public int updateBlog(BlogEdit blogEdit) {
        blogEdit.setUpdateTime(new Date());
        return blogDao.updateBlog(blogEdit);
    }

    /**
     * 搜索博客
     */
    @Override
    public List<BlogQuery> searchBlogs(BlogSearch blogSearch) {
        return blogDao.searchBlogs(blogSearch);
    }









    //查询博客列表
    @Override
    public List<BlogPage> getBlogPage() {
        return blogDao.getBlogPage();
    }

    //查询出最新推荐博客列表
    @Override
    public List<BlogRecommend> getRecommendBlog() {
        return blogDao.getRecommendBlog();
    }

    //搜索博客
    @Override
    public List<BlogPage> searchBlogPage(String query) {
        return blogDao.searchBlogPage(query);
    }

    //统计博客总数
    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    //统计访问总数
    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    //统计评论总数
    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    //统计留言总数
    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }





    //查询博客详情
    @Override
    public BlogDetail getBlogDetail(Long id) {

        BlogDetail blogDetail = blogDao.getBlogDetail(id);
        if (blogDetail == null){
            throw new NotFoundException("该博客不存在");
        }
        //获取文章主题内容
        String content = blogDetail.getContent();
        blogDetail.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        //博客访问数量增加
        blogDao.updateBlogViews(id);

        //查询博客评论数量并增加
        blogDao.getCommentCountById(id);

        return blogDetail;
    }


    /**
     * 根据typeId查询博客列表
     */
    @Override
    public List<BlogPage> getBlogsByTypeId(Long typeId) {
        return blogDao.getBlogsByTypeId(typeId);
    }


    /**
     * 查询出所有博客用于时间轴页面展示
     */
    @Override
    public List<BlogQuery> getAllBlogsTime() {
        return blogDao.getAllBlogsTime();
    }


}
