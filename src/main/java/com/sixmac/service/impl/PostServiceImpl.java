package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.dao.PostDao;
import com.sixmac.dao.PostImageDao;
import com.sixmac.dao.UserDao;
import com.sixmac.entity.*;
import com.sixmac.service.PostService;
import com.sixmac.utils.FileUtil;
import com.sixmac.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019 下午 1:46.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostImageDao postImageDao;

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }

    @Override
    public Page<Post> find(int pageNum, int pageSize) {
        return postDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Post> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Post getById(Long id) {
        return postDao.findOne(id);
    }

    @Override
    public Post deleteById(Long id) {
        Post post = getById(id);
        postDao.delete(post);
        return post;
    }

    @Override
    public Post create(Post post) {
        return postDao.save(post);
    }

    @Override
    public Post update(Post post) {
        return postDao.save(post);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<Post> page(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Post> page = postDao.findAll(new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        return postDao.findByUserId(userId);
    }

    @Override
    public Page<Post> page(final Long userId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Post> page = postDao.findAll(new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (userId != null) {
                    Predicate pre = cb.equal(root.get("user").get("id").as(Long.class), userId);
                    predicateList.add(pre);
                }

                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }

    @Override
    @Transactional
    public void publish(HttpServletRequest request, HttpServletResponse response, Long userId, String content) {
        MultipartFile multipartFile = null;
        MultipartRequest multipartRequest = null;

        if(request instanceof MultipartRequest) {
            multipartRequest = (MultipartRequest) request;

        }

        User user = userDao.findOne(userId);
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);
        post.setStatus(0);
        postDao.save(post);

        try {
            // 保存圈子图片集合
            PostImage postImage = new PostImage();

            if(multipartRequest != null) {

                // 获取图片集合
                Iterator<String> fileList = multipartRequest.getFileNames();
                while (fileList.hasNext()) {
                    String fileName = fileList.next();
                    MultipartFile file = multipartRequest.getFile(fileName);
                    if (null != file) {
                        postImage = new PostImage();
                        postImage.setPostId(post.getId());
                        postImage.setStatus(0);
                        postImage.setAvater(FileUtil.save(file).getPath());

                        postImageDao.save(postImage);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.printApi(response, new Result(false).msg(ErrorCode.ERROR_CODE_0001));
        }
    }

}